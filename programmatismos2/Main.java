
import org.apache.commons.math3.optimization.PointValuePair;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the area of the field (in square meters): ");
        int area = scanner.nextInt();

        System.out.println("Enter the labor cost per hour: ");
        double laborCost = scanner.nextDouble();

        System.out.println("Enter the available water for irrigation (in liters): ");
        int availableWater = scanner.nextInt();
        
        if (area <= 0 || laborCost <= 0 || availableWater <= 0) {
            System.out.println("Please enter valid positive values!");
            return;
        }
        PlantsData plants = new PlantsData(area, laborCost, availableWater);

        OptimizationEngine engine = new OptimizationEngine(plants);
        PointValuePair result = engine.optimize();
        ReportGenerator report = new ReportGenerator();
        report.generateReport(result, plants);

        scanner.close();
    }
}