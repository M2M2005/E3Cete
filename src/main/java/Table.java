import java.util.Scanner;

/**
 * La classe Table représente une table de jeu contenant des cartes.
 * <p>
 * La table est représentée graphiquement par une matrice.
 * On peut donc avoir des tables de dimensions 3x3, 3x4, 4x4, 5x5, 10x15...
 * En mémoire, la Table est représentée par un simple tableau (à une dimension)
 * Quand elle est initialisée, la table est vide.
 * <p>
 * Pour désigner une carte sur la table, on utilise des coordonées (i,j) ou i
 * représenta la ligne et j la colonne.
 * Les lignes et colonnes sont numérotés à partir de 1.
 * Les cartes sont numérotées à partir de 1.
 * <p>
 * Par exemple, sur une table 3x3, la carte en position (1,1) est la premiere
 * carte du tableau, soit celle à l'indice 0.
 * La carte (2,1) => carte numéro 4 stockée à l'indice 3 dans le tableau
 * représenatnt la table
 * La carte (3,3) => carte numéro 9 stockée à l'indice 8 dans le tableau
 * représenatnt la table
 */
public class Table {
    private final int colonne;
    private final int ligne;
    private final Carte[] cartes;

    /**
     * Pre-requis : hauteur >=3, largeur >=3
     * <p>
     * Action : Construit une table "vide" avec les dimensions précisées en
     * paramètre.
     * <p>
     * Exemple : hauteur : 3, largeur : 3 => construit une table 3x3 (pouvant donc
     * accueillir 9 cartes).
     */

    public Table(int hauteur, int largeur) {
        this.colonne = hauteur;
        this.ligne = largeur;
        this.cartes = new Carte[hauteur * largeur];

    }

    /**
     * Résullat : Le nombre de cartes que la table peut stocker.
     */

    public int getTaille() {
        return this.colonne * this.ligne;
    }

    /**
     * Pre-requis : la table est pleine
     * Action : Affiche des cartes de la table sous forme de matrice
     * L'affichage des cartes doit respecter le format défini dans la classe Carte
     * (chaque carte doit donc être colorée).
     * On ne donne volontairement pas d'exemple puisque celà depend du choix fait
     * pour votre représentation de Carte
     */

    public String toString() {
        StringBuilder affiche = new StringBuilder("\n");
        int x = 0;
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                if (cartes[x] != null) {
                    affiche.append("[").append(cartes[x].toString()).append("] ");
                } else {
                    affiche.append("[   ]");
                }
                x++;
            }
            affiche.append("\n");
        }
        return affiche.toString();
    }

    /**
     * Résullat : Vrai la carte située aux coordonnées précisées en paramètre est
     * une carte possible pour la table.
     */
    public boolean carteExiste(Coordonnees coordonnees) {
        return coordonnees.getColonne() <= this.colonne && coordonnees.getColonne() >= 0
                && coordonnees.getLigne() <= this.ligne && coordonnees.getLigne() >= 0;
    }

    /**
     * Pre-requis :
     * Il reste des cartes sur la table.
     * <p>
     * Action : Fait sélectionner au joueur (par saisie de ses coordonnées) une
     * carte valide (existante) de la table.
     * L'algorithme doit faire recommencer la saisie au joueur s'il ne saisit pas
     * une carte valide.
     * <p>
     * Résullat : Le numéro de carte sélectionné.
     */

    public int faireSelectionneUneCarte(int numC) {
        System.out.println("Choisir Carte " + numC + " :");
        String carte = saisirChaine();
        new Coordonnees(carte);
        Coordonnees coor;
        while (!Coordonnees.formatEstValide(carte)) {
            System.out.println("Ce n'est pas une saisie valide");
            carte = saisirChaine();
        }
        coor = new Coordonnees(carte);
        return (coor.getLigne() - 1) * 3 + coor.getColonne() - 1;
    }

    /**
     * Pre-requis : 1<=nbCartes <= nombre de Cartes de this
     * Action : Fait sélectionner nbCartes Cartes au joueur sur la table en le
     * faisant recommencer jusqu'à avoir une sélection valide.
     * Il ne doit pas y avoir de doublons dans les numéros de cartes sélectionnées.
     * Résullat : Un tableau contenant les numéros de cartes sélectionnées.
     */

    public int[] selectionnerCartesJoueur(int nbCartes) {
        int[] cartes = new int[nbCartes];
        for (int i = 0; i < nbCartes; i++) {
            cartes[i] = faireSelectionneUneCarte(i + 1);
        }
        while (cartes[0] == cartes[1] || cartes[1] == cartes[2] || cartes[2] == cartes[0]) {
            System.out.println("Impossible de choisir plusieurs fois la même carte. \nChoisissez 3 cartes différentes.");
            for (int i = 0; i < nbCartes; i++) {
                cartes[i] = faireSelectionneUneCarte(i + 1);
            }
        }
        return cartes;
    }

    public void placerCarte(Carte carte, int coord) {
        cartes[coord] = carte;
    }

    public void placerCarteTab(Carte[] carte) {
        System.arraycopy(carte, 0, cartes, 0, carte.length);
    }

    public Carte getCarte(int i) {
        return cartes[i];
    }

    public static String saisirChaine() {
        Scanner clavier = new Scanner(System.in);
        return clavier.nextLine();
    }
}
