public enum TypeCanard {
    EAU, FEU, GLACE, VENT;

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
