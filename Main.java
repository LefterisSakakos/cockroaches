import java.util.Scanner;

public class Main  {
    public static void main(String[] args) {
        // Χρήση try-with-resources για αυτόματο κλείσιμο του scanner
        try (Scanner scanner = new Scanner(System.in)) {
            
            // Δημιουργία πίνακα με τα λαχανικά
            Plants[] plants = new Plants[]{
                new Plants("Λάδι", 100, 2000, 2.5, 300, 1500),
                new Plants("Τομάτα", 120, 2500, 1.8, 350, 2000),
                new Plants("Αγγούρι", 110, 2200, 2.0, 250, 1800),
                new Plants("Καρότο", 80, 1800, 2.2, 200, 1200),
                new Plants("Πιπεριά", 130, 2100, 3.0, 320, 1900),
                new Plants("Κρεμμύδι", 90, 1600, 1.5, 150, 1400),
                new Plants("Μελιτζάνα", 110, 2400, 2.8, 300, 1700),
                new Plants("Κολοκύθι", 100, 2200, 2.4, 270, 1600),
                new Plants("Σπανάκι", 60, 1500, 1.7, 100, 1100),
                new Plants("Πατάτα", 95, 1800, 2.1, 280, 1500)
            };

            // Εισαγωγή στοιχείων για το χωράφι
            System.out.println("Εισαγωγή στοιχείων για το χωράφι:");
            System.out.print("Αριθμός στρεμμάτων: ");
            int area = scanner.nextInt();

            System.out.print("Κόστος εργατοωρών ανά ώρα: ");
            double laborCostPerHour = scanner.nextDouble();

            System.out.print("Συνολικό νερό διαθέσιμο (σε λίτρα): ");
            int totalWaterAvailable = scanner.nextInt();

            // Δημιουργία του χωραφιού
            PlantsData plantsD = new PlantsData(area, laborCostPerHour, totalWaterAvailable);

            // Δημιουργία του προβλήματος βελτιστοποίησης
            OptimizationProblem optimization = new OptimizationProblem(plants, plantsD);

            // Κλήση της μεθόδου για τη βελτιστοποίηση
            optimization.optimize();

        } catch (Exception e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά την εκτέλεση: " + e.getMessage());
        }
    }
}

