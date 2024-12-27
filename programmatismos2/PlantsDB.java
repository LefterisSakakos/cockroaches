
public class PlantsDB {
    //Plants Names
    public static final String[] plantsNames = {
        "Apples", "Oranges", "Peaches", "Pears", "Grapes",
        "Kiwis", "Olives", "Figs", "Pomegranates", "Lemons"
    };
    //Plants Fixed Data[Spacing ανα δεντρο , Labor Hours, , Irregation , esoda ana dentro , kostos ana dentro , ]
    public static final double[][] plantsFixedData = {
        {4.5, 2.22, 45500.0, 140.0, 9.0},  // Apples
        {5.75, 1,78, 56000.0, 75.0, 12.0},  // Oranges
        {5.0, 2.00, 38500.0, 90.0, 10.0},  // Peaches
        {5.0, 2.22, 38500.0, 95.0, 9.0},  // Pears
        {2.15, 1,55, 32250.0, 25.0, 4.0},  // Grapes
        {3.75, 1.90, 56000.0, 50.0, 14.0},  // Kiwis
        {6.5, 1.78, 36750.0, 70.0, 12.0},  // Olives
        {5.5, 1.33, 28000.0, 120.0, 8.0},  // Figs
        {4.5, 1.11, 45500.0, 65.0, 11.0},  // Pomegranates
        {5.5, 2.00, 45500.0, 65.0, 12.0}   // Lemons
    };
    public static int getNumberOfPlants() {
        return plantsNames.length;
    }
}