public  class OptimizationProblem {
   private final Plants[] plants;
    private final PlantsData plantsD;

    // Constructor
    public OptimizationProblem(Plants[] plants, PlantsData plantsD) {
        this.plants = plants;
        this.plantsD = plantsD;
    }

    // Μέθοδος για να υπολογιστεί ο βέλτιστος συνδυασμός λαχανικών
    public void optimize() {
        int numOfPlants = plants.length;
        double maxProfit = 0;
        double[] bestCombination = new double[numOfPlants]; // Αποθήκευση της καλύτερης συνδυαστικής λύσης

        // Διαφορετικές παραμέτρους για τη βελτιστοποίηση (max profit, περιορισμοί)
        double maxArea = plantsD.getArea();
        double availableLaborCostPerHour = plantsD.getLaborCost(); // Κόστος εργασίας ανά ώρα
        double availableWater = plantsD.getAvailableIrrigation(); // Συνολικό διαθέσιμο νερό

        // Μέγιστο κέρδος
        for (int i = 0; i < (1 << numOfPlants); i++) {
            double totalProfit = 0;
            double totalWater = 0;
            double totalArea = 0;

            double[] currentCombination = new double[numOfPlants];

            // Δημιουργία ενός συνδυασμού λαχανικών με βάση το bitmask
            for (int j = 0; j < numOfPlants; j++) {
                if ((i & (1 << j)) != 0) { // Αν το bit είναι 1, το λαχανικό περιλαμβάνεται
                    currentCombination[j] = 1.0; // Αντικαθιστά με το συντελεστή στρεμμάτων
                    totalArea += 1;
                    totalWater += plants[j].getWaterRequiredPerHectare(); // Νερό ανά στρέμμα
                    // Υπολογισμός του κέρδους λαχανικού με αφαιρεμένο το κόστος εργατοώρων
                    double plantsProfit = plants[j].getProductionPerHectare() * plants[j].getPricePerKg()
                            - plants[j].getSeedCostPerHectare()
                            - (plants[j].getLaborHoursPerHectare() * availableLaborCostPerHour); // Αφαίρεση κόστους εργατοώρων
                    totalProfit += plantsProfit;
                }
            }

            // Αν ο συνδυασμός τηρεί τους περιορισμούς (έκταση, εργατοώρες, νερό)
            if (totalArea <= maxArea && totalWater <= availableWater) {
                if (totalProfit > maxProfit) {
                    maxProfit = totalProfit;
                    bestCombination = currentCombination.clone();
                }
            }
        }

        // Εμφάνιση του καλύτερου συνδυασμού
        System.out.println("Ο καλύτερος συνδυασμός λαχανικών με κέρδος: " + maxProfit);
        for (int i = 0; i < plants.length; i++) {
            if (bestCombination[i] > 0) {
                System.out.println(plants[i].getName() + ": " + bestCombination[i] + " στρέμματα");
            }
        }
    }
}
