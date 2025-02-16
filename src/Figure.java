public enum Figure {
    Triangle("triangle"),
    Carre("carré"),
    Ovale("ovale");
    /*
     * Triangle("triangle"),
     * Cercle("cercle"),
     * Rectangle("rectangle"),
     * Etoile("étoile"),
     * Coeur("coeur"),
     * Croix("croix");
     */

    private String nomFig;

    private Figure(String a) {
        this.nomFig = a;
    }

    /**
     * Représente la figure (forme) d'une Carte : ovale , triangle ...
     */
    public String getNomFigure() {
        return this.nomFig;
    }
}
