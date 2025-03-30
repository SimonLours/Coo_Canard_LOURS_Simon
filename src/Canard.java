/**
 * Classe abstraite représentant un canard dans le simulateur de combat.
 * <p>
 * Un canard possède un nom, un type (défini par l'énumération {@link TypeCanard}),
 * des points de vie et des points d'attaque. Il peut attaquer un autre canard,
 * subir des dégâts et activer une capacité spéciale propre à son type.
 * </p>
 *
 * @version 1.0
 */
public abstract class Canard {
    protected String nom;
    protected TypeCanard type;
    protected int pointsDeVie;
    protected int pointsAttaque;

    /**
     * Constructeur de la classe Canard.
     *
     * @param nom           le nom du canard
     * @param type          le type du canard (par exemple, EAU, FEU, GLACE, VENT)
     * @param pointsDeVie   les points de vie initiaux du canard
     * @param pointsAttaque les points d'attaque du canard
     */
    public Canard(String nom, TypeCanard type, int pointsDeVie, int pointsAttaque) {
        this.nom = nom;
        this.type = type;
        this.pointsDeVie = pointsDeVie;
        this.pointsAttaque = pointsAttaque;
    }

    /**
     * Retourne le nom du canard.
     *
     * @return le nom du canard
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne les points de vie actuels du canard.
     *
     * @return les points de vie
     */
    public int getPointsDeVie() {
        return pointsDeVie;
    }

    /**
     * Retourne les points d'attaque du canard.
     *
     * @return les points d'attaque
     */
    public int getPointsAttaque() {
        return pointsAttaque;
    }

    /**
     * Retourne le type du canard.
     *
     * @return le type du canard
     */
    public TypeCanard getType() {
        return type;
    }

    /**
     * Permet au canard d'attaquer un autre canard.
     * <p>
     * Cette méthode calcule les dégâts en fonction des points d'attaque du canard et
     * du multiplicateur obtenu grâce aux interactions de type via {@link TypeCanard#getMultiplicateur(TypeCanard, TypeCanard)}.
     * Une attaque critique (10 % de probabilité) double les dégâts infligés.
     * </p>
     *
     * @param autreCanard le canard qui subit l'attaque
     */
    public void attaquer(Canard autreCanard) {
        double multiplicateur = TypeCanard.getMultiplicateur(this.type, autreCanard.getType());
        int degats = (int)(pointsAttaque * multiplicateur);
        // Probabilité de 10 % pour une attaque critique
        if (Math.random() < 0.1) {
            System.out.println(this.nom + " réalise une attaque critique !");
            degats *= 2;
        }
        System.out.println(this.nom + " attaque " + autreCanard.getNom() +
                " et inflige " + degats + " points de dégâts.");
        autreCanard.subirDegats(degats);
    }

    /**
     * Réduit les points de vie du canard en fonction des dégâts subis.
     * <p>
     * Si les points de vie deviennent négatifs, ils sont ramenés à zéro.
     * </p>
     *
     * @param degats le nombre de points de dégâts à soustraire
     */
    public void subirDegats(int degats) {
        pointsDeVie -= degats;
        if (pointsDeVie < 0) {
            pointsDeVie = 0;
        }
        System.out.println(nom + " subit " + degats + " dégâts, restant à " +
                pointsDeVie + " PV.");
    }

    /**
     * Vérifie si le canard est hors de combat.
     *
     * @return {@code true} si les points de vie sont inférieurs ou égaux à 0, sinon {@code false}
     */
    public boolean estKO() {
        return pointsDeVie <= 0;
    }

    /**
     * Méthode abstraite à implémenter par chaque classe fille pour activer
     * la capacité spéciale propre au type du canard.
     */
    public abstract void activerCapaciteSpeciale();
}
