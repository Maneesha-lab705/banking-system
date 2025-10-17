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
                case 1 : createAccount();
                break;
                case 2 : deposit();
                break;
                case 3 : withdraw();
                break;
                case 4 : checkBalance();
                break;
                case 5 : transferFunds();
                break;
                case 6 : applyLoan();
                break;
                case 7 : viewLoanDetails();
                case 8 : {
                    System.out.println("Thank you. Goodbye!");
                    System.exit(0);
                }
                break;
                default : {
                    System.out.println("Invalid choice! Press Enter to go to main menu...");
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
//        scan.nextLine();
        System.out.print("Enter Name: ");
        accounts[accountCount][0] = scan.next();
        System.out.print("Enter Account Number: ");
        accounts[accountCount][1] = String.valueOf(scan.nextInt());
        System.out.print("Enter Account Type (Savings / Current / Wanitha Wasana): ");
        accounts[accountCount][2] = scan.next();
        System.out.print("Enter Deposit Amount: ");
        accounts[accountCount][3] = String.valueOf(scan.nextInt());

        loans[accountCount][0] = "0";
        loans[accountCount][1] = "";

        accountCount++;
        System.out.println("Account created successfully! Press Enter to go to main menu...");
        scan.nextLine();
    }

    public static void deposit() {
        System.out.print("Enter Account Number: ");
        String accNumber = scan.next();
        int index = findAccount(accNumber);
        if (index != -1) {
            System.out.print("Enter Amount to Deposit: ");
            double amount = scan.nextDouble();
            double balance = Double.parseDouble(accounts[index][3]);
            balance += amount;
            accounts[index][3] = String.valueOf(balance);
            System.out.println("Deposit successful! New Balance: " + balance);
        } else {
            System.out.println("Account not found!");
        }
        pause();
    }

    public static void withdraw() {
        System.out.print("Enter Account Number: ");
        String accNumber = scan.next();
        int index = findAccount(accNumber);
        if (index != -1) {
            System.out.print("Enter Amount to Withdraw: ");
            double amount = scan.nextDouble();
            double balance = Double.parseDouble(accounts[index][3]);
            if (amount <= balance) {
                balance -= amount;
                accounts[index][3] = String.valueOf(balance);
                System.out.println("Withdrawal successful! New Balance: " + balance);
            } else {
                System.out.println("Low balance!");
            }
        } else {
            System.out.println("Account not found!");
        }
        pause();
    }

    public static void checkBalance() {
        System.out.print("Enter Account Number: ");
        String accNumber = scan.next();
        int index = findAccount(accNumber);
        if (index != -1) {
            System.out.println("Account Holder: " + accounts[index][0]);
            System.out.println("Account Type: " + accounts[index][2]);
            System.out.println("Balance: " + accounts[index][3]);
        } else {
            System.out.println("Account not found!");
        }
        pause();
    }

    public static void applyLoan() {
        System.out.print("Enter Account Number: ");
        String accNumber = scan.next();
        int index = findAccount(accNumber);
        scan.nextLine();
        if (index != -1) {
            System.out.print("Enter Loan Amount: ");
            loans[index][0] = scan.nextLine();
            System.out.print("Enter Loan Description: ");
            loans[index][1] = scan.nextLine();
            System.out.println("Loan applied successfully!");
        } else {
            System.out.println("Account not found!");
        }
        pause();
    }

    public static void viewLoanDetails() {
        System.out.print("Enter Account Number: ");
        String accNumber = scan.next();
        int index = findAccount(accNumber);
        if (index != -1) {
            System.out.println("Loan Amount: " + loans[index][0]);
            System.out.println("Loan Description: " + loans[index][1]);
        } else {
            System.out.println("Account not found!");
        }
        pause();
    }

    public static void transferFunds() {
        System.out.print("Enter Your Account Number: ");
        String fromAcc = scan.next();
        int fromIndex = findAccount(fromAcc);
        if (fromIndex == -1) {
            System.out.println("Sender account not found!");
            pause();
            return;
        }
        System.out.print("Enter Recipient Account Number: ");
        String toAcc = scan.next();
        int toIndex = findAccount(toAcc);
        if (toIndex == -1) {
            System.out.println("Recipient account not found!");
            pause();
            return;
        }
        System.out.print("Enter Amount to Transfer: ");
        double amount = scan.nextDouble();
        double fromBalance = Double.parseDouble(accounts[fromIndex][3]);
        double toBalance = Double.parseDouble(accounts[toIndex][3]);
        if (amount <= fromBalance) {
            fromBalance -= amount;
            toBalance += amount;
            accounts[fromIndex][3] = String.valueOf(fromBalance);
            accounts[toIndex][3] = String.valueOf(toBalance);
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Insufficient balance!");
        }
        pause();
    }

    public static int findAccount(String accNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i][1].equals(accNumber)) {
                return i;
            }
        }
        return -1;
    }

    public static void pause() {
        System.out.println("Press Enter to to go to main menu...");
        scan.nextLine(); scan.nextLine();
    }
}
