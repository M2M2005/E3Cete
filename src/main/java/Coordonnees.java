public class Coordonnees {
    /**
     * La classe Coordonnees représente les coordonnées (i,j) d'une Carte sur la
     * Table
     * ou i représenta la ligne et j la colonne
     * Cette classe est utilisée uniquement pour intéragir avec l'utilisateur
     */

    private final int l;
    private final int c;

    /**
     * Prérequis : x, y>=0
     * Action : Construit des Coordonnées ayant x comme numéro de ligne et y comme
     * numéro de colonne
     */
    public Coordonnees(int x, int y) {
        this.l = x;
        this.c = y;
    }

    /**
     * Pre-requis : input est sous la forme suivante : int,int
     * Action : Construit des Coordonnées ayant x comme numéro de ligne et y comme
     * numéro de colonne
     */
    public Coordonnees(String input) {
        String[] splited = input.split(",");
        // splitted est un tableau de String qui contient les sous chaines de caracteres
        // contenues dans input et séparées par ','
        this.l = Integer.parseInt(splited[0]);
        this.c = Integer.parseInt(splited[1]);
    }

    /**
     * Action : Retourne le numéro de la ligne
     */
    public int getLigne() {
        return this.l;
    }

    /**
     * Action : Retourne le numéro de la colonne
     */
    public int getColonne() {
        return this.c;
    }

    /**
     * Pre-requis : aucun
     * Action : Retourne vrai si la variable input est dans un format valide à
     * savoir int,int
     * Aide : On peut utiliser Ut.estNombre pour vérifier qu'une chaîne de
     * caractères est bien un nombre.
     */
    public static boolean formatEstValide(String input) {
        String[] splited = input.split(",");
        return splited.length == 2 && estNombre(splited[0]) && estNombre(splited[1]);
    }

    public static boolean estNombre(String chaine) {
        try {
            Integer.parseInt(chaine);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
