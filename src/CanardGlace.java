public class CanardGlace extends Canard {
    public CanardGlace(String nom, int pointsDeVie, int pointsAttaque) {
        super(nom, TypeCanard.GLACE, pointsDeVie, pointsAttaque);
    }

    @Override
    public void activerCapaciteSpeciale() {
        System.out.println(nom + " active sa capacité spéciale : gèle l'adversaire, le faisant perdre un tour.");
        // Dans une implémentation complète, on pourrait définir un flag sur l'adversaire pour indiquer qu'il est gelé.
    }
}
