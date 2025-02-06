import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {
    private static final int LENGTH_ID = 8;
    private static final int BASE = (int)Math.pow(10, LENGTH_ID);

    private Map<Integer, Account> accounts = new HashMap<>();
    private int currentAccountID;

    public void welcome() {
        boolean exit;
        do {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Welcome to Bank. Are you already a client? [Y/N] > ");
                String answer = scanner.nextLine().toLowerCase();
                System.out.println();

                if (answer.equals("y")) {
                    while (true) {
                        if (this.login()) {
                            break;
                        }
                    }
                    break;
                } else if (answer.equals("n")) {
                    this.register();
                    break;
                } else {
                    System.out.println("Error: Invalid input");
                }
            }

            Account.pause();

            for (int i = 0; i < 5; i++) {
                System.out.println();
            }

            exit = this.accounts.get(this.currentAccountID).menu();

            this.currentAccountID = 0;
        } while (!exit);
    }

    private static int generateID() {
        SecureRandom secureRandom = new SecureRandom();
        return BASE + secureRandom.nextInt(9*BASE);
    }

    public void register() {
        Account account = new Account();

        account.register();
        int ID;
        do {
            ID = generateID();
        } while (this.accounts.containsKey(ID));
        account.setID(ID);

        this.accounts.put(ID, account);
        this.currentAccountID = ID;
    }

    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insurance number > ");
        String insuranceNumber = scanner.nextLine();

        Account account = getAccount(insuranceNumber);
        if(account != null) {
            if(account.login()) {
                this.currentAccountID = account.getID();

                return true;
            }
        }

        return false;
    }

    public Account getAccount(String insuranceNumber) {
        for(Account account: this.accounts.values()) {
            if(account.getInsuranceNumber().equals(insuranceNumber)) {
                return account;
            }
        }

        System.out.println("Error: Account not found");
        return null;
    }
}