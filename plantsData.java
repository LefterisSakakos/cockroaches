class plantsData {
    private String name;
    int water;
    int labor;
    int rad;
    public plantsData(String name, int water, int labor, int rad) {
        this.name = name;
        this.water = water;
        this.labor = labor;
        this.rad = rad;
    }
     public String getName() {
        return name;
    }

    public int getWater() {
        return water;
    }

    public int getLabor() {
        return slabor;
    }

    public int getRad() {
        return rad;
}
public void setName(String name) {
        this.name = name;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public void setLabor(int labor) {
        this.labor = labor;
    }

    public void setRad(int rad) {
        this.rad = rad;
    }
}