/**
 * Classe représentant un canard de type Feu.
 * <p>
 * Cette classe hérite de la classe abstraite {@link Canard} et définit le comportement spécifique
 * pour un canard de type Feu. Lors de l'activation de sa capacité spéciale, ce canard prépare une attaque enflammée
 * qui ajoutera 10 dégâts supplémentaires lors de sa prochaine attaque.
 * </p>
 *
 * @version 1.0
 */
public class CanardFeu extends Canard {
    /**
     * Indique si la capacité spéciale est activée pour ajouter des dégâts supplémentaires lors de la prochaine attaque.
     */
    private boolean capaciteActive = false;

    /**
     * Constructeur de la classe CanardFeu.
     * <p>
     * Initialise un canard de type Feu avec un nom, un nombre de points de vie et un nombre de points d'attaque.
     * </p>
     *
     * @param nom           le nom du canard
     * @param pointsDeVie   les points de vie initiaux du canard
     * @param pointsAttaque les points d'attaque du canard
     */
    public CanardFeu(String nom, int pointsDeVie, int pointsAttaque) {
        super(nom, TypeCanard.FEU, pointsDeVie, pointsAttaque);
    }

    /**
     * Active la capacité spéciale du canard de type Feu.
     * <p>
     * Cette méthode active l'attaque enflammée qui ajoutera 10 dégâts supplémentaires
     * lors de la prochaine attaque. Le flag {@code capaciteActive} est alors mis à {@code true}.
     * </p>
     */
    @Override
    public void activerCapaciteSpeciale() {
        System.out.println(nom + " active sa capacité spéciale : attaque enflammée, infligeant 10 dégâts supplémentaires lors de sa prochaine attaque.");
        capaciteActive = true;
    }

    /**
     * Permet au canard de type Feu d'attaquer un autre canard.
     * <p>
     * La méthode calcule les dégâts en multipliant les points d'attaque par le multiplicateur obtenu via
     * {@link TypeCanard#getMultiplicateur(TypeCanard, TypeCanard)}. Si la capacité spéciale a été activée,
     * 10 dégâts supplémentaires sont ajoutés, puis la capacité est réinitialisée.
     * </p>
     *
     * @param autreCanard le canard qui subit l'attaque
     */
    @Override
    public void attaquer(Canard autreCanard) {
        double multiplicateur = TypeCanard.getMultiplicateur(this.type, autreCanard.getType());
        int degats = (int)(pointsAttaque * multiplicateur);
        if (capaciteActive) {
            degats += 10; // Ajout de dégâts supplémentaires
            capaciteActive = false; // Réinitialisation de la capacité après utilisation
        }
        System.out.println(this.nom + " attaque " + autreCanard.getNom() +
                " et inflige " + degats + " points de dégâts.");
        autreCanard.subirDegats(degats);
    }
}
