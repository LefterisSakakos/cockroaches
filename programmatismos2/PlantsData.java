
public class PlantsData {
    private double area;
    private double laborCost;
    private double availableIrrigation;

    // Constructor
    public PlantsData(double area, double laborCost, double availableIrrigation) {
        this.area = area;
        this.laborCost = laborCost;
        this.availableIrrigation = availableIrrigation;
    }

    // Getters
    public double getArea() {
        return area;
    }

    public double getLaborCost() {
        return laborCost;
    }

    public double getAvailableIrrigation() {
        return availableIrrigation;
    }

    // Setters
    public void setArea(double area) {
        this.area = area;
    }

    public void setLaborCost(double laborCost) { // Εδώ αλλάξαμε σε double
        this.laborCost = laborCost;
    }

    public void setAvailableIrrigation(double availableIrrigation) {
        this.availableIrrigation = availableIrrigation;
    }
}
