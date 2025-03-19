import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private int accountNumber;
    private String accountHolder;
    private double balance;

    public Account(int accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    public void displayBalance() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: $" + balance);
    }
}

public class BankingApplication {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nWelcome to Simple Banking Application!");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

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
                    checkBalance();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you for using the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid one.");
                    break;
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();

        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();

        int accountNumber = accounts.size() + 1;
        Account newAccount = new Account(accountNumber, name, initialDeposit);
        accounts.add(newAccount);

        System.out.println("Account created successfully. Your account number is: " + accountNumber);
    }

    private static void performDeposit() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();

        Account account = findAccount(accountNumber);

        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();

        Account account = findAccount(accountNumber);

        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void checkBalance() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();

        Account account = findAccount(accountNumber);

        if (account != null) {
            account.displayBalance();
        } else {
            System.out.println("Account not found.");
        }
    }

    private static Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}
