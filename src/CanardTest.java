import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests unitaires pour les classes Canard et TypeCanard.
 * <p>
 * Ces tests permettent de vérifier :
 * <ul>
 *   <li>Le calcul correct des multiplicateurs entre types via {@link TypeCanard#getMultiplicateur(TypeCanard, TypeCanard)}</li>
 *   <li>Le bon déroulement des attaques et la gestion des points de vie dans {@link Canard#attaquer(Canard)} et {@link Canard#subirDegats(int)}</li>
 *   <li>La détection d'un canard hors combat via {@link Canard#estKO()}</li>
 *   <li>Le fonctionnement des capacités spéciales implémentées dans les classes dérivées de Canard</li>
 * </ul>
 * </p>
 *
 * @version 1.0
 */
public class CanardTest {

    /**
     * Vérifie que la méthode {@link TypeCanard#getMultiplicateur(TypeCanard, TypeCanard)} retourne les multiplicateurs attendus
     * en fonction des interactions entre différents types de canards.
     */
    @Test
    public void testGetMultiplicateur() {
        // Attaques efficaces (multiplicateur 1.5)
        assertEquals(1.5, TypeCanard.getMultiplicateur(TypeCanard.EAU, TypeCanard.FEU));
        assertEquals(1.5, TypeCanard.getMultiplicateur(TypeCanard.FEU, TypeCanard.GLACE));
        assertEquals(1.5, TypeCanard.getMultiplicateur(TypeCanard.GLACE, TypeCanard.VENT));
        assertEquals(1.5, TypeCanard.getMultiplicateur(TypeCanard.VENT, TypeCanard.EAU));

        // Attaques désavantagées (multiplicateur 0.5)
        assertEquals(0.5, TypeCanard.getMultiplicateur(TypeCanard.FEU, TypeCanard.EAU));
        assertEquals(0.5, TypeCanard.getMultiplicateur(TypeCanard.GLACE, TypeCanard.FEU));
        assertEquals(0.5, TypeCanard.getMultiplicateur(TypeCanard.VENT, TypeCanard.GLACE));
        assertEquals(0.5, TypeCanard.getMultiplicateur(TypeCanard.EAU, TypeCanard.VENT));

        // Attaques neutres (même type ou non définies)
        assertEquals(1.0, TypeCanard.getMultiplicateur(TypeCanard.EAU, TypeCanard.EAU));
        assertEquals(1.0, TypeCanard.getMultiplicateur(TypeCanard.FEU, TypeCanard.FEU));
    }

    /**
     * Teste l'interaction entre l'attaque et la réception de dégâts.
     * <p>
     * Un canard de type Eau attaque un canard de type Feu (multiplicateur 1.5) et inversement.
     * Ce test vérifie que les points de vie sont correctement mis à jour après chaque attaque.
     * </p>
     */
    @Test
    public void testAttaquerEtSubirDegats() {
        // Création d'un canard de type Eau et d'un canard de type Feu
        Canard canardEau = new CanardEau("Aqua", 100, 20);
        Canard canardFeu = new CanardFeu("Flame", 100, 20);

        // Eau est fort contre Feu : multiplicateur = 1.5
        // Dégâts attendus = 20 * 1.5 = 30 (en supposant qu'il n'y a pas d'attaque critique)
        canardEau.attaquer(canardFeu);
        assertEquals(70, canardFeu.getPointsDeVie());

        // Feu est désavantagé contre Eau : multiplicateur = 0.5
        // Dégâts attendus = 20 * 0.5 = 10 (en supposant qu'il n'y a pas d'attaque critique)
        canardFeu.attaquer(canardEau);
        assertEquals(90, canardEau.getPointsDeVie());
    }

    /**
     * Vérifie que la méthode {@link Canard#estKO()} retourne correctement si un canard est hors combat.
     */
    @Test
    public void testEstKO() {
        Canard canard = new CanardEau("Test", 10, 10);
        canard.subirDegats(5);
        assertFalse(canard.estKO(), "Le canard ne doit pas être KO après 5 dégâts.");
        canard.subirDegats(5);
        assertTrue(canard.estKO(), "Le canard doit être KO après avoir subi 10 dégâts.");
    }

    /**
     * Teste les capacités spéciales des différentes classes dérivées de {@link Canard}.
     * <p>
     * Pour {@link CanardEau}, la capacité spéciale doit régénérer 20 PV.
     * Pour {@link CanardFeu}, la capacité spéciale doit ajouter 10 dégâts supplémentaires lors de l'attaque.
     * </p>
     */
    @Test
    public void testCapaciteSpeciale() {
        // Test de la capacité spéciale de CanardEau : régénération de 20 PV
        Canard canardEau = new CanardEau("Aqua", 80, 20);
        canardEau.activerCapaciteSpeciale(); // devrait porter les PV à 100
        assertEquals(100, canardEau.getPointsDeVie());

        // Test de la capacité spéciale de CanardFeu : attaque enflammée
        // Création d'un canard cible pour simplifier le calcul du multiplicateur
        CanardFeu canardFeu = new CanardFeu("Flame", 100, 20);
        Canard cible = new CanardEau("Target", 100, 20);

        // Activation de la capacité spéciale qui ajoutera 10 dégâts supplémentaires lors de l'attaque
        canardFeu.activerCapaciteSpeciale();
        canardFeu.attaquer(cible);
        // Pour Feu vs Eau : multiplicateur = 0.5, donc les dégâts de base = 20 * 0.5 = 10,
        // auxquels s'ajoute 10 supplémentaires, totalisant 20 dégâts.
        // PV attendus pour la cible = 100 - 20 = 80.
        assertEquals(80, cible.getPointsDeVie());
    }
}
