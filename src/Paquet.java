import java.util.Arrays;

/**
 * La classe Paquet représente un paquet de cartes.
 * Les cartes sont stockées dans un tableau fixe et un indice (entier) permet de
 * connaître le nombre de cartes
 * restantes (non piochées) dans le paquet. Quand on pioche, cet indice diminue.
 * Dans les traitements, on considère alors seulement les cartes se trouvant
 * entre 0 et cet indice (exclus).
 * Par conséquent, on ne supprime pas vraiment les cartes piochées, on les
 * ignore juste.
 * On a donc besoin de connaître :
 * - Le tableau stockant les cartes.
 * - Le nombre de cartes restantes dans le paquet.
 */
public class Paquet {
    private Carte[] paquet;
    private int nbCartesRestantes;

    private int operationsSelection;
    private int operationsBulles;
    private int operationsInsertion;

    /**
     * Pre-requis : figures.length > 0, couleurs.length > 0, textures.length > 0,
     * nbFiguresMax > 0
     *
     * Action : Construit un paquet de cartes mélangé contenant toutes les cartes
     * incluant 1 à nbFiguresMax figures
     * qu'il est possibles de créer en combinant les différentes figures, couleurs
     * et textures précisées en paramètre.
     * Bien sûr, il n'y a pas de doublons.
     *
     * Exemple :
     * - couleurs = [Rouge, Jaune]
     * - nbFiguresMax = 2
     * - figures = [A, B]
     * - textures = [S, T]
     * Génère un paquet (mélangé) avec toutes les combinaisons de cartes possibles
     * pour ces caractéristiques : 1-A-S (rouge), 1-A-T (rouge), etc...
     */

    public Paquet(Couleur[] couleurs, int nbFiguresMax, Figure[] figures, Texture[] textures) {
        int nbTotalCartes = getNombreCartesAGenerer(couleurs, nbFiguresMax, figures, textures);
        this.nbCartesRestantes = nbTotalCartes;
        paquet = new Carte[nbTotalCartes];
        int i = 0; /// numéro de la carte
        for (int nbFigures = 1; nbFigures <= nbFiguresMax; nbFigures++) {
            for (Couleur couleur : couleurs) {
                for (Figure figure : figures) {
                    for (Texture texture : textures) {
                        paquet[i++] = new Carte(couleur, nbFigures, figure, texture);
                    }
                }
            }
        }
        melanger();
    }

    /**
     * Action : Construit un paquet par recopie en copiant les données du paquet
     * passé en paramètre.
     */

    public Paquet(Paquet paquet) {
        this.paquet = paquet.paquet;
        this.nbCartesRestantes = paquet.nbCartesRestantes;
        for (int i = 0; i < this.paquet.length; i++) {
            Carte temporaire = this.paquet[i];
            this.paquet[i] = paquet.paquet[i];
            paquet.paquet[i] = temporaire;
        }
    }

    /**
     * Pre-requis : figures.length > 0, couleurs.length > 0, textures.length > 0,
     * nbFiguresMax > 0
     *
     * Resultat : Le nombre de cartes uniques contenant entre 1 et nbFiguresMax
     * figures qu'il est possible de générer en
     * combinant les différentes figures, couleurs et textures précisées en
     * paramètre.
     */

    public static int getNombreCartesAGenerer(Couleur[] couleurs, int nbFiguresMax, Figure[] figures,
            Texture[] textures) {
        int nbCartes = couleurs.length * nbFiguresMax * figures.length * textures.length;
        return nbCartes;
    }

    /**
     * Action : Mélange aléatoirement les cartes restantes dans le paquet.
     * Attention, on rappelle que le paquet peut aussi contenir des cartes déjà
     * piochées qu'il faut ignorer.
     */

    public void melanger() {
        for (int i = 0; i < paquet.length; i++) {
            int j = Ut.randomMinMax(0, paquet.length - 1);
            Carte temporaire = paquet[i];
            paquet[i] = paquet[j];
            paquet[j] = temporaire;
        }
    }

    /**
     * Action : Calcule et renvoie un paquet trié à partir du paquet courant (this)
     * selon la méthode du tri selection.
     * Le tri est effectué à partir des données du paquet courant (this) mais
     * celui-ci ne doit pas être modifié !
     * Une nouvelle instance du paquet est traitée et renvoyée.
     * On rappelle que le paquet peut aussi contenir des cartes déjà piochées qu'il
     * faut ignorer (voir partie 2 de la SAE).
     * Le tri doit fonctionner que le Paquet soit plein ou non.
     * https://www.youtube.com/watch?v=Ns4TPTC8whw&t=2s vidéo explicative
     */

    public Paquet trierSelection() {
        Paquet paquetTrier = new Paquet(this);
        for (int i = 0; i < paquetTrier.paquet.length - 1; i++) {
            for (int n = i + 1; n < paquetTrier.paquet.length; n++) {
                if (paquetTrier.paquet[i].compareTo(paquetTrier.paquet[n]) >= 1) {
                    Carte temporaire = paquetTrier.paquet[i];
                    paquetTrier.paquet[i] = paquetTrier.paquet[n];
                    paquetTrier.paquet[n] = temporaire;
                }
                operationsSelection++;
            }
        }
        return paquetTrier;
    }

    /**
     * Action : Calcule et renvoie un paquet trié à partir du paquet courant (this)
     * selon la méthode du tri bulles.
     * Le tri est effectué à partir des données du paquet courant (this) mais
     * celui-ci ne doit pas être modifié !
     * Une nouvelle instance du paquet est traitée et renvoyée.
     * On rappelle que le paquet peut aussi contenir des cartes déjà piochées qu'il
     * faut ignorer (voir partie 2 de la SAE).
     * Le tri doit fonctionner que le Paquet soit plein ou non.
     * https://www.youtube.com/watch?v=lyZQPjUT5B4&embeds_referring_euri=https%3A%2F%2Fwww.developpez.com%2F&source_ve_path=Mjg2NjY&feature=emb_logo
     * vidéo explicative
     */

    public Paquet trierBulles() {
        Paquet paquetTrier = new Paquet(this);
        int n = paquetTrier.paquet.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (paquetTrier.paquet[j].compareTo(paquetTrier.paquet[j + 1]) > 0) {
                    Carte temporaire = paquetTrier.paquet[j];
                    paquetTrier.paquet[j] = paquetTrier.paquet[j + 1];
                    paquetTrier.paquet[j + 1] = temporaire;
                }
                operationsBulles++;
            }
        }
        return paquetTrier;
    }

    /**
     * Action : Calcule et renvoie un paquet trié à partir du paquet courant (this)
     * selon la méthode du tri insertion.
     * Le tri est effectué à partir des données du paquet courant (this) mais
     * celui-ci ne doit pas être modifié !
     * Une nouvelle instance du paquet est traitée et renvoyée.
     * On rappelle que le paquet peut aussi contenir des cartes déjà piochées qu'il
     * faut ignorer (voir partie 2 de la SAE).
     * Le tri doit fonctionner que le Paquet soit plein ou non.
     * https://www.youtube.com/watch?v=ROalU379l3U&t=1s : vidéo explicative
     */

    public Paquet trierInsertion() {
        Paquet paquetTrier = new Paquet(this);
        int n = paquetTrier.paquet.length;
        for (int i = 1; i < n; i++) {
            Carte tempo = paquetTrier.paquet[i];
            int j = i - 1;
            while (j >= 0 && paquetTrier.paquet[j].compareTo(tempo) > 0) {
                paquetTrier.paquet[j + 1] = paquetTrier.paquet[j];
                j = j - 1;
                operationsInsertion++;
            }
            paquetTrier.paquet[j + 1] = tempo;
        }
        return paquetTrier;
    }

    /**
     * Action : Permet de tester les différents tris.
     * On doit s'assurer que chaque tri permette effectivement de trier un paquet
     * mélangé.
     * Des messages d'informations devront être affichés.
     * La méthode est "static" et ne s'effectue donc pas sur la paquet courant
     * "this".
     */
    public static void testTris() {
        int n = 2;
        Paquet p = new Paquet(Arrays.copyOfRange(Couleur.values(), 0, n), n, Arrays.copyOfRange(Figure.values(), 0, n),
                Arrays.copyOfRange(Texture.values(), 0, n));

        Paquet triBulles = p.trierBulles();
        Paquet triInsertion = p.trierInsertion();
        Paquet triSelection = p.trierSelection();

        System.out.println("Paquet triBulles : " + triBulles);
        System.out.println("Paquet triInsertion : " + triInsertion);
        System.out.println("Paquet triSelection : " + triSelection);

        if (Arrays.equals(triBulles.paquet, triInsertion.paquet)) {
            System.out.println("TrierBulles égale à TrierIntersection");
        } else {
            System.out.println("Non égaux");
        }
        if (Arrays.equals(triInsertion.paquet, triSelection.paquet)) {
            System.out.println("trierInsertion égale à trierSelection");
        } else {
            System.out.println("Non égaux");
        }
        if (Arrays.equals(triSelection.paquet, triBulles.paquet)) {
            System.out.println("trierSelection égale à TrierBulles");
        } else {
            System.out.println("Non égaux");
        }

        System.out.println("Nombre Total Carte : " + p.nbCartesRestantes);
        long tempsExec1 = Ut.getTempsExecution(p::trierSelection);
        System.out.println("Temps d'exécution du tri par Sélection : " + tempsExec1 + " nanosecondes");
        System.out.println("Nombre Opérations du tri par Sélection : " + p.operationsSelection + " opération" + "\n");
        p.melanger();
        long tempsExec2 = Ut.getTempsExecution(p::trierBulles);
        System.out.println("Temps d'exécution du tri par Bulles : " + tempsExec2 + " nanosecondes");
        System.out.println("Nombre Opérations du tri par Bulles : " + p.operationsBulles + " opération" + "\n");
        p.melanger();
        long tempsExec3 = Ut.getTempsExecution(p::trierInsertion);
        System.out.println("Temps d'exécution du tri par Insertion : " + tempsExec3 + " nanosecondes");
        System.out.println("Nombre Opérations du tri par Insertion : " + p.operationsInsertion + " opération" + "\n");
    }

    public static void testMelange() {
        int n = 3;
        Paquet p = new Paquet(Arrays.copyOfRange(Couleur.values(), 0, n), n, Arrays.copyOfRange(Figure.values(), 0, n),
                Arrays.copyOfRange(Texture.values(), 0, n));
        System.out.println("Paquet : " + p);
        p.melanger();
        System.out.println("Paquet melanger : " + p);
        Paquet triBulles = p.trierBulles();
        System.out.println("Paquet melanger puis trier : " + triBulles);
    }

    /**
     * Pre-requis : 0 < nbCartes <= nombre de cartes restantes dans le paquet.
     *
     * Action : Pioche nbCartes Cartes au dessus du Paquet this (et met à jour son
     * état).
     *
     * Résultat : Un tableau contenant les nbCartes Cartes piochees dans le Paquet.
     *
     * Exemple :
     * Contenu paquet : [A,B,C,D,E,F,G]
     * Nombre de cartes restantes : 5. On considère donc seulement les cartes de 0 à
     * 4.
     *
     * piocher(3)
     * Contenu paquet : [A,B,C,D,E,F,G]
     * Nombre de cartes restantes : 2
     * Renvoie [E,D,C]
     */

    public Carte[] piocher(int nbCartes) {
        Carte[] cp = new Carte[nbCartes];
        int hautPioche = nbCartesRestantes - 1;
        for (int i = hautPioche; i > hautPioche - nbCartes; i--) {
            cp[hautPioche - i] = paquet[i];
            nbCartesRestantes--;
        }
        return cp;
    }

    /**
     * Résultat : Vrai s'il reste assez de cartes dans le paquet pour piocher
     * nbCartes.
     */

    public boolean peutPiocher(int nbCartes) {
        return nbCartes <= this.nbCartesRestantes;
    }

    /**
     * Résultat : Vrai s'il ne reste plus aucune cartes dans le paquet.
     */

    public boolean estVide() {
        return this.nbCartesRestantes == 0;
    }

    /**
     * Résultat : Une chaîne de caractères représentant le paquet sous la forme d'un
     * tableau
     * [X, Y, Z] de couleur A représentant les cartes restantes dans le paquet.
     * X = nb / Y = Figue / Z = Texture / A = Couleur
     * Exemple :
     * Contenu paquet : 1-O-P (rouge), 2-C-V (jaune), 3-L-P (jaune), 3-L-P (rouge),
     * 1-L-V (bleu)
     * Nombre de cartes restantes : 3
     * Retourne [1-O-P, 2-C-V, 3-L-P] (et chaque représentation d'une carte est
     * coloré selon la couleur de la carte...)
     */

    @Override
    public String toString() {
        String p = "Contenu paquet : ";
        for (int i = 0; i < this.paquet.length; i++) {
            p += this.paquet[i].toString();
            if (i < this.paquet.length - 1) {
                p += "\u001B[0m, ";
            }
        }
        p += "\n\u001B[0mNombre de cartes restantes : " + this.nbCartesRestantes;
        return p;
    }

    public Carte getCarte(int n) {
        return paquet[n];
    }
}
