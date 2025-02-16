import java.util.Objects;

/**
 * La classe Carte représente une carte possèdant une figure répétée un certain
 * nombre de fois avec une texture et une couleur.
 * On a besoin de connaître :
 * - La figure représentée,
 * - Le nombre de fois où la figure est représentée,
 * - La couleur de la figure,
 * - La texture de la figure.
 */
public class Carte {
    private final Figure figure;
    private final int nbFigures;
    private final Couleur couleur;
    private final Texture texture;

    /**
     * Pre-requis : nbFigures > 0
     * Action : Construit une carte contenant nbFigures "figures" qui possèdent une
     * "texture" et une "couleur"
     * Exemple : new Carte(Couleur.ROUGE, 2, Figure.OVALE, Texture.PLEIN) représente
     * une carte contenant 2 figures ovales rouge et pleines
     */

    public Carte(Couleur couleur, int nbFigures, Figure figure, Texture texture) {
        this.couleur = couleur;
        this.nbFigures = nbFigures;
        this.figure = figure;
        this.texture = texture;
    }

    /**
     * Résultat : Le nombre de fois où la figure est représentée sur la carte.
     */

    public int getNbFigures() {
        return this.nbFigures;
    }

    /**
     * Résultat : La figure représentée sur la carte.
     */

    public Figure getFigure() {
        return this.figure;
    }

    /**
     * Résultat : La couleur représentée sur les figures de la carte.
     */

    public Couleur getCouleur() {
        return this.couleur;
    }

    /**
     * Résultat : La texture représentée sur les figures de la carte.
     */

    public Texture getTexture() {
        return this.texture;
    }

    /**
     * Action : compare les attributs de "this" et de "carte"
     * afin de déterminer si this est plus petit, égal ou plus grand que "carte"
     * <p>
     * L'odre d'importance des attrbiuts est celui donné dans le constructeur (du
     * plus prioritaire au moins prioritaire) :
     * Couleur, nombre de figures, figure, texture.
     * Pour comparer les couleurs, les figures et les textures, on utilisera leur
     * position (ordinal) dans
     * leurs énumérations respectives.
     * Ainsi, pour toute paire {c1,c2} de Carte, c1 est inférieure à c2 si et
     * seulement si
     * la valeur de c1 est inférieure à celle de c2 pour la caractéristique ayant la
     * plus grande priorité
     * parmi celles pour lesquelles c1 et c2 ont des valeurs différentes.
     * <p>
     * <p>
     * Résultat :
     * 0 si "this" est égal à "carte"
     * Un nombre négatif si "this" est inférieur à "carte"
     * Un nombre strictement positif si "this "est supérieur à "carte"
     */

    public int compareTo(Carte carte) {
        if (!this.couleur.equals(carte.couleur)) {
            return this.couleur.compareTo(carte.couleur);
        } else if (this.nbFigures != carte.nbFigures) {
            return Integer.compare(this.nbFigures, carte.nbFigures);
        } else if (!this.figure.equals(carte.figure)) {
            return this.figure.compareTo(carte.figure);
        } else {
            return this.texture.compareTo(carte.texture);
        }
    }

    /**
     * Résultat :
     * Une chaîne de caractères représentant la carte de la manière suivante :
     * - Le texte est coloré selon la couleur de la carte
     * - La chaîne de caractères retournée doit faire apparaitre toutes les
     * caractériqtiques d'une carte sauf la couleur puisque le texte est affiché en
     * couleur
     * (Vous devez choisir une représentation agréable pour l'utilisateur)
     */

    @Override
    public String toString() {
        StringBuilder t = new StringBuilder(this.couleur.getNomCouleur());
        if (Objects.equals(this.texture.getNomTexture(), "vide")) {
            if (Objects.equals(this.figure.getNomFigure(), "triangle"))
                t.append("△".repeat(java.lang.Math.max(0, this.nbFigures)));
            if (Objects.equals(this.figure.getNomFigure(), "carré"))
                t.append("□".repeat(java.lang.Math.max(0, this.nbFigures)));
            if (Objects.equals(this.figure.getNomFigure(), "ovale"))
                t.append("◯".repeat(java.lang.Math.max(0, this.nbFigures)));
        }
        if (Objects.equals(this.texture.getNomTexture(), "hachurée")) {
            if (Objects.equals(this.figure.getNomFigure(), "triangle"))
                t.append("◬".repeat(java.lang.Math.max(0, this.nbFigures)));
            if (Objects.equals(this.figure.getNomFigure(), "carré"))
                t.append("▧".repeat(java.lang.Math.max(0, this.nbFigures)));
            if (Objects.equals(this.figure.getNomFigure(), "ovale"))
                t.append("◍".repeat(java.lang.Math.max(0, this.nbFigures)));
        }
        if (Objects.equals(this.texture.getNomTexture(), "pleine")) {
            if (Objects.equals(this.figure.getNomFigure(), "triangle"))
                t.append("▲".repeat(java.lang.Math.max(0, this.nbFigures)));
            if (Objects.equals(this.figure.getNomFigure(), "carré"))
                t.append("■".repeat(java.lang.Math.max(0, this.nbFigures)));
            if (Objects.equals(this.figure.getNomFigure(), "ovale"))
                t.append("●".repeat(java.lang.Math.max(0, this.nbFigures)));
        }
        t.append("\u001B[0m");
        return t.toString();
    }
}
