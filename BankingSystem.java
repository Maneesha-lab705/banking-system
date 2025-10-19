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
                case 1 : {
                    boolean isCreateAccount = createAccount();
                    if (isCreateAccount) {
                        System.out.println("Account created successfully!");
                    } else {
                        System.out.println("Account creation failed!");
                    }
                    pause();
                }

                case 2 : {
                    boolean isDeposit = deposit();
                    if (isDeposit) {
                        System.out.println("Deposit completed successfully!");
                    } else {
                        System.out.println("Deposit failed!");
                    }
                    pause();
                }

                case 3 : {
                    boolean isWithdraw = withdraw();
                    if (isWithdraw) {
                        System.out.println("Withdrawal successful!");
                    } else {
                        System.out.println("Withdrawal failed!");
                    }
                    pause();
                }

                case 4 : {
                    String balanceInfo = checkBalance();
                    if (balanceInfo != null) {
                        System.out.println(balanceInfo);
                    } else {
                        System.out.println("Account not found!");
                    }
                    pause();
                }

                case 5 : {
                    boolean isTransfer = transferFunds();
                    if (isTransfer) {
                        System.out.println("Funds transferred successfully!");
                    } else {
                        System.out.println("Fund transfer failed!");
                    }
                    pause();
                }

                case 6 :{
                    boolean isLoanApplied = applyLoan();
                    if (isLoanApplied) {
                        System.out.println("Loan applied successfully!");
                    } else {
                        System.out.println("Loan application failed!");
                    }
                    pause();
                }

                case 7 : {
                    String loanInfo = viewLoanDetails();
                    if (loanInfo != null) {
                        System.out.println(loanInfo);
                    } else {
                        System.out.println("Loan details not found!");
                    }
                    pause();
                }

                case 8 :{
                    System.out.println("Thank you. Goodbye!");
                    System.exit(0);
                }

                default : {
                    System.out.println("Invalid choice! Press Enter to go to main menu...");
                    scan.nextLine();
                    scan.nextLine();
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

    public static void pause() {
        System.out.println("Press Enter to go to main menu...");
        scan.nextLine();
        scan.nextLine();
    }

    public static boolean createAccount() {
        System.out.print("Enter Name: ");
        String name = scan.next();
        System.out.print("Enter Account Number: ");
        String accNum = scan.next();
        System.out.print("Enter Account Type (Savings / Current / Wanitha Wasana): ");
        String accType = scan.next();
        System.out.print("Enter Deposit Amount: ");
        double deposit = scan.nextDouble();

        if (findAccount(accNum) != -1) {
            System.out.println("Account number already exists!");
            return false;
        }

        accounts[accountCount][0] = name;
        accounts[accountCount][1] = accNum;
        accounts[accountCount][2] = accType;
        accounts[accountCount][3] = String.valueOf(deposit);

        loans[accountCount][0] = "0";
        loans[accountCount][1] = "";

        accountCount++;
        return true;
    }

    public static boolean deposit() {
        System.out.print("Enter Account Number: ");
        String accNumber = scan.next();
        int index = findAccount(accNumber);
        if (index != -1) {
            System.out.print("Enter Amount to Deposit: ");
            double amount = scan.nextDouble();
            double balance = Double.parseDouble(accounts[index][3]);
            balance += amount;
            accounts[index][3] = String.valueOf(balance);
            System.out.println("New Balance: " + balance);
            return true;
        }else {
            System.out.println("Account not found!");
            return false;
        }
    }

    public static boolean withdraw() {
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
                System.out.println("New Balance: " + balance);
                return true;
            } else {
                System.out.println("Insufficient funds!");
                return false;
            }
        }else {
            System.out.println("Account not found!");
            return false;
        }
    }

    public static String checkBalance() {
        System.out.print("Enter Account Number: ");
        String accNumber = scan.next();
        int index = findAccount(accNumber);
        if (index != -1) {
            return "Account Holder: " + accounts[index][0] +
                    "\nAccount Type: " + accounts[index][2] +
                    "\nBalance: " + accounts[index][3];
        }else {
            System.out.println("Account not found!");
            return null;
        }
    }

    public static boolean applyLoan() {
        System.out.print("Enter Account Number: ");
        String accNumber = scan.next();
        int index = findAccount(accNumber);
        scan.nextLine();
        if (index != -1) {
            System.out.print("Enter Loan Amount: ");
            loans[index][0] = scan.nextLine();
            System.out.print("Enter Loan Description: ");
            loans[index][1] = scan.nextLine();
            return true;
        }else {
            System.out.println("Account not found!");
            return false;
        }
    }

    public static String viewLoanDetails() {
        System.out.print("Enter Account Number: ");
        String accNumber = scan.next();
        int index = findAccount(accNumber);
        if (index != -1) {
            return "Loan Amount: " + loans[index][0] +
                    "\nLoan Description: " + loans[index][1];
        }else {
            System.out.println("Account not found!");
            return null;
        }
    }

    public static boolean transferFunds() {
        System.out.print("Enter Your Account Number: ");
        String fromAcc = scan.next();
        int fromIndex = findAccount(fromAcc);
        if (fromIndex == -1) {
            System.out.println("Sender account not found!");
            return false;
        }

        System.out.print("Enter Recipient Account Number: ");
        String toAcc = scan.next();
        int toIndex = findAccount(toAcc);
        if (toIndex == -1) {
            System.out.println("Recipient account not found!");
            return false;
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
            return true;
        } else {
            System.out.println("Insufficient balance!");
            return false;
        }
    }

    public static int findAccount(String accNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i][1].equals(accNumber)) {
                return i;
            }
        }
        return -1;
    }
}
