// Tomer Tasa 211880778
// Guy Forsht 211547799

import java.util.Scanner;
    public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the name of the College: ");
            String collegeName = scanner.nextLine();
            CollegeManager manager = new CollegeManager(collegeName);
            manager.run(scanner);
            scanner.close();
        }
    }