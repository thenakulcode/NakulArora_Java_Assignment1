import java.util.Scanner;

// Account class to store account information and operations
class Account {
    int accountNumber;
    String accountHolderName;
    double balance;
    String email;
    String phoneNumber;

    // Constructor
    public Account(int accNum, String name, double initialBalance, String email, String phone) {
        this.accountNumber = accNum;
        this.accountHolderName = name;
        this.balance = initialBalance;
        this.email = email;
        this.phoneNumber = phone;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully. Current Balance: " + balance);
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawal successful. Current Balance: " + balance);
            } else {
                System.out.println("Insufficient balance!");
            }
        } else {
            System.out.println("Invalid amount. Withdrawal failed.");
        }
    }

    // Method to display account details
    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }

    // Method to update contact details
    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated successfully.");
    }
}

// UserInterface class to manage multiple accounts
public class BankingApp {
    static Account[] accounts = new Account[100]; // max 100 accounts
    static int accountCount = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
    }

    // Display main menu
    public static void mainMenu() {
        int choice;
        do {
            System.out.println("\nWelcome to the Banking Application!");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdrawal();
                    break;
                case 4:
                    showAccountDetails();
                    break;
                case 5:
                    updateContact();
                    break;
                case 6:
                    System.out.println("Thank you for using the Banking App!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 6);
    }

    // Create a new account
    public static void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = sc.nextDouble();
        sc.nextLine(); // consume newline
        System.out.print("Enter email address: ");
        String email = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        int accNum = 1000 + accountCount + 1; // simple account number
        accounts[accountCount] = new Account(accNum, name, initialDeposit, email, phone);
        accountCount++;
        System.out.println("Account created successfully with Account Number: " + accNum);
    }

    // Deposit money into an account
    public static void performDeposit() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        Account acc = findAccount(accNum);
        if (acc != null) {
            System.out.print("Enter amount to deposit: ");
            double amt = sc.nextDouble();
            acc.deposit(amt);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Withdraw money from an account
    public static void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        Account acc = findAccount(accNum);
        if (acc != null) {
            System.out.print("Enter amount to withdraw: ");
            double amt = sc.nextDouble();
            acc.withdraw(amt);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Show account details
    public static void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        Account acc = findAccount(accNum);
        if (acc != null) {
            acc.displayAccountDetails();
        } else {
            System.out.println("Account not found!");
        }
    }

    // Update contact details
    public static void updateContact() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        sc.nextLine(); // consume newline
        Account acc = findAccount(accNum);
        if (acc != null) {
            System.out.print("Enter new email: ");
            String email = sc.nextLine();
            System.out.print("Enter new phone number: ");
            String phone = sc.nextLine();
            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Find account by account number
    public static Account findAccount(int accNum) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].accountNumber == accNum) {
                return accounts[i];
            }
        }
        return null;
    }
}
