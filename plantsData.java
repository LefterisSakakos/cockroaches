public class PlantsData {
    private int area;
    private double laborCost;
    private int availableIrrigation;

    // Constructor
    public PlantsData(int area, double laborCost, int availableIrrigation) {
        this.area = area;
        this.laborCost = laborCost;
        this.availableIrrigation = availableIrrigation;
    }

    // Getters
    public int getArea() {
        return area;
    }

    public double getLaborCost() {
        return laborCost;
    }

    public int getAvailableIrrigation() {
        return availableIrrigation;
    }

    // Setters
    public void setArea(int area) {
        this.area = area;
    }

    public void setLaborCost(double laborCost) { // Εδώ αλλάξαμε σε double
        this.laborCost = laborCost;
    }

    public void setAvailableIrrigation(int availableIrrigation) {
        this.availableIrrigation = availableIrrigation;
    }
}
