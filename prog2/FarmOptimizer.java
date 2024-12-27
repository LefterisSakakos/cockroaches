import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FarmOptimizer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Καλώς ήρθατε στο πρόγραμμα βελτιστοποίησης καλλιέργειας!");
        System.out.println("Θα σας ζητηθεί να εισάγετε στοιχεία για τα φυτά.\n");

        // Εισαγωγή διαθέσιμων φυτών
        List<Plant> plants = new ArrayList<>();
        System.out.println("Πόσα φυτά θέλετε να καταχωρήσετε;");
        int numPlants = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numPlants; i++) {
            System.out.println("Καταχωρήστε τα στοιχεία για το φυτό " + (i + 1) + ":");

            System.out.print("Όνομα: ");
            String name = scanner.nextLine();

            System.out.print("Νερό ανά μονάδα: ");
            double waterRequired = Double.parseDouble(scanner.nextLine());

            System.out.print("Έκταση ανά μονάδα (σε στρέμματα): ");
            double landRequired = Double.parseDouble(scanner.nextLine());

            System.out.print("Εργατοώρες ανά μονάδα: ");
            double laborHours = Double.parseDouble(scanner.nextLine());

            String climateType;
            while (true) {
                System.out.print("Κλίμα (δυνατές επιλογές: compatible, dry, wet): ");
                climateType = scanner.nextLine().trim();
                if (climateType.equalsIgnoreCase("compatible") ||
                    climateType.equalsIgnoreCase("dry") ||
                    climateType.equalsIgnoreCase("wet")) {
                    break;
                }
                System.out.println("Λάθος επιλογή! Παρακαλώ επιλέξτε μία από τις δυνατές επιλογές: compatible, dry, wet.");
            }

            System.out.print("Κέρδος ανά μονάδα: ");
            double profitPerUnit = Double.parseDouble(scanner.nextLine());

            plants.add(new Plant(name, waterRequired, landRequired, laborHours, climateType, profitPerUnit));
        }

        // Προκαθορισμένοι περιορισμοί
        List<Constraint> constraints = new ArrayList<>();
        constraints.add(new Constraint("water", 500)); // Μέγιστο όριο νερού
        constraints.add(new Constraint("land", 5));   // Μέγιστη διαθέσιμη έκταση
        constraints.add(new Constraint("labor", 50)); // Μέγιστες διαθέσιμες εργατοώρες

        // Υπολογισμός βέλτιστης λύσης
        OptimizationEngine engine = new OptimizationEngine(plants, constraints);

        System.out.println("\nΥπολογισμός βέλτιστης λύσης...");
        Map<Plant, Integer> solution = engine.optimize();

        System.out.println("\nΒέλτιστη λύση:");
        for (Map.Entry<Plant, Integer> entry : solution.entrySet()) {
            System.out.printf("Φυτό: %s, Ποσότητα: %d, Κέρδος: %.2f\n",
                    entry.getKey().name, entry.getValue(), entry.getKey().calculateProfit(entry.getValue()));
        }

        System.out.println("\nΣας ευχαριστούμε που χρησιμοποιήσατε το πρόγραμμα μας!");
        scanner.close();
    }
}
