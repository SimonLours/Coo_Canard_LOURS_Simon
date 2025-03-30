/**
 * Enumération représentant les types de canards.
 * <p>
 * Les types disponibles sont : EAU, FEU, GLACE et VENT.
 * Cette énumération fournit également une méthode statique pour calculer le multiplicateur de dégâts
 * lors d'une attaque en fonction des interactions entre les types des canards.
 * </p>
 *
 * @version 1.0
 */
public enum TypeCanard {
    EAU, FEU, GLACE, VENT;

    /**
     * Calcule le multiplicateur de dégâts basé sur les types de l'attaquant et de la cible.
     * <p>
     * La méthode retourne :
     * <ul>
     *   <li>1.5 si l'attaquant a l'avantage (ex : EAU > FEU, FEU > GLACE, GLACE > VENT, VENT > EAU)</li>
     *   <li>0.5 si l'attaquant est désavantagé (ex : FEU < EAU, GLACE < FEU, VENT < GLACE, EAU < VENT)</li>
     *   <li>1.0 dans tous les autres cas (attaque neutre)</li>
     * </ul>
     * </p>
     *
     * @param attaquant le type du canard attaquant
     * @param cible     le type du canard cible
     * @return le multiplicateur de dégâts à appliquer à l'attaque
     */
    public static double getMultiplicateur(TypeCanard attaquant, TypeCanard cible) {
        // Cas où l'attaquant est fort contre la cible
        if (attaquant == EAU && cible == FEU) return 1.5;
        if (attaquant == FEU && cible == GLACE) return 1.5;
        if (attaquant == GLACE && cible == VENT) return 1.5;
        if (attaquant == VENT && cible == EAU) return 1.5;

        // Cas où l'attaquant est désavantagé
        if (attaquant == FEU && cible == EAU) return 0.5;
        if (attaquant == GLACE && cible == FEU) return 0.5;
        if (attaquant == VENT && cible == GLACE) return 0.5;
        if (attaquant == EAU && cible == VENT) return 0.5;

        // Cas neutre
        return 1.0;
    }
}
