public abstract class Canard {
    protected String nom;
    protected TypeCanard type;
    protected int pointsDeVie;
    protected int pointsAttaque;

    public Canard(String nom, TypeCanard type, int pointsDeVie, int pointsAttaque) {
        this.nom = nom;
        this.type = type;
        this.pointsDeVie = pointsDeVie;
        this.pointsAttaque = pointsAttaque;
    }

    public String getNom() {
        return nom;
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    public int getPointsAttaque() {
        return pointsAttaque;
    }

    public TypeCanard getType() {
        return type;
    }

    public void attaquer(Canard autreCanard) {
        double multiplicateur = TypeCanard.getMultiplicateur(this.type, autreCanard.getType());
        int degats = (int)(pointsAttaque * multiplicateur);
        System.out.println(this.nom + " attaque " + autreCanard.getNom() +
                " et inflige " + degats + " points de dégâts.");
        autreCanard.subirDegats(degats);
    }

    public void subirDegats(int degats) {
        pointsDeVie -= degats;
        if (pointsDeVie < 0) {
            pointsDeVie = 0;
        }
        System.out.println(nom + " subit " + degats + " dégâts, restant à " +
                pointsDeVie + " PV.");
    }

    public boolean estKO() {
        return pointsDeVie <= 0;
    }

    // Méthode à implémenter par chaque classe fille
    public abstract void activerCapaciteSpeciale();
}
