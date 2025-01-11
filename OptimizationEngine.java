import org.apache.commons.math3.optim.*;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.noderiv.SimplexOptimizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptimizationEngine {
    private PlantsData plants;
    private PlantsDB plantsDB = new PlantsDB();

    public OptimizationEngine(PlantsData plants) {
        this.plants = plants;
    }

    public void optimizeAndDisplay() {
        // Συνάρτηση στόχου
        double[] profitCoefficients = getProfitCoefficients();
        validateArray(profitCoefficients, "Profit coefficients");

        LinearObjectiveFunction function = new LinearObjectiveFunction(profitCoefficients, 0);

        // Περιορισμοί
        double[] areaConstraints = getAreaConstraints();
        validateArray(areaConstraints, "Area constraints");

        double[] waterConstraints = getWaterConstraints();
        validateArray(waterConstraints, "Water constraints");

        List<LinearConstraint> constraints = new ArrayList<>();
        constraints.add(new LinearConstraint(areaConstraints, Relationship.LEQ, plants.getArea()));
        constraints.add(new LinearConstraint(waterConstraints, Relationship.LEQ, plants.getAvailableIrrigation()));

        // Εκτύπωση διαγνωστικών
        System.out.println("Profit coefficients: " + Arrays.toString(profitCoefficients));
        System.out.println("Area constraints: " + Arrays.toString(areaConstraints));
        System.out.println("Water constraints: " + Arrays.toString(waterConstraints));
        System.out.println("Available area: " + plants.getArea());
        System.out.println("Available water: " + plants.getAvailableIrrigation());

        // Επίλυση
        SimplexOptimizer optimizer = new SimplexOptimizer(1e-6, 1e-10);
        try {
            PointValuePair solution = optimizer.optimize(
                    new MaxIter(1000),
                    function,
                    new LinearConstraintSet(constraints),
                    GoalType.MAXIMIZE,
                    new NonNegativeConstraint(true));

            // Επεξεργασία αποτελεσμάτων
            if (solution != null) {
                double[] plantQuantities = solution.getPoint();
                double totalProfit = solution.getValue();
                double totalAreaUsed = calculateTotalArea(plantQuantities);
                double[] areasPerPlant = calculateAreasPerPlant(plantQuantities);

                // Εμφάνιση αποτελεσμάτων
                System.out.println("Βέλτιστος Συνδυασμός Φυτών:");
                System.out.printf("%-15s %-15s %-15s %-15s\n", "Φυτό", "Ποσότητα", "Χρησιμοποιούμενη Έκταση", "Κέρδος");
                for (int i = 0; i < plantsDB.getNumberOfPlants(); i++) {
                    if (plantQuantities[i] > 0) {
                        System.out.printf("%-15s %-15.2f %-15.2f %-15.2f\n",
                                PlantsDB.plantsNames[i], plantQuantities[i], areasPerPlant[i],
                                plantQuantities[i] * profitCoefficients[i]);
                    }
                }
                System.out.println("\nΣυνολικό Κέρδος: " + totalProfit);
                System.out.println("Συνολική Έκταση που Χρησιμοποιήθηκε: " + totalAreaUsed);
            } else {
                System.out.println("No solution found for the optimization problem.");
            }

        } catch (Exception e) {
            System.out.println("An error occurred during optimization: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private double calculateTotalArea(double[] plantQuantities) {
        double[] areaConstraints = getAreaConstraints();
        double totalArea = 0;
        for (int i = 0; i < plantQuantities.length; i++) {
            totalArea += plantQuantities[i] * areaConstraints[i];
        }
        return totalArea;
    }

    private double[] calculateAreasPerPlant(double[] plantQuantities) {
        double[] areaConstraints = getAreaConstraints();
        double[] areasPerPlant = new double[plantQuantities.length];
        for (int i = 0; i < plantQuantities.length; i++) {
            areasPerPlant[i] = plantQuantities[i] * areaConstraints[i];
        }
        return areasPerPlant;
    }

    private double[] getProfitCoefficients() {
        double[] profits = new double[plantsDB.getNumberOfPlants()];
        for (int i = 0; i < plantsDB.getNumberOfPlants(); i++) {
            double Ri = plantsDB.plantsFixedData[i][3];
            double Ci = plantsDB.plantsFixedData[i][2];
            double Cl = plants.getLaborCost();
            double Hi = plantsDB.plantsFixedData[i][4];
            double Wa = plantsDB.plantsFixedData[i][1];
            double Cw = 0.0002;

            profits[i] = Ri - Ci - (Cl * Hi) - (Wa * Cw);

            // Αν το κέρδος είναι αρνητικό, το μηδενίζουμε
            if (profits[i] < 0) {
                profits[i] = 0;
            }
        }
        return profits;
    }

    private double[] getAreaConstraints() {
        double[] areaConstraints = new double[plantsDB.getNumberOfPlants()];
        for (int i = 0; i < plantsDB.getNumberOfPlants(); i++) {
            areaConstraints[i] = plantsDB.plantsFixedData[i][0];
        }
        return areaConstraints;
    }

    private double[] getWaterConstraints() {
        double[] waterConstraints = new double[plantsDB.getNumberOfPlants()];
        for (int i = 0; i < plantsDB.getNumberOfPlants(); i++) {
            waterConstraints[i] = plantsDB.plantsFixedData[i][1];
        }
        return waterConstraints;
    }

    private void validateArray(double[] array, String arrayName) {
        if (array == null || array.length == 0) {
            throw new RuntimeException(arrayName + " is null or empty.");
        }
        if (Arrays.stream(array).anyMatch(Double::isNaN)) {
            throw new RuntimeException(arrayName + " contains NaN values.");
        }
    }
}
