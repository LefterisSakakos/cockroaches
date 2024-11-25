class Plant {
    String name;
    double waterRequired;
    double landRequired;
    double laborHours;
    String climateType;
    double profitPerUnit;

    public Plant(String name, double waterRequired, double landRequired, double laborHours, String climateType, double profitPerUnit) {
        this.name = name;
        this.waterRequired = waterRequired;
        this.landRequired = landRequired;
        this.laborHours = laborHours;
        this.climateType = climateType;
        this.profitPerUnit = profitPerUnit;
    }

    public double calculateProfit(int quantity) {
        return profitPerUnit * quantity;
    }
}
