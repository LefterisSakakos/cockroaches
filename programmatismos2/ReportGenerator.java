
public class ReportGenerator {
    PlantsDB plantsDB = new PlantsDB();
    public  void generateReport(PointValuePair result, PlantsData plants) {
        double[] optimalSolution = result.getPoint();
        double totalLaborHours = 0;
        double totalWaterRequired = 0;
        double totalCost = 0;
        double totalRevenue = 0;
        double totalAreaUsed = 0;
        double totalWaterCost = 0;
        double totalLaborCost = 0;

        System.out.println("Optimal Planting Configuration:");

        for (int i = 0; i < plantsDB.getNumberOfPlants(); i++) {
            double numPlants = optimalSolution[i];

            if (numPlants > 0) {
                double areaUsed = numPlants * plantsDB.plantsFixedData[i][0];
                double laborRequired = numPlants * plantsDB.plantsFixedData[i][1];
                double waterRequired = numPlants * plantsDB.plantsFixedData[i][3];
                double cost = numPlants * plantsDB.plantsFixedData[i][4];
                double revenue = numPlants * plantsDB.plantsFixedData[i][2];
                double waterCost = 0.0002 * waterRequired;
                double laborCost = plants.getLaborCost() * laborRequired;

                totalAreaUsed += areaUsed;
                totalLaborHours += laborRequired;
                totalWaterRequired += waterRequired;
                totalCost += cost;
                totalRevenue += revenue;
                totalWaterCost += waterCost;
                totalLaborCost += laborCost;

                System.out.println(plantsDB.plantsNames[i] + ": " + (int) numPlants);
                System.out.println("  Area: " + areaUsed + " m²");
                System.out.println("  Labor Hours: " + laborRequired);
                System.out.println("  Water Required: " + waterRequired + " liters");
                System.out.println("  Cost: " + cost);
                System.out.println("  Revenue: " + revenue);
            }
        }

        System.out.println("\nSummary:");
        System.out.println("Total Area Used: " + totalAreaUsed + " m²");
        System.out.println("Total Labor Hours: " + totalLaborHours);
        System.out.println("Total Water Required: " + totalWaterRequired + " liters");
        System.out.println("Total Water Cost: " +  totalWaterCost   );
        System.out.println("Total Labor Cost: " + totalLaborCost );
        System.out.println("Total Cost: " + totalCost + totalWaterCost +  totalLaborCost );
        System.out.println("Total Revenue: " + totalRevenue);
        System.out.println("Total Profit: " + (totalRevenue - totalCost - totalWaterCost - totalLaborCost));
    }
}
