import javax.print.DocFlavor.STRING;

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
    private Figure figure;
    private int nbFigures;
    private Couleur couleur;
    private Texture texture;

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
     *
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
     *
     *
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
        String t = this.couleur.getNomCouleur();
        if (this.texture.getNomTexture() == "vide") {
            if (this.figure.getNomFigure() == "triangle")
                for (int i = 0; i < this.nbFigures; i++) {
                    t += "△";
                }
            if (this.figure.getNomFigure() == "carré")
                for (int i = 0; i < this.nbFigures; i++) {
                    t += "□";
                }
            if (this.figure.getNomFigure() == "ovale")
                for (int i = 0; i < this.nbFigures; i++) {
                    t += "◯";
                }
        }
        if (this.texture.getNomTexture() == "hachurée") {
            if (this.figure.getNomFigure() == "triangle")
                for (int i = 0; i < this.nbFigures; i++) {
                    t += "◬";
                }
            if (this.figure.getNomFigure() == "carré")
                for (int i = 0; i < this.nbFigures; i++) {
                    t += "▧";
                }
            if (this.figure.getNomFigure() == "ovale")
                for (int i = 0; i < this.nbFigures; i++) {
                    t += "◍";
                }
        }
        if (this.texture.getNomTexture() == "pleine") {
            if (this.figure.getNomFigure() == "triangle")
                for (int i = 0; i < this.nbFigures; i++) {
                    t += "▲";
                }
            if (this.figure.getNomFigure() == "carré")
                for (int i = 0; i < this.nbFigures; i++) {
                    t += "■";
                }
            if (this.figure.getNomFigure() == "ovale")
                for (int i = 0; i < this.nbFigures; i++) {
                    t += "●";
                }
        }
        t += "\u001B[0m";
        return t;
    }
}
