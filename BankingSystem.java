import java.util.Scanner;

public class BankingSystem {

    static String[][] accounts = new String[100][4];
    static String[][] loans = new String[100][2];

    static int accountCount = 0;
    static Scanner scan = new Scanner(System.in);

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

            int choice = scan.nextInt();

            switch (choice) {
                case 1 -> createAccount();
                default -> {
                    System.out.println("Invalid choice! Press Enter to continue...");
                    scan.nextLine(); scan.nextLine();
                }
            }
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

    public static void createAccount() {
        scan.nextLine();
        System.out.print("Enter Name: ");
        accounts[accountCount][0] = scan.nextLine();
        System.out.print("Enter Account Number: ");
        accounts[accountCount][1] = scan.nextLine();
        System.out.print("Enter Account Type (Savings / Current / Wanitha Wasana): ");
        accounts[accountCount][2] = scan.nextLine();
        System.out.print("Enter Deposit Amount: ");
        accounts[accountCount][3] = scan.nextLine();

        loans[accountCount][0] = "0";
        loans[accountCount][1] = "";

        accountCount++;
        System.out.println("Account created successfully! Press Enter to go to menu...");
        scan.nextLine();
    }

}
