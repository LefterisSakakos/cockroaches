# Cockroaches - Farm Optimization Program

Welcome to **Cockroaches**, a Java-based program designed to help farmers optimize the use of their resources for maximum profit. This program takes input about the size of the farm, available water, and labor costs, and calculates the optimal amount of each plant to cultivate based on the given constraints.

## Features
#### User Input:

- Field size in square meters.
- Available water for irrigation in liters.
- Labor cost per hour in euros.
#### Optimization:
- Calculates the optimal number of plants for each crop type based on user input and predefined plant data.
- Considers factors like planting area, labor hours, water usage, and associated costs for each plant type.
- Maximizes total profit for the farmer.

## Technology Stack
- Programming Language: Java
- Optimization Library: Apache Commons Math3
- IDE/Tools: Compatible with IntelliJ IDEA, Eclipse, or any Java-based IDE.
  

## Installation Instructions
#### Prerequisites
1. Install Java JDK 8+.
2. Add the Apache Commons Math3 library to your project.
3. Clone or download the repository:
   ```bash
   git clone https://github.com/username/cockroaches.git

4. Open the files in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).
5. Navigate to the project directory and compile the program:
javac Main.java  
 6. Run the programm using the code bellow....

## Project Structure   
#### Below is an overview of the repository structure and the role of each file:
cockroaches/  
├── Main.java               # Entry point of the program  
├── OptimizationEngine.java # Contains the optimization logic using Apache Commons Math3  
├── PlantsData.java         # Handles user input and stores field-specific data  
├── PlantsDB.java           # Defines fixed data for various plant types  
├── ReportGenerator.java    # Generates detailed output reports  
└── README.md               # Documentation of the project  

Usage
When you run the program, you will be prompted to input data about the plants, such as:

Name of the plant
Water required per unit
Land required per unit (in acres)
Labor hours required per unit
Climate type (options: compatible, dry, wet)
Profit per unit
The program will then compute the optimal quantity of each plant to cultivate based on the available resources and constraints.



Running Example:
Καλησπέρα 
Ποία είναι η έκταση του χωραφιού σε τετραγωνικά μέτρα;
500
Ποίο είναι το κόστος της ώρας εργασίας σε ευρώ;
3,5
Πόσα λίτρα διαθέσιμο νερό υπάρχει ;
400000

[...]

Υπολογισμός ιδανικού συνδιασμού.....
Φυτό 1:σταφύλι , ποσότητα : 8 , κέρδος 240.00
Φυτό 2:Ελιές , ποσότητα : 5 < κέρδος 170.00

Repository Structure
FarmOptimizer.java: The main class of the program. It handles user input, initializes constraints, and triggers the optimization process.
Plant.java: Represents a plant with attributes such as name, water requirements, land usage, labor hours, and profit per unit.
Constraint.java: Represents a resource constraint (e.g., water, land, labor) and checks whether a given plant quantity satisfies the constraint.
OptimizationEngine.java: Implements the logic for finding the optimal cultivation plan based on the given plants and constraints.

Example Code
Plant.java
This class models a plant:
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

Constraint.java
This class models a resource constraint:
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


License
This project is licensed under the MIT License. See the LICENSE file for details.

Contributing
Dear user Thank you for your interest in our program! We’re thrilled that you’ve taken the time to explore Cockroaches and hope it helps you optimize your farming processes effectively. Your feedback and suggestions are always welcome, as they help us improve and grow. We appreciate your support and hope you enjoy using our program!"
If you'd like to contribute, feel free to submit a pull request or open an issue.


