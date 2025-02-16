public class Math {

    public static void main(String[] args) {
        Math jeu = new Math();
        int nbEssai = 10000;
        int n = (jeu.proba3CR(nbEssai) * 100) / nbEssai;
        System.out.println("Probabilité Tables  3 Cartes rouges : " + n + "%");
        int x = (jeu.probaE3Cete(nbEssai) * 100) / nbEssai;
        System.out.println("Probabilité E3Cète : " + x + "%");
    }

    public int proba3CR(int nbEssai) {
        int r = 0;
        Paquet p = new Paquet(Couleur.values(), 3, Figure.values(), Texture.values());
        p.melanger();
        for (int n = 0; n < nbEssai; n++) {
            Carte[] selection = new Carte[9];
            // Selection aléatoire :
            for (int i = 0; i < 9; i++) {
                selection[i] = p.getCarte(randomMinMax(0, 80));
            }
            // 3CR ?
            if (est3CR(selection)) {
                r++;
            }
        }
        return r;
    }

    public int probaE3Cete(int nbEssai) {
        int r = 0;
        Paquet p = new Paquet(Couleur.values(), 3, Figure.values(), Texture.values());
        Table table = new Table(3, 3);
        p.melanger();
        for (int n = 0; n < nbEssai; n++) {
            // Selection aléatoire :
            for (int i = 0; i < 9; i++) {
                table.placerCarte(p.getCarte(randomMinMax(0, 80)), i);
            }
            // E3C ?
            if (chercherE3C(table)) {
                r++;
            }
        }
        return r;
    }

    private boolean est3CR(Carte[] Cartes) {
        int r = 0;
        for (int i = 0; i < 9; i++) {
            if (Cartes[i].getCouleur().estRouge()) {
                r++;
            }
        }
        return r == 3;
    }

    private boolean chercherE3C(Table table) {
        for (int i = 0; i < table.getTaille() - 2; i++) {
            for (int j = i + 1; j < table.getTaille() - 1; j++) {
                for (int k = j + 1; k < table.getTaille(); k++) {
                    Carte carte1 = table.getCarte(i);
                    Carte carte2 = table.getCarte(j);
                    Carte carte3 = table.getCarte(k);

                    Carte[] cartes123 = {carte1, carte2, carte3};

                    if (estUnE3C(cartes123)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean estUnE3C(Carte[] cartes) {
        return Jeu.estUnE3C(cartes);
    }

    public static int randomMinMax(int min, int max) {
        return Jeu.randomMinMax(min, max);
    }
}
