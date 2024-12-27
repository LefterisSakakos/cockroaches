import org.apache.commons.math3.optimization.*;
import org.apache.commons.math3.optimization.direct.*;
import org.apache.commons.math3.analysis.function.*;

import java.util.Scanner;

public class OptimalPlanting {

    // Πίνακες με δεδομένα φυτών
    public static final String[] plantsNames = {
        "Apples", "Oranges", "Peaches", "Pears", "Grapes",
        "Kiwis", "Olives", "Figs", "Pomegranates", "Lemons"
    };

    public static final double[][] plantsFixedData = {
        {4.5, 2.22, 45500.0, 140.0, 9.0},  // Apples
        {5.75, 1,78, 56000.0, 75.0, 12.0},  // Oranges
        {5.0, 2.00, 38500.0, 90.0, 10.0},  // Peaches
        {5.0, 2.22, 38500.0, 95.0, 9.0},  // Pears
        {2.15, 1,55, 32250.0, 25.0, 4.0},  // Grapes
        {3.75, 1.90, 56000.0, 50.0, 14.0},  // Kiwis
        {6.5, 1.78, 36750.0, 70.0, 12.0},  // Olives
        {5.5, 1.33, 28000.0, 120.0, 8.0},  // Figs
        {4.5, 1.11, 45500.0, 65.0, 11.0},  // Pomegranates
        {5.5, 2.00, 45500.0, 65.0, 12.0}   // Lemons
    };

    public static void main(String[] args) {
        // Δημιουργία Scanner για είσοδο από τον χρήστη
        Scanner scanner = new Scanner(System.in);

        // Εισαγωγή δεδομένων από τον χρήστη
        System.out.println("Enter the area of the field (in square meters): ");
        int area = scanner.nextInt();

        System.out.println("Enter the labor cost per hour: ");
        double laborCost = scanner.nextDouble();

        System.out.println("Enter the available water for irrigation (in liters): ");
        int availableWater = scanner.nextInt();

        // Δημιουργία του αντικειμένου PlantsData για να αποθηκεύσουμε τα δεδομένα του χρήστη
        PlantsData userData = new PlantsData(area, laborCost, availableWater);
        double Cw = 0.0002; 
        double Cl = userData.getLaborCost();
        // Ορισμός της συνάρτησης στόχου για μεγιστοποίηση του κέρδους
        ObjectiveFunction function = new ObjectiveFunction((double[] variables) -> {
            double profit = 0;
            for (int i = 0; i < plantsNames.length; i++) {
                double Ri = plantsFixedData[i][2]; // Έσοδα
                double Ci = plantsFixedData[i][4]; // Κόστος
                // Κόστος εργατοώρων
                double Hi = plantsFixedData[i][1]; // Εργατοώρες
                double Wa = plantsFixedData[i][3]; // Νερό
                 // Κόστος ανά λίτρο νερού (π.χ., 0.0002 ανά λίτρο)
                profit += (Ri - Ci - (Cl * Hi) - (Wa * Cw)) * variables[i];
            }
            return profit; // μπορεί να χρειαστεί να το κάνουμε -profit γιατί η βιβλιοθήκη ελαχιστοποιεί
        });

        // Ορισμός περιορισμών για την έκταση του χωραφιού και το νερό
        LinearConstraintSet constraints = new LinearConstraintSet();
        double[] areaConstraints = new double[plantsNames.length];
        double[] waterConstraints = new double[plantsNames.length];

        for (int i = 0; i < plantsNames.length; i++) {
            areaConstraints[i] = plantsFixedData[i][0]; // Απόσταση μεταξύ των φυτών
            waterConstraints[i] = plantsFixedData[i][3]; // Απαιτούμενη ποσότητα νερού
        }

        constraints.addLinearConstraint(new LinearConstraint(areaConstraints, Relationship.LEQ, userData.getArea()));
        constraints.addLinearConstraint(new LinearConstraint(waterConstraints, Relationship.LEQ, userData.getAvailableIrrigation()));

        // Επιλογή του αλγορίθμου βελτιστοποίησης (π.χ. Simplex, Nelder-Mead)
        SimplexSimplex optimizer = new SimplexSimplex(1e-6);
        PointValuePair result = optimizer.optimize(function, constraints);

        // Εκτύπωση του βέλτιστου συνδυασμού φυτών και άλλων στοιχείων
        double totalLaborHours = 0;
        double totalWaterRequired = 0;
        double totalCost = 0;
        double totalRevenue = 0;
        double totalAreaUsed = 0;
        double totalWaterCost = 0;
        double totalLaborRequiredCost = 0;

        System.out.println("Optimal Planting Configuration: ");
        double[] optimalSolution = result.getPoint();  // Αυτό είναι το αποτέλεσμα των φυτών (πόσα να φυτέψετε)

        // Εμφάνιση των αποτελεσμάτων
        for (int i = 0; i < plantsNames.length; i++) {
            double numPlants = optimalSolution[i];

            // Εμφάνιση στοιχείων για κάθε φυτό
            if (numPlants > 0) {
                double areaUsed = numPlants * plantsFixedData[i][0]; // Υπολογισμός των τετραγωνικών μέτρων που καταλαμβάνει το φυτό
                double laborRequired = numPlants * plantsFixedData[i][1]; // Εργατοώρες
                double waterRequired = numPlants * plantsFixedData[i][3]; // Νερό
                double cost = numPlants * plantsFixedData[i][4]; // Κόστος
                double revenue = numPlants * plantsFixedData[i][2]; // Έσοδα
                double watercost = Cw * waterRequired;
                double laborRequiredCost = Cl * laborRequired;

                totalAreaUsed += areaUsed;
                totalLaborHours += laborRequired;
                totalWaterRequired += waterRequired;
                totalCost += cost;
                totalRevenue += revenue;
                totalWaterCost += watercost;
                totalLaborRequiredCost += laborRequiredCost;

                // Εκτύπωση για κάθε φυτό
                System.out.println(plantsNames[i] + ": " + (int) numPlants);
                System.out.println("  Area: " + areaUsed + " m²");
                System.out.println("  Labor Hours: " + laborRequired);
                System.out.println("  Water Required: " + waterRequired + " liters");
                System.out.println("  Cost: " + cost);
                System.out.println("  Revenue: " + revenue);
                System.out.println("Water Cost: " + watercost);
            }
        }

        // Εκτύπωση συνολικών στοιχείων
        System.out.println("\nSummary of Optimal Configuration:");
        System.out.println("Total Area Used: " + totalAreaUsed + " m²");
        System.out.println("Total Labor Hours: " + totalLaborHours);
        System.out.println("Total Labor Hours Cost: " + totalLaborRequiredCost );
        System.out.println("Total Water Required: " + totalWaterRequired + " liters");
        System.out.println("Total Water Required Cost: " +  totalWaterCost );
        System.out.println("Total Cost: " + totalCost);
        System.out.println("Total Revenue: " + totalRevenue);
        System.out.println("Total Profit: " + (totalRevenue - totalCost - totalWaterCost - totalLaborRequiredCost ));
        
        scanner.close();
    }
}


