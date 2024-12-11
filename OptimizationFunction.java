package com.example;

public class OptimizationFunction {

    public static double calculateOptimization(int n, double[] r, double[] C, double C_L, double[] H, double W_A, double[] X) {
        if (r.length != n || C.length != n || H.length != n || X.length != n) {
            throw new IllegalArgumentException("Όλοι οι πίνακες πρέπει να έχουν μήκος ίσο με το n.");
        }

        double Z = 0.0;

        for (int i = 0; i < n; i++) {
            Z += (r[i] - C[i] - (C_L * H[i]) - W_A) * X[i];
        }

        return Z;
    }

    public static void main(String[] args) {
        // Παράδειγμα εισόδων
        int n = 10; // Αριθμός επαναλήψεων
        double[] r = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100}; // Έσοδα
        double[] C = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5}; // Κόστος
        double C_L = 2; // Κόστος Εργατοωρών
        double[] H = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // Απαιτούμενες εργατοώρες
        double W_A = 3; // Κόστος νερού
        double[] X = {1, 0, 1, 0, 1, 1, 0, 1, 0, 1}; // Φυτά

        double result = calculateOptimization(n, r, C, C_L, H, W_A, X);
        System.out.println("Η τιμή της συνάρτησης βελτιστοποίησης Z είναι: " + result);
    }
}

