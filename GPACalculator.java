import java.util.*;

public class GPACalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== GPA Calculator (Java) ===");
        System.out.println("Scale: A=4.0, A-=3.7, B+=3.3 ... F=0.0");
        System.out.println("Weights: Honors +0.5, AP +1.0 (capped at 5.0)\n");

        boolean again = true;
        while (again) {
            int n = readInt(sc, "How many courses? (1-20): ", 1, 20);

            String[] courseNames = new String[n];
            String[] grades = new String[n];
            String[] levels = new String[n];
            double[] credits = new double[n];
            double[] unweightedPts = new double[n];
            double[] weightedPts = new double[n];

            double totalCredits = 0.0;
            double sumUnweighted = 0.0;
            double sumWeighted = 0.0;

            for (int i = 0; i < n; i++) {
                System.out.println("\nCourse " + (i + 1) + ":");
                courseNames[i] = readNonEmptyLine(sc, "  Course name: ");
                grades[i] = readGrade(sc, "  Letter grade (A, A-, B+ ... F): ");
                credits[i] = readDouble(sc, "  Credits (e.g., 5.0): ", 0.1, 100);
                levels[i] = readLevel(sc, "  Level (R/H/AP): ");

                double base = gradeToPoints(grades[i]);
                double w = base + levelBonus(levels[i]);
                if (w > 5.0) w = 5.0; // cap

                unweightedPts[i] = base;
                weightedPts[i] = w;

                totalCredits += credits[i];
                sumUnweighted += base * credits[i];
                sumWeighted += w * credits[i];
            }

            double unweightedGPA = sumUnweighted / totalCredits;
            double weightedGPA = sumWeighted / totalCredits;

            // Print table
            System.out.println("\n=== Summary Table ===");
            System.out.printf("%-20s %-6s %-6s %-8s %-7s %-7s%n",
                    "Course", "Grade", "Level", "Credits", "UnwPts", "WtdPts");

            for (int i = 0; i < n; i++) {
                System.out.printf(Locale.US, "%-20s %-6s %-6s %-8.1f %-7.2f %-7.2f%n",
                        shorten(courseNames[i], 20), grades[i], levels[i], credits[i], unweightedPts[i], weightedPts[i]);
            }

            System.out.println("\n=== GPA Results ===");
            System.out.printf(Locale.US, "Total credits: %.2f%n", totalCredits);
            System.out.printf(Locale.US, "Unweighted GPA: %.3f%n", unweightedGPA);
            System.out.printf(Locale.US, "Weighted GPA:   %.3f%n", weightedGPA);

            again = readYesNo(sc, "\nCalculate another GPA? (y/n): ");
            System.out.println();
        }

        System.out.println("Goodbye!");
        sc.close();
    }

    // --------- GPA RULES (A: with +/-) ---------
    private static double gradeToPoints(String g) {
        switch (g) {
            case "A+": return 4.0;  // common cap
            case "A":  return 4.0;
            case "A-": return 3.7;
            case "B+": return 3.3;
            case "B":  return 3.0;
            case "B-": return 2.7;
            case "C+": return 2.3;
            case "C":  return 2.0;
            case "C-": return 1.7;
            case "D+": return 1.3;
            case "D":  return 1.0;
            case "D-": return 0.7;
            case "F":  return 0.0;
            default:   return 0.0;
        }
    }

    private static double levelBonus(String level) {
        if (level.equals("H")) return 0.5;
        if (level.equals("AP")) return 1.0;
        return 0.0;
    }

    private static String shorten(String s, int maxLen) {
        if (s.length() <= maxLen) return s;
        return s.substring(0, maxLen - 3) + "...";
    }

    // --------- INPUT HELPERS ---------
    private static int readInt(Scanner sc, String prompt, int lo, int hi) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(line);
                if (v < lo || v > hi) {
                    System.out.println("Please enter an integer from " + lo + " to " + hi + ".");
                } else return v;
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Try again.");
            }
        }
    }

    private static double readDouble(Scanner sc, String prompt, double lo, double hi) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                double v = Double.parseDouble(line);
                if (v < lo || v > hi) {
                    System.out.println("Please enter a number from " + lo + " to " + hi + ".");
                } else return v;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private static String readNonEmptyLine(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Cannot be empty. Try again.");
        }
    }

    private static String readGrade(Scanner sc, String prompt) {
        Set<String> allowed = new HashSet<>(Arrays.asList(
                "A+","A","A-","B+","B","B-","C+","C","C-","D+","D","D-","F"
        ));
        while (true) {
            System.out.print(prompt);
            String g = sc.nextLine().trim().toUpperCase();
            if (allowed.contains(g)) return g;
            System.out.println("Invalid grade. Use A, A-, B+, B, ... or F.");
        }
    }

    private static String readLevel(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String lvl = sc.nextLine().trim().toUpperCase();
            if (lvl.equals("R") || lvl.equals("H") || lvl.equals("AP")) return lvl;
            System.out.println("Invalid level. Enter R, H, or AP.");
        }
    }

    private static boolean readYesNo(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim().toLowerCase();
            if (s.equals("y") || s.equals("yes")) return true;
            if (s.equals("n") || s.equals("no")) return false;
            System.out.println("Please enter y or n.");
        }
    }
}
