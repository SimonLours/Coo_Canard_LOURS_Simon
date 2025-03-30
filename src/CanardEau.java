public class CanardEau extends Canard {
    public CanardEau(String nom, int pointsDeVie, int pointsAttaque) {
        super(nom, TypeCanard.EAU, pointsDeVie, pointsAttaque);
    }

    @Override
    public void activerCapaciteSpeciale() {
        System.out.println(nom + " active sa capacité spéciale : régénération de 20 PV.");
        pointsDeVie += 20;
    }
}
