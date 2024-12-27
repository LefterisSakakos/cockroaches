
import org.apache.commons.math3.optimization.*;
import org.apache.commons.math3.optimization.direct.*;
import org.apache.commons.math3.analysis.function.*;

public class OptimizationEngine {
    private PlantsData plants;
    PlantsDB plantsDB = new PlantsDB();

    public OptimizationEngine(PlantsData plants) {
        this.plants = plants;
    }

    public PointValuePair optimize() {
        ObjectiveFunction function = new ObjectiveFunction((double[] variables) -> {
            double profit = 0;
            for (int i = 0; i < plantsDB.getNumberOfPlants(); i++) {
                double Ri = plantsDB.plantsFixedData[i][2]; // Έσοδα ανά δέντρο
                double Ci = plantsDB.plantsFixedData[i][4]; // Κόστος ανά δέντρο
                double Cl = plants.getLaborCost();      // Κόστος εργατοώρας
                double Hi = plantsDB.plantsFixedData[i][1]; // Εργατοώρες ανά δέντρο
                double Wa = plantsDB.plantsFixedData[i][3]; // Νερό ανά δέντρο
                double Cw = 0.0002;                        // Κόστος νερού ανά λίτρο
                profit += (Ri - Ci - (Cl * Hi) - (Wa * Cw)) * variables[i];
            }
            return profit;
        });

        // Περιορισμοί
        double[] areaConstraints = new double[plantsDB.getNumberOfPlants()];
        double[] waterConstraints = new double[plantsDB.getNumberOfPlants()];

        for (int i = 0; i < plantsDB.getNumberOfPlants(); i++) {
            areaConstraints[i] = plantsDB.plantsFixedData[i][0]; // Απόσταση ανά δέντρο
            waterConstraints[i] = plantsDB.plantsFixedData[i][3]; // Νερό ανά δέντρο
        }

        LinearConstraintSet constraints = new LinearConstraintSet();
        constraints.addLinearConstraint(new LinearConstraint(areaConstraints, Relationship.LEQ, plants.getArea()));
        constraints.addLinearConstraint(new LinearConstraint(waterConstraints, Relationship.LEQ, plants.getAvailableIrrigation()));

        SimplexOptimizer optimizer = new SimplexOptimizer(1e-6, 1e-10);
        PointValuePair result = optimizer.optimize(
            new MaxIter(1000),                       // Μέγιστος αριθμός επαναλήψεων
            new ObjectiveFunction(function),         // Συνάρτηση στόχου
            GoalType.MAXIMIZE,                       // Στόχος: μεγιστοποίηση
            new LinearConstraintSet(constraints),    // Περιορισμοί
            new NonNegativeConstraint(true)          // Μη αρνητικές τιμές για μεταβλητές
        );
    }
}
