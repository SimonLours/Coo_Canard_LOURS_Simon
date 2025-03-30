/**
 * Classe représentant un canard de type Glace.
 * <p>
 * Cette classe hérite de la classe abstraite {@link Canard} et définit le comportement
 * spécifique pour un canard de type Glace. Lors de l'activation de sa capacité spéciale,
 * ce canard "gèle" l'adversaire, ce qui devrait le faire perdre un tour.
 * Dans une implémentation complète, on pourrait définir un flag sur l'adversaire pour indiquer qu'il est gelé.
 * </p>
 *
 * @version 1.0
 */
public class CanardGlace extends Canard {

    /**
     * Constructeur de la classe CanardGlace.
     * <p>
     * Initialise un canard de type Glace avec un nom, un nombre de points de vie et un nombre de points d'attaque.
     * </p>
     *
     * @param nom           le nom du canard
     * @param pointsDeVie   les points de vie initiaux du canard
     * @param pointsAttaque les points d'attaque du canard
     */
    public CanardGlace(String nom, int pointsDeVie, int pointsAttaque) {
        super(nom, TypeCanard.GLACE, pointsDeVie, pointsAttaque);
    }

    /**
     * Active la capacité spéciale du canard de type Glace.
     * <p>
     * Cette méthode simule le gel de l'adversaire, ce qui le ferait perdre un tour.
     * Dans une version complète, un flag pourrait être défini sur l'adversaire pour signaler son état gelé.
     * </p>
     */
    @Override
    public void activerCapaciteSpeciale() {
        System.out.println(nom + " active sa capacité spéciale : gèle l'adversaire, le faisant perdre un tour.");
        // Implémentation possible : définir un flag sur l'adversaire pour indiquer qu'il est gelé.
    }
}
