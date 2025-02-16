import java.util.Objects;

public enum Couleur {

    Jaune("\u001B[33m"),
    Rouge("\u001B[31m"),
    Bleu("\u001B[34m");
    /*
     * Vert("\u001B[32m"),
     * Cyan("\u001B[36m"),
     * Magenta("\u001B[35m"),
     * Blanc("\u001B[37m"),
     * Noir("\u001B[30m"),
     * Gris("\u001B[30m;1m");
     */

    private final String nomCouleur;

    /**
     * Représente la couleur d'une Carte : jaune, rouge ...
     * En plus de donner une liste énumérative des couleurs possibles,
     * cette enumération doit permettre à la méthode toString d'une Carte de
     * réaliser un affichage en couleur.
     */
    Couleur(String a) {
        this.nomCouleur = a;
    }

    public String getNomCouleur() {
        return this.nomCouleur;
    }

    public boolean estRouge() {
        return Objects.equals(this.nomCouleur, "\u001B[31m");
    }
}
