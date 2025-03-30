public class CanardFeu extends Canard {
    // Indique si la capacité spéciale est activée pour ajouter des dégâts supplémentaires sur la prochaine attaque
    private boolean capaciteActive = false;

    public CanardFeu(String nom, int pointsDeVie, int pointsAttaque) {
        super(nom, TypeCanard.FEU, pointsDeVie, pointsAttaque);
    }

    @Override
    public void activerCapaciteSpeciale() {
        System.out.println(nom + " active sa capacité spéciale : attaque enflammée, infligeant 10 dégâts supplémentaires lors de sa prochaine attaque.");
        capaciteActive = true;
    }

    @Override
    public void attaquer(Canard autreCanard) {
        double multiplicateur = TypeCanard.getMultiplicateur(this.type, autreCanard.getType());
        int degats = (int)(pointsAttaque * multiplicateur);
        if (capaciteActive) {
            degats += 10; // Dégâts supplémentaires
            capaciteActive = false; // Réinitialisation après utilisation
        }
        System.out.println(this.nom + " attaque " + autreCanard.getNom() +
                " et inflige " + degats + " points de dégâts.");
        autreCanard.subirDegats(degats);
    }
}
