import java.util.Random;

/**
 * La classe Jeu permet de faire des parties du jeu "E3Cète" soit avec un
 * humain, soit avec un ordinateur.
 * <p>
 * Règles :
 * <p>
 * - On possède un paquet de cartes qui représentent entre une et trois figures
 * (losange, carre ou ovale), une texture
 * (vide, hachuré ou plein) et une couleur (rouge, jaune ou bleu). La
 * cardinalité des énumérations est fixée à 3 pour cette partie 2 de la SAE
 * uniquement.
 * <p>
 * - Une table 3x3 permet de stocker 9 cartes. Au début de la partie, on dispose
 * 9 cartes sur la table, piochées sur le dessus du paquet.
 * <p>
 * - A chaque tour, le joueur doit essayer de trouver un E3C.
 * <p>
 * - Le joueur doit désigner trois cartes par leurs coordonnées.
 * - Si ces cartes forment un E3C, il gagne trois points sur son score.
 * - Si ce n'est pas un E3C, il perd 1 point sur son score.
 * <p>
 * - Les trois cartes sont remplacées par de nouvelles cartes piochées dans le
 * paquet.
 * <p>
 * - La partie se termine quand il n'y a plus de cartes dans le paquet (même
 * s'il reste des cartes sur la table).
 * <p>
 * On a donc besoin :
 * <p>
 * - D'un paquet pour stocker toutes les cartes et avoir une pioche.
 * - D'une table.
 * - De quoi stocker le score du joueur (humain ou ordinateur).
 */

public class Jeu {
    private Paquet pioche;
    private Table table;
    private int score;

    /**
     * Action :
     * - Initialise le jeu "E3Cète" en initialisant le score du joueur, le paquet et
     * la table.
     * - La table doit être remplie.
     */

    public Jeu() {
        table = new Table(3, 3);
        score = 0;
        pioche = new Paquet(Couleur.values(), 3, Figure.values(), Texture.values());
    }

    /**
     * Action : Pioche autant de cartes qu'il y a de numéros de cartes dans le
     * tableau numerosDeCartes et les place
     * aux positions correspondantes sur la table.
     */

    public void piocherEtPlacerNouvellesCartes(int[] numerosDeCartes) {
        int nbpioche = 0;
        for (int numerosDeCarte : numerosDeCartes) {
            if (numerosDeCarte != 0) {
                nbpioche++;
            }
        }
        Carte[] carte1 = pioche.piocher(nbpioche);
        Carte[] carte2 = new Carte[numerosDeCartes.length];
        int n = 0;
        for (int i = 0; i < numerosDeCartes.length; i++) {
            if (numerosDeCartes[i] != 0) {
                carte2[i] = carte1[n];
                n++;
            }
        }
        table.placerCarteTab(carte2);
    }

    public void piocherEtPlacerNouvellesCartesCoordonnees(int coord) {
        Carte[] cartes = pioche.piocher(1);
        table.placerCarte(cartes[0], coord);
    }

    /**
     * Action : Ré-initialise les données et variables du jeu afin de rejouer une
     * nouvelle partie.
     */

    public void resetJeu() {
        table = new Table(3, 3);
        score = 0;
        pioche = new Paquet(Couleur.values(), 3, Figure.values(), Texture.values());

    }

    /**
     * Résullat : Vrai si les cartes passées en paramètre forment un E3C.
     */

    public static boolean estUnE3C(Carte[] cartes) {
        if (cartes[0].getCouleur().equals(cartes[1].getCouleur()) &&
                cartes[1].getCouleur().equals(cartes[2].getCouleur()) ||
                !cartes[0].getCouleur().equals(cartes[1].getCouleur()) &&
                        !cartes[1].getCouleur().equals(cartes[2].getCouleur()) &&
                        !cartes[2].getCouleur().equals(cartes[0].getCouleur())) {
            if (cartes[0].getFigure().equals(cartes[1].getFigure()) &&
                    cartes[1].getFigure().equals(cartes[2].getFigure()) ||
                    !cartes[0].getFigure().equals(cartes[1].getFigure()) &&
                            !cartes[1].getFigure().equals(cartes[2].getFigure()) &&
                            !cartes[2].getFigure().equals(cartes[0].getFigure())) {
                if (cartes[0].getTexture().equals(cartes[1].getTexture()) &&
                        cartes[1].getTexture().equals(cartes[2].getTexture()) ||
                        !cartes[0].getTexture().equals(cartes[1].getTexture()) &&
                                !cartes[1].getTexture().equals(cartes[2].getTexture()) &&
                                !cartes[2].getTexture().equals(cartes[0].getTexture())) {
                    return cartes[0].getNbFigures() == cartes[1].getNbFigures() &&
                            cartes[1].getNbFigures() == cartes[2].getNbFigures() ||
                            cartes[0].getNbFigures() != cartes[1].getNbFigures() &&
                                    cartes[1].getNbFigures() != cartes[2].getNbFigures() &&
                                    cartes[2].getNbFigures() != cartes[0].getNbFigures();
                }
            }
        }
        return false;
    }

    /**
     * Action : Recherche un E3C parmi les cartes disposées sur la table.
     * Résullat :
     * - Si un E3C existe, un tableau contenant les numéros de cartes (de la table)
     * qui forment un E3C.
     * - Sinon, la valeur null.
     */

    public int[] chercherE3CSurTableOrdinateur() {
        for (int i = 0; i < table.getTaille() - 2; i++) {
            for (int j = i + 1; j < table.getTaille() - 1; j++) {
                for (int k = j + 1; k < table.getTaille(); k++) {
                    Carte carte1 = table.getCarte(i);
                    Carte carte2 = table.getCarte(j);
                    Carte carte3 = table.getCarte(k);

                    Carte[] cartes123 = {carte1, carte2, carte3};

                    if (estUnE3C(cartes123)) {
                        return new int[]{i, j, k};
                    }
                }
            }
        }
        return null;
    }

    /**
     * Action : Sélectionne alétoirement trois cartes sur la table.
     * La sélection ne doit pas contenir de doublons
     * Résullat : un tableau contenant les numéros des cartes sélectionnées
     * alétaoirement
     */

    public int[] selectionAleatoireDeCartesOrdinateur() {
        int[] selection = new int[3];
        for (int i = 0; i < 3; i++) {
            selection[i] = randomMinMax(i * 3, (i * 3) + 2);
        }
        return selection;
    }

    /**
     * Résullat : Vrai si la partie en cours est terminée.
     */

    public boolean partieEstTerminee() {
        return pioche.estVide();
    }

    /**
     * Action : Fait jouer un tour à un joueur humain.
     * La Table et le score du joueur sont affichés.
     * Le joueur sélectionne 3 cartes.
     * - Si c'est un E3C, il gagne trois points.
     * - Sinon, il perd un point.
     * Les cartes sélectionnées sont remplacées.
     * Divers messages d'informations doivent être affichés pour l'ergonomie.
     */

    public void jouerTourHumain() {
        System.out.println("Voici le score : " + this.score + "\nVoici la table : " + this.table);
        System.out.println("Choisissez 3 cartes (Format i,j) pour avoir un E3Cète.");
        int[] positionCarte = table.selectionnerCartesJoueur(3);
        Carte[] tabcartes = new Carte[3];
        tabcartes[0] = table.getCarte(positionCarte[0]);
        tabcartes[1] = table.getCarte(positionCarte[1]);
        tabcartes[2] = table.getCarte(positionCarte[2]);
        if (estUnE3C(tabcartes)) {
            System.out.println("Vous avez trouvé un E3C.");
            this.score = this.score + 3;
        } else {
            System.out.println("Ce n'était pas un E3C.");
            this.score = this.score - 1;
        }
        piocherEtPlacerNouvellesCartesCoordonnees(positionCarte[0]);
        piocherEtPlacerNouvellesCartesCoordonnees(positionCarte[1]);
        piocherEtPlacerNouvellesCartesCoordonnees(positionCarte[2]);

    }

    /**
     * Action : Fait jouer une partie à un joueur humain.
     * A la fin, le score final du joueur est affiché.
     */

    public void jouerHumain() {
        pioche.melanger();
        int[] n = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        piocherEtPlacerNouvellesCartes(n);
        while (!partieEstTerminee()) {
            jouerTourHumain();
            pause(500);
        }
        System.out.println("FIN" + "\nScore Final : " + this.score);
    }

    /**
     * Action : Fait jouer un tour à l'ordinateur.
     * La Table et le score de l'ordinateur sont affichés.
     * L'ordinateur sélectionne des cartes :
     * - L'ordinateur essaye toujours de trouver un E3C sur la table. S'il en trouve
     * un, il gagne donc trois points.
     * - S'il n'en trouve pas, il se rabat sur 3 cartes sélectionnées aléatoirement
     * et perd un point.
     * Les cartes sélectionnées sont remplacées.
     * Divers messages d'informations doivent être affichés pour l'ergonomie.
     */

    public void jouerTourOrdinateur() {
        System.out.println("Voici le score : " + score + "\nVoici la table : " + table);
        int[] positionCarte = chercherE3CSurTableOrdinateur();
        if (positionCarte == null) {
            positionCarte = selectionAleatoireDeCartesOrdinateur();
        }
        Carte[] tabcartes = new Carte[3];
        tabcartes[0] = table.getCarte(positionCarte[0]);
        tabcartes[1] = table.getCarte(positionCarte[1]);
        tabcartes[2] = table.getCarte(positionCarte[2]);
        if (estUnE3C(tabcartes)) {
            this.score = this.score + 3;
        } else {
            this.score = this.score - 1;
        }
        piocherEtPlacerNouvellesCartesCoordonnees(positionCarte[0]);
        piocherEtPlacerNouvellesCartesCoordonnees(positionCarte[1]);
        piocherEtPlacerNouvellesCartesCoordonnees(positionCarte[2]);
    }

    /**
     * Action : Fait jouer une partie à l'ordinateur.
     * Une pause est faite entre chaque tour (500 ms ou plus) afin de pouvoir
     * observer la progression de l'ordinateur.
     * A la fin, le score final de l'ordinateur est affiché.
     * Rappel : Ut.pause(temps) permet de faire une pause de "temps" millisecondes
     */

    public void jouerOrdinateur() {
        pioche.melanger();
        int[] n = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        piocherEtPlacerNouvellesCartes(n);
        while (!partieEstTerminee()) {
            jouerTourOrdinateur();
            pause(500);
        }
        System.out.println("FIN" + "\nScore Final : " + this.score);
    }

    /**
     * Action : Permet de lancer des parties de "E3Cète" au travers d'un menu.
     * Le menu permet au joueur de sélectionner une option parmi :
     * - humain : lance une partie avec un joueur humain
     * - ordinateur : lance une partie avec un ordinateur
     * - terminer : arrête le programme.
     * Après la fin de chaque partie, les données de jeu sont ré-initialisées et le
     * menu est ré-affiché
     * (afin de faire une nouvelle sélection).
     * Les erreurs de saisie doivent être gérées (si l'utilisateur sélectionne une
     * option inexistante).
     * Divers messages d'informations doivent être affichés pour l'ergonomie.
     */

    public void jouer() {
        System.out.println("Démarrer une partie ? Veuillez saisir une option entre 1 et 3 :");
        System.out.println("(1) = Avec un joueur Humain | (2) = Avec un Ordinateur | (3) = Quitter le jeu");
        int choix = saisirEntier();
        if (choix == 1) {
            System.out.println("Partie lancée avec un joueur Humain");
            jouerHumain();
            resetJeu();
            jouer();
        }
        if (choix == 2) {
            System.out.println("Partie lancée avec un Ordinateur");
            jouerOrdinateur();
            resetJeu();
            jouer();
        }
        if (choix == 3) {
            System.out.println("Fin de la partie");
            partieEstTerminee();
            resetJeu();
        } else if (choix != 1 && choix != 2) {
            System.out.println("Saisie non valide");
            jouer();
        }
    }

    public static void pause(int timeMilli) {
        try {
            Thread.sleep(timeMilli);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static int randomMinMax(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public static int saisirEntier() {
        String s = Table.saisirChaine();
        int lu;
        try {
            lu = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            System.err.println("Ce n'est pas un entier valide");
            return saisirEntier();
        }
        return lu;
    }
}
