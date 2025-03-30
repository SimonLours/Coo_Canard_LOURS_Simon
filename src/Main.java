import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principale du simulateur de combat "Canard Fighter Simulator".
 * <p>
 * Cette classe fournit une interface textuelle permettant à l'utilisateur de créer des canards,
 * de sélectionner deux canards pour un combat, et de simuler un combat tour par tour.
 * Chaque canard peut choisir d'effectuer une attaque normale ou d'utiliser sa capacité spéciale.
 * </p>
 *
 * @version 1.0
 */
public class Main {

    /**
     * Point d'entrée de l'application Canard Fighter Simulator.
     * <p>
     * Affiche un menu avec les options suivantes :
     * <ul>
     *   <li>1. Créer un canard</li>
     *   <li>2. Lancer une bataille</li>
     *   <li>3. Quitter</li>
     * </ul>
     * L'utilisateur peut créer des canards en renseignant leur nom, type (Eau, Feu, Glace, Vent),
     * points de vie et points d'attaque. Une bataille peut ensuite être lancée entre deux canards sélectionnés.
     * Chaque round permet aux deux canards de choisir s'ils attaquent ou utilisent leur capacité spéciale.
     * </p>
     *
     * @param args les arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Canard> canards = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Canard Fighter Simulator ---");
            System.out.println("1. Créer un canard");
            System.out.println("2. Lancer une bataille");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option : ");
            String choix = scanner.nextLine();

            switch(choix) {
                case "1":
                    // Création d'un canard
                    System.out.print("Entrez le nom du canard : ");
                    String nom = scanner.nextLine();

                    System.out.print("Entrez le type du canard (Eau, Feu, Glace, Vent) : ");
                    String typeStr = scanner.nextLine().toUpperCase();

                    // Vérification du type et création en fonction
                    TypeCanard type;
                    try {
                        type = TypeCanard.valueOf(typeStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Type invalide. Création d'un canard de type EAU par défaut.");
                        type = TypeCanard.EAU;
                    }

                    System.out.print("Entrez les points de vie du canard : ");
                    int pv = Integer.parseInt(scanner.nextLine());

                    System.out.print("Entrez les points d'attaque du canard : ");
                    int pa = Integer.parseInt(scanner.nextLine());

                    Canard nouveauCanard;
                    switch (type) {
                        case EAU:
                            nouveauCanard = new CanardEau(nom, pv, pa);
                            break;
                        case FEU:
                            nouveauCanard = new CanardFeu(nom, pv, pa);
                            break;
                        case GLACE:
                            nouveauCanard = new CanardGlace(nom, pv, pa);
                            break;
                        case VENT:
                            nouveauCanard = new CanardVent(nom, pv, pa);
                            break;
                        default:
                            nouveauCanard = new CanardEau(nom, pv, pa);
                    }

                    canards.add(nouveauCanard);
                    System.out.println("Canard " + nouveauCanard.getNom() + " de type " + nouveauCanard.getType() + " créé !");
                    break;

                case "2":
                    // Vérification qu'il y a au moins 2 canards
                    if (canards.size() < 2) {
                        System.out.println("Il faut au moins 2 canards pour lancer une bataille.");
                        break;
                    }

                    // Affichage de la liste des canards
                    System.out.println("\nListe des canards :");
                    for (int i = 0; i < canards.size(); i++) {
                        Canard c = canards.get(i);
                        System.out.println((i+1) + ". " + c.getNom() + " (Type: " + c.getType() +
                                ", PV: " + c.getPointsDeVie() + ", PA: " + c.getPointsAttaque() + ")");
                    }

                    // Sélection des deux canards pour le combat
                    System.out.print("Sélectionnez le numéro du premier canard : ");
                    int index1 = Integer.parseInt(scanner.nextLine()) - 1;

                    System.out.print("Sélectionnez le numéro du deuxième canard : ");
                    int index2 = Integer.parseInt(scanner.nextLine()) - 1;

                    if (index1 < 0 || index1 >= canards.size() ||
                            index2 < 0 || index2 >= canards.size() ||
                            index1 == index2) {
                        System.out.println("Sélection invalide. Annulation de la bataille.");
                        break;
                    }

                    Canard canard1 = canards.get(index1);
                    Canard canard2 = canards.get(index2);

                    System.out.println("\nDébut du combat entre " + canard1.getNom() + " et " + canard2.getNom());

                    int round = 1;
                    // Boucle de combat : les canards s'affrontent tour par tour jusqu'à ce que l'un soit KO.
                    while (!canard1.estKO() && !canard2.estKO()) {
                        System.out.println("\n--- Round " + round + " ---");

                        // Tour du premier canard
                        System.out.println("\nAction pour " + canard1.getNom() + " :");
                        System.out.println("1. Attaquer");
                        System.out.println("2. Utiliser la capacité spéciale");
                        System.out.print("Choix : ");
                        String choixAction1 = scanner.nextLine();

                        if ("1".equals(choixAction1)) {
                            canard1.attaquer(canard2);
                        } else if ("2".equals(choixAction1)) {
                            // Pour certains types, on combine la capacité spéciale avec l'attaque
                            if (canard1 instanceof CanardFeu) {
                                ((CanardFeu) canard1).activerCapaciteSpeciale();
                                canard1.attaquer(canard2);
                            } else if (canard1 instanceof CanardVent) {
                                ((CanardVent) canard1).activerCapaciteSpeciale();
                                canard1.attaquer(canard2);
                            } else {
                                // Pour CanardEau et CanardGlace, la capacité ne provoque pas d'attaque
                                canard1.activerCapaciteSpeciale();
                            }
                        } else {
                            System.out.println("Action invalide. Attaque par défaut.");
                            canard1.attaquer(canard2);
                        }

                        if (canard2.estKO()) {
                            System.out.println(canard2.getNom() + " est KO !");
                            break;
                        }

                        // Tour du deuxième canard
                        System.out.println("\nAction pour " + canard2.getNom() + " :");
                        System.out.println("1. Attaquer");
                        System.out.println("2. Utiliser la capacité spéciale");
                        System.out.print("Choix : ");
                        String choixAction2 = scanner.nextLine();

                        if ("1".equals(choixAction2)) {
                            canard2.attaquer(canard1);
                        } else if ("2".equals(choixAction2)) {
                            if (canard2 instanceof CanardFeu) {
                                ((CanardFeu) canard2).activerCapaciteSpeciale();
                                canard2.attaquer(canard1);
                            } else if (canard2 instanceof CanardVent) {
                                ((CanardVent) canard2).activerCapaciteSpeciale();
                                canard2.attaquer(canard1);
                            } else {
                                canard2.activerCapaciteSpeciale();
                            }
                        } else {
                            System.out.println("Action invalide. Attaque par défaut.");
                            canard2.attaquer(canard1);
                        }

                        if (canard1.estKO()) {
                            System.out.println(canard1.getNom() + " est KO !");
                            break;
                        }

                        round++;
                        System.out.print("\nAppuyez sur Entrée pour passer au round suivant...");
                        scanner.nextLine();
                    }

                    // Affichage du vainqueur
                    if (canard1.estKO()) {
                        System.out.println("\n" + canard2.getNom() + " remporte le combat !");
                    } else {
                        System.out.println("\n" + canard1.getNom() + " remporte le combat !");
                    }
                    break;

                case "3":
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;

                default:
                    System.out.println("Option invalide. Essayez de nouveau.");
            }
        }
    }
}
