import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {
    final static int lengthID = 8;

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
        return (int)Math.pow(10, lengthID) + secureRandom.nextInt(9*(int)Math.pow(10, lengthID));
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

        for(Account account: this.accounts.values()) {
            if(account.getInsuranceNumber().equals(insuranceNumber)) {
                if(account.login()) {
                    this.currentAccountID = account.getID();

                    return true;
                }
                return false;
            }
        }

        System.out.println("Error: Account not found");
        return false;
    }
}