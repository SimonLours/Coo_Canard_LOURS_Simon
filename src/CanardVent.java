public class CanardVent extends Canard {
    // Indique si la capacité spéciale est activée pour permettre une double attaque
    private boolean capaciteActive = false;

    public CanardVent(String nom, int pointsDeVie, int pointsAttaque) {
        super(nom, TypeCanard.VENT, pointsDeVie, pointsAttaque);
    }

    @Override
    public void activerCapaciteSpeciale() {
        System.out.println(nom + " active sa capacité spéciale : augmentation de la vitesse d'attaque, double attaque ce tour.");
        capaciteActive = true;
    }

    @Override
    public void attaquer(Canard autreCanard) {
        if (capaciteActive) {
            System.out.println(nom + " profite de sa vitesse d'attaque accrue !");
            // Première attaque
            super.attaquer(autreCanard);
            // Si l'adversaire n'est pas KO après la première attaque, on attaque une deuxième fois
            if (!autreCanard.estKO()) {
                super.attaquer(autreCanard);
            }
            capaciteActive = false; // Réinitialisation de la capacité après utilisation
        } else {
            // Comportement normal
            super.attaquer(autreCanard);
        }
    }
}
