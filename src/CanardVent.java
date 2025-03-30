/**
 * Classe représentant un canard de type Vent.
 * <p>
 * Cette classe hérite de la classe abstraite {@link Canard} et définit le comportement spécifique
 * pour un canard de type Vent. Lors de l'activation de sa capacité spéciale, ce canard bénéficie
 * d'une augmentation de sa vitesse d'attaque, ce qui lui permet de réaliser une double attaque
 * durant le même tour.
 * </p>
 *
 * @version 1.0
 */
public class CanardVent extends Canard {
    /**
     * Indique si la capacité spéciale est activée pour permettre une double attaque.
     */
    private boolean capaciteActive = false;

    /**
     * Constructeur de la classe CanardVent.
     * <p>
     * Initialise un canard de type Vent avec un nom, un nombre de points de vie et un nombre de points d'attaque.
     * </p>
     *
     * @param nom           le nom du canard
     * @param pointsDeVie   les points de vie initiaux du canard
     * @param pointsAttaque les points d'attaque du canard
     */
    public CanardVent(String nom, int pointsDeVie, int pointsAttaque) {
        super(nom, TypeCanard.VENT, pointsDeVie, pointsAttaque);
    }

    /**
     * Active la capacité spéciale du canard de type Vent.
     * <p>
     * Cette méthode active l'augmentation de la vitesse d'attaque, permettant ainsi une double attaque ce tour-ci.
     * Le flag {@code capaciteActive} est mis à {@code true} pour signaler que la capacité spéciale est activée.
     * </p>
     */
    @Override
    public void activerCapaciteSpeciale() {
        System.out.println(nom + " active sa capacité spéciale : augmentation de la vitesse d'attaque, double attaque ce tour.");
        capaciteActive = true;
    }

    /**
     * Permet au canard de type Vent d'attaquer un autre canard.
     * <p>
     * Si la capacité spéciale est activée, le canard réalise une première attaque normale.
     * Si l'adversaire n'est pas KO après cette attaque, une deuxième attaque est effectuée.
     * Ensuite, la capacité spéciale est réinitialisée.
     * Si la capacité n'est pas activée, une attaque normale est réalisée.
     * </p>
     *
     * @param autreCanard le canard qui subit l'attaque
     */
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
