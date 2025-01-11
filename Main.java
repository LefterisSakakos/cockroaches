import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Λήψη εισόδων από τον χρήστη
        System.out.println("Enter the area of the field (in square meters): ");
        int area = scanner.nextInt();

        System.out.println("Enter the labor cost per hour: ");
        double laborCost = scanner.nextDouble();

        System.out.println("Enter the available water for irrigation (in liters): ");
        int availableWater = scanner.nextInt();

        // Έλεγχος αν οι είσοδοι είναι έγκυρες
        if (area <= 0 || laborCost <= 0 || availableWater <= 0) {
            System.out.println("Invalid input! All values must be positive.");
            return;
        }

        // Εμφάνιση των δεδομένων εισόδου
        System.out.println("Area: " + area + ", Labor cost: " + laborCost + ", Water: " + availableWater);

        // Δημιουργία αντικειμένου PlantsData και OptimizationEngine
        PlantsData plantsData = new PlantsData(area, laborCost, availableWater);
        OptimizationEngine engine = new OptimizationEngine(plantsData);

        try {
            // Εκτέλεση της βελτιστοποίησης και εμφάνιση αποτελεσμάτων
            engine.optimizeAndDisplay();
        } catch (Exception e) {
            System.out.println("An error occurred during optimization:");
            e.printStackTrace();
        }
    }
}
