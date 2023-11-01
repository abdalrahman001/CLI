import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a command: ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                System.out.println("Exiting the program.");
                break;
            }

            Parser parser = new Parser();
            boolean parsed = parser.parse(input);

            if (parsed) {
                System.out.println("Command Name: " + parser.getCommandName());
                System.out.println("Options: " + String.join(" ", parser.getOptions()));
                System.out.println("Arguments: " + String.join(" ", parser.getArgs()));
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }

        scanner.close();
    }
}
