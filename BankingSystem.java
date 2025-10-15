import java.util.Scanner;

public class BankingSystem {

    public static void main(String[] args) {
        while (true) {
            clearConsole();
            System.out.println("==== Welcome to Banking System ====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer Funds");
            System.out.println("6. Apply for Loan");
            System.out.println("7. View Loan Details");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

        }
    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
