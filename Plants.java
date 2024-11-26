public class Plants {
    private final String name;
    private final double laborHoursPerHectare;
    private final double productionPerHectare;
    private final double pricePerKg;
    private final double seedCostPerHectare;
    private final  double waterRequiredPerHectare;

    // Constructor
    public Plants(String name, double laborHoursPerHectare, double productionPerHectare, double pricePerKg, double seedCostPerHectare, double waterRequiredPerHectare) {
        this.name = name;
        this.laborHoursPerHectare = laborHoursPerHectare;
        this.productionPerHectare = productionPerHectare;
        this.pricePerKg = pricePerKg;
        this.seedCostPerHectare = seedCostPerHectare;
        this.waterRequiredPerHectare = waterRequiredPerHectare;
    }

    // Getters
    public String getName() { return name; }
    public double getLaborHoursPerHectare() { return laborHoursPerHectare; }
    public double getProductionPerHectare() { return productionPerHectare; }
    public double getPricePerKg() { return pricePerKg; }
    public double getSeedCostPerHectare() { return seedCostPerHectare; }
    public double getWaterRequiredPerHectare() { return waterRequiredPerHectare; }
}