class Constraint {
    String type;
    double maxLimit;

    public Constraint(String type, double maxLimit) {
        this.type = type;
        this.maxLimit = maxLimit;
    }

    public boolean isSatisfied(Plant plant, double quantity) {
        switch (type) {
            case "water":
                return plant.waterRequired * quantity <= maxLimit;
            case "land":
                return plant.landRequired * quantity <= maxLimit;
            case "labor":
                return plant.laborHours * quantity <= maxLimit;
            case "climate":
                return plant.climateType.equalsIgnoreCase("compatible");
            default:
                return true;
        }
    }
}
