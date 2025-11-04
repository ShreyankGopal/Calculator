package com.example;

public class Calculator {

    // Square root function
    public double squareRoot(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Cannot take square root of negative number");
        }
        return Math.sqrt(x);
    }

    // Factorial function
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Cannot take factorial of negative number");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    //hello
    //Natural log
    // Natural logarithm function
    public double naturalLog(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Logarithm defined only for positive numbers");
        }
        return Math.log(x);
    }

    // Power function
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    // Main method (menu-driven for CLI use)
    public static void main(String[] args) throws java.io.IOException {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        Calculator calc = new Calculator();
        int choice;

        do {
            System.out.println("\n=== Scientific Calculator ===");
            System.out.println("1. Square Root (√x)");
            System.out.println("2. Factorial (!x)");
            System.out.println("3. Natural Logarithm (ln x)");
            System.out.println("4. Power (x^b)");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter number: ");
                        double sq = sc.nextDouble();
                        System.out.println("Result: " + calc.squareRoot(sq));
                        break;
                    case 2:
                        System.out.print("Enter number: ");
                        int f = sc.nextInt();
                        System.out.println("Result: " + calc.factorial(f));
                        break;
                    case 3:
                        System.out.print("Enter number: ");
                        double ln = sc.nextDouble();
                        System.out.println("Result: " + calc.naturalLog(ln));
                        break;
                    case 4:
                        System.out.print("Enter base: ");
                        double base = sc.nextDouble();
                        System.out.print("Enter exponent: ");
                        double exp = sc.nextDouble();
                        System.out.println("Result: " + calc.power(base, exp));
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 5);

        sc.close();
    }
}
