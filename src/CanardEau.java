/**
 * Classe représentant un canard de type Eau.
 * <p>
 * Cette classe hérite de la classe abstraite {@link Canard} et définit le comportement
 * spécifique pour un canard de type Eau, notamment la régénération de 20 points de vie
 * via sa capacité spéciale.
 * </p>
 *
 * @version 1.0
 */
public class CanardEau extends Canard {

    /**
     * Constructeur de la classe CanardEau.
     * <p>
     * Initialise un canard de type Eau avec un nom, un nombre de points de vie et un nombre de points d'attaque.
     * </p>
     *
     * @param nom           le nom du canard
     * @param pointsDeVie   les points de vie initiaux du canard
     * @param pointsAttaque les points d'attaque du canard
     */
    public CanardEau(String nom, int pointsDeVie, int pointsAttaque) {
        super(nom, TypeCanard.EAU, pointsDeVie, pointsAttaque);
    }

    /**
     * Active la capacité spéciale du canard de type Eau.
     * <p>
     * Cette capacité permet de régénérer 20 points de vie.
     * </p>
     */
    @Override
    public void activerCapaciteSpeciale() {
        System.out.println(nom + " active sa capacité spéciale : régénération de 20 PV.");
        pointsDeVie += 20;
    }
}
