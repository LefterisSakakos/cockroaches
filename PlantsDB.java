import java.util.List;
import java.util.ArrayList;

public class PlantsDB {

    // Ονόματα φυτών
    public static final String[] plantsNames = {
            "Apples", "Oranges", "Peaches", "Pears", "Grapes",
            "Kiwis", "Olives", "Figs", "Pomegranates", "Lemons"
    };

    // Δεδομένα φυτών: land, water, cost, revenue, labor
    public static final double[][] plantsFixedData = {
            { 4.5, 50, 9, 140, 2.22 }, // Apples
            { 5.75, 60, 12, 75, 1.78 }, // Oranges
            { 5.0, 45, 10, 90, 2.0 }, // Peaches
            { 5.0, 45, 9, 95, 2.22 }, // Pears
            { 2.15, 30, 4, 25, 1.55 }, // Grapes
            { 3.75, 60, 14, 50, 1.9 }, // Kiwis
            { 6.5, 37.5, 12, 70, 1.78 }, // Olives
            { 5.5, 28, 8, 120, 1.33 }, // Figs
            { 4.5, 45, 11, 65, 1.11 }, // Pomegranates
            { 5.5, 45, 12, 65, 2.0 } // Lemons
    };

    // Επιστρέφει τον αριθμό των φυτών
    public int getNumberOfPlants() {
        if (plantsNames == null || plantsFixedData == null) {
            throw new RuntimeException("Plant data is not initialized.");
        }
        if (plantsNames.length != plantsFixedData.length) {
            throw new RuntimeException("Mismatch between plant names and plant data.");
        }
        return plantsNames.length;
    }

    // Επιστρέφει όλα τα δεδομένα φυτών ως λίστα
    public static List<double[]> getPlantData() {
        if (plantsFixedData == null || plantsFixedData.length == 0) {
            throw new RuntimeException("No plant data available.");
        }

        List<double[]> data = new ArrayList<>();
        for (double[] plant : plantsFixedData) {
            if (plant.length != 5) { // Βεβαιωνόμαστε ότι κάθε φυτό έχει 5 τιμές δεδομένων
                throw new RuntimeException("Invalid plant data format.");
            }
            data.add(plant);
        }
        return data;
    }

    // Επιστρέφει τα δεδομένα για συγκεκριμένο φυτό
    public static double[] getPlantDataByName(String plantName) {
        if (plantName == null || plantName.isEmpty()) {
            throw new IllegalArgumentException("Plant name cannot be null or empty.");
        }

        for (int i = 0; i < plantsNames.length; i++) {
            if (plantsNames[i].equalsIgnoreCase(plantName)) {
                return plantsFixedData[i];
            }
        }
        throw new RuntimeException("Plant name not found: " + plantName);
    }

    // Επιστρέφει το όνομα του φυτού από το index
    public static String getPlantNameByIndex(int index) {
        if (index < 0 || index >= plantsNames.length) {
            throw new IndexOutOfBoundsException("Invalid plant index: " + index);
        }
        return plantsNames[index];
    }

    // Επιστροφή δεδομένων με όμορφη μορφή για debug/διαγνωστικά
    public static void printPlantData() {
        System.out.println("Plant Data:");
        for (int i = 0; i < plantsNames.length; i++) {
            System.out.printf("%-15s: Land: %.2f, Water: %.2f, Cost: %.2f, Revenue: %.2f, Labor: %.2f\n",
                    plantsNames[i],
                    plantsFixedData[i][0], // Land
                    plantsFixedData[i][1], // Water
                    plantsFixedData[i][2], // Cost
                    plantsFixedData[i][3], // Revenue
                    plantsFixedData[i][4] // Labor
            );
        }
    }
}
