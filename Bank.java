import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {
    private Map<Integer, Account> accounts = new HashMap<>();
    private int currentAccountID;

    public void welcome() {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Welcome to Bank. Are you already a client? [Y/N] > ");
            String answer = scanner.nextLine().toLowerCase();
            System.out.println();

            if(answer.equals("y")) {
                while(true) {
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

        for(int i = 0; i<5; i++) {
            System.out.println();
        }

        this.accounts.get(this.currentAccountID).menu();
    }

    public void register() {
        Account account = new Account();

        int ID = account.register();

        this.accounts.put(ID, account);
        this.currentAccountID = ID;
    }

    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insurance number > ");
        String insuranceNumber = scanner.nextLine();

        for(Account account: this.accounts.values()) {
            if(account.getInsuranceNumber().equals(insuranceNumber)) {
                account.login();
                if(account.getLoginStatus()) {
                    this.currentAccountID = account.getID();
                }
                return true;
            }
        }

        System.out.println("Error: Account not found");
        return false;
    }
}