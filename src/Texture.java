public enum Texture {
    Vide("vide"),
    Hachure("hachurée"),
    Pleine("pleine");
    /*
     * Pois("à pois"),
     * Rayures("rayures"),
     * Points("points"),
     * Zigzag("zigzag"),
     * Damier("damier"),
     * Paillette("Paillette");
     */

    private String nomTexture;

    private Texture(String a) {
        this.nomTexture = a;
    }

    /**
     * Représente la texture d'une Carte : pleine , à pois...
     */
    public String getNomTexture() {
        return this.nomTexture;
    }
}
