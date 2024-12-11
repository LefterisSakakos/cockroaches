import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.linear.*;

import java.util.ArrayList;
import java.util.List;

public class SimplexExample {
    public static void main(String[] args) {
        // Παράδειγμα Linear Program:
        // Μέγιστο: Z = Σ (r[i] - C[i] - (C_L * H[i]) - W_A) * X[i]

        // Εισαγωγή δεδομένων
        int n = 10; // Αριθμός φυτών
        double[] r = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 }; // Έσοδα
        double[] C = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }; // Κόστος
        double C_L = 2; // Κόστος Εργατοωρών
        double[] H = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // Απαιτούμενες εργατοώρες
        double W_A = 3; // Κόστος νερού
        double[] X = new double[n]; // Φυτά (αποφάσεις)

        // Ορισμός της αντικειμενικής συνάρτησης
        double[] coefficients = new double[n];
        for (int i = 0; i < n; i++) {
            coefficients[i] = r[i] - C[i] - (C_L * H[i]) - W_A; // Υπολογισμός των συντελεστών για την αντικειμενική
                                                                // συνάρτηση
        }
        LinearObjectiveFunction objectiveFunction = new LinearObjectiveFunction(coefficients, 0);

        // Δημιουργία περιορισμών
        List<LinearConstraint> constraints = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // Ο περιορισμός για κάθε X[i] να είναι είτε 0 είτε 1
            double[] constraintCoefficients = new double[n];
            constraintCoefficients[i] = 1; // Κάθε X[i] έχει περιορισμό μεταξύ 0 και 1
            constraints.add(new LinearConstraint(constraintCoefficients, Relationship.LEQ, 1)); // X[i] ≤ 1
            constraints.add(new LinearConstraint(constraintCoefficients, Relationship.GEQ, 0)); // X[i] ≥ 0
        }

        // Δημιουργία του Simplex solver
        SimplexSolver solver = new SimplexSolver();

        // Επίλυση του προβλήματος με τα περιορισμένα δεδομένα
        PointValuePair solution = solver.optimize(objectiveFunction, constraints, GoalType.MAXIMIZE, false);

        // Εκτύπωση της λύσης
        System.out.println("Solution (values for X):");
        for (int i = 0; i < n; i++) {
            System.out.println("X[" + i + "] = " + solution.getPoint()[i]);
        }

        // Εκτύπωση του συνολικού Z
        double Z = solution.getValue();
        System.out.println("Η τιμή της συνάρτησης βελτιστοποίησης Z είναι: " + Z);
    }
}
