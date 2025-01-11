import java.util.Map;

public class ReportGenerator {
    private String[] plantNames;
    private double[][] plantData; // Δεδομένα φυτών: water, land, labor

    public ReportGenerator(String[] plantNames, double[][] plantData) {
        this.plantNames = plantNames;
        this.plantData = plantData;
    }

    public void generateReport(Map<String, Integer> solution, Map<String, Double> constraints) {
        double totalLaborHours = 0;
        double totalWaterRequired = 0;
        double totalAreaUsed = 0;

        System.out.println("Optimal Planting Configuration:");

        for (Map.Entry<String, Integer> entry : solution.entrySet()) {
            String plantName = entry.getKey();
            int quantity = entry.getValue();
            int plantIndex = getPlantIndex(plantName);

            if (plantIndex != -1 && quantity > 0) {
                double waterRequired = plantData[plantIndex][0] * quantity;
                double landRequired = plantData[plantIndex][1] * quantity;
                double laborRequired = plantData[plantIndex][2] * quantity;

                totalWaterRequired += waterRequired;
                totalAreaUsed += landRequired;
                totalLaborHours += laborRequired;

                System.out.println(plantName + ": " + quantity);
                System.out.println("  Area Used: " + landRequired + " m²");
                System.out.println("  Water Required: " + waterRequired + " liters");
                System.out.println("  Labor Hours: " + laborRequired);
            }
        }

        System.out.println("\nSummary:");
        System.out.println("Total Area Used: " + totalAreaUsed + " m²");
        System.out.println("Total Water Required: " + totalWaterRequired + " liters");
        System.out.println("Total Labor Hours: " + totalLaborHours);
        System.out.println("Constraints:");
        System.out.println("  Available Land: " + constraints.get("land") + " m²");
        System.out.println("  Available Water: " + constraints.get("water") + " liters");
        System.out.println("  Available Labor: " + constraints.get("labor") + " hours");

        // Έλεγχος αν οι περιορισμοί ικανοποιούνται
        if (totalAreaUsed > constraints.get("land")) {
            System.out.println("Warning: Exceeded land constraint!");
        }
        if (totalWaterRequired > constraints.get("water")) {
            System.out.println("Warning: Exceeded water constraint!");
        }
        if (totalLaborHours > constraints.get("labor")) {
            System.out.println("Warning: Exceeded labor constraint!");
        }
    }

    private int getPlantIndex(String plantName) {
        for (int i = 0; i < plantNames.length; i++) {
            if (plantNames[i].equals(plantName)) {
                return i;
            }
        }
        return -1; // Επιστρέφει -1 αν το φυτό δεν βρεθεί
    }
}
