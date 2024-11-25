
import java.util.List;
import java.util.Map;
import java.util.HashMap;
class OptimizationEngine {
    List<Plant> availablePlants;
    List<Constraint> constraints;

    public OptimizationEngine(List<Plant> availablePlants, List<Constraint> constraints) {
        this.availablePlants = availablePlants;
        this.constraints = constraints;
    }

    public Map<Plant, Integer> optimize() {
        Map<Plant, Integer> optimalSolution = new HashMap<>();
        for (Plant plant : availablePlants) {
            int quantity = 0;
            boolean canPlantMore = true;
            while (canPlantMore) {
                for (Constraint constraint : constraints) {
                    if (!constraint.isSatisfied(plant, quantity + 1)) {
                        canPlantMore = false;
                        break;
                    }
                }
                if (canPlantMore) quantity++;
            }
            optimalSolution.put(plant, quantity);
        }
        return optimalSolution;
    }
}
