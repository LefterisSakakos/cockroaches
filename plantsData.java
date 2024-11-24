class plantsData {
    int area;
    double laborCost;
    int availableIrrigation;
    public plantsData(int area, double laborCost, int vailableIrrigation) {
        this.area = area;
        this.laborCost = laborCost;
        this.availableIrrigation = vailableIrrigation;
    }
     public int getArea() {
        return area;
    }

    public double getLaborCost() {
        return laborCost;
    }

    public int getAvailableIrrigation() {
        return availableIrrigation;
    }

public void setArea(int area) {
        this.area = area;
    }

    public void setLaborCost(int laborCost) {
        this.laborCost = laborCost;
    }

    public void setAvailableIrrigation(int availableIrrigation) {
        this.availableIrrigation = availableIrrigation;
    }
}