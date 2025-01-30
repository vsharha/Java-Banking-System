import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TODO: Verify login in some functions
//TODO: Generate random IDs, make sure they are unique
//TODO: Fix log out and quit
//TODO: Add password, name and insurance number checking

//TODO: Add list scrolling

//TODO: Change ID to card number
//TODO: give the user their id
//TODO: add transfer functionality

//TODO: Add time stamps for transactions

//TODO: Use safe random generation
//TODO: Use password hashes
//TODO: Store data in database

public class Account {
    private int balance = 0;
    private String firstName;
    private String lastName;
    private String insuranceNumber;
    private String password;
    private int ID;
    private boolean loginVerified = false;

    private List<Integer> transactions = new ArrayList<>();

    public static int readAmount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount > $");
        double dollarAmount = scanner.nextDouble();
        int centAmount = (int)Math.round(dollarAmount*100);

        if (centAmount <= 0) {
            System.out.println("Error: Enter a positive amount.");
            return 0;
        }

        return centAmount;
    }

    public static String formatAmount(int amount) {
        return String.format("$%.2f", amount / 100.0);
    }

    public static int generateID() {
        return 1;
    }

    public static void pause() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Press any key to continue...");
        scanner.nextLine();
    }

    public void menu() {
        while(true) {
            System.out.println("-=- MENU -=-");
            System.out.println("1. View balance");
            System.out.println("2. View transaction history");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println();
            System.out.println("5. Log out");
            System.out.println();
            System.out.println("Q - quit");
            System.out.println();
            System.out.print("> ");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();

            System.out.println();

            switch (input) {
                case "1":
                    this.viewBalance();
                    break;
                case "2":
                    this.viewTransactions();
                    break;
                case "3":
                    this.deposit();
                    break;
                case "4":
                    this.withdraw();
                    break;
                case "5":
                    this.logout();
                    return;
                case "q":
                    return;
                default:
                    System.out.println("Invalid input");
                    break;
            }

            pause();
            for (int i = 0; i < 5; i++) {
                System.out.println();
            }
        }
    }

    private void viewBalance() {
        System.out.println("Your balance is " + formatAmount(this.balance));
    }

    private void viewTransactions() {
        System.out.println("Transaction history:");

        int i;
        for(i = 0; i < transactions.size(); i++) {
            System.out.println(i+1 + ". ");
            int displayAmount = transactions.get(i);
            if(displayAmount < 0) {
                displayAmount = -displayAmount;
                System.out.println("Withdrawal");
            } else {
                System.out.println("Deposit");
            }
            System.out.println(formatAmount(displayAmount));
            System.out.println();
        }

        if (i == 0) {
            System.out.println("No transactions yet");
        }
    }

    private void deposit() {
        int amount = readAmount();

        this.changeBalance(amount);
    }

    private void withdraw() {
        int amount = readAmount();

        if(amount <= this.balance) {
            this.changeBalance(-amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }

    private void changeBalance(int amount) {
        this.balance += amount;

        if (amount != 0) {
            transactions.add(amount);
        }

        System.out.println("Your balance is now " + formatAmount(this.balance));
    }

    public boolean getLoginStatus() {
        return this.loginVerified;
    }

    public int getID() {
        return this.ID;
    }

    public String getInsuranceNumber() {
        return this.insuranceNumber;
    }

    private boolean setFirstName(String firstName) {
        if (checkName(firstName)) {
            this.firstName = firstName;
            return true;
        }
        return false;
    }

    private boolean setLastName(String lastName) {
        if (checkName(lastName)) {
            this.lastName = lastName;
            return true;
        }
        return false;
    }

    private boolean setPassword(String password) {
        if(checkPassword(password)) {
            this.password = password;
            return true;
        }
        return false;
    }

    private boolean setInsuranceNumber(String insuranceNumber) {
        if(checkInsuranceNumber(insuranceNumber)) {
            this.insuranceNumber = insuranceNumber;
            return true;
        }

        return false;
    }

    public void login() {
        if (this.loginVerified) {
            System.out.println("Already logged in");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Password > ");
        String password = scanner.nextLine();

        if (password.equals(this.password)) {
            this.loginVerified = true;
        }
    }

    public void logout() {
        loginVerified = false;
    }

    public int register() {
        if(this.ID != 0) {
            System.out.println("Error: Account already registered");
            return this.ID;
        }
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.print("First name > ");
            String firstName = scanner.nextLine();
            if (this.setFirstName(firstName))
                break;
        }

        while(true) {
            System.out.print("Surname    > ");
            String lastName = scanner.nextLine();
            if (this.setLastName(lastName))
                break;
        }

        while(true) {
            System.out.print("Insurance number > ");
            String insuranceNumber = scanner.nextLine();
            if (this.setInsuranceNumber(insuranceNumber))
                break;
        }

        while(true) {
            System.out.println();
            System.out.print("Enter password   > ");
            String password = scanner.nextLine();

            while(true) {
                System.out.print("Repeat password  > ");

                if (scanner.nextLine().equals(password)) {
                    break;
                }

                System.out.println("Error: Passwords must match.");
            }

            if (this.setPassword(password))
                break;
        }

        this.ID = generateID();

        System.out.println();
        System.out.println(firstName + " " + lastName + ", welcome to Bank!");
        return this.ID;
    }

    private static boolean checkName(String name) {
        return true;
    }

    private static boolean checkPassword(String password) {
        return true;
    }

    private static boolean checkInsuranceNumber(String insuranceNumber) {
        return true;
    }
}
