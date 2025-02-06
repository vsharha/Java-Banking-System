import java.util.Date;

public class Transaction {
    final private int amount;
    final private String insuranceNumber;
    final private Date timestamp;

    Transaction (int amount, String insuranceNumber) {
        this.amount = amount;
        this.insuranceNumber = insuranceNumber;
        this.timestamp = new Date();
    }

    int getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        String output = "";
        int displayAmount = this.amount;
        if(displayAmount < 0) {
            output += "Withdrew ";
            displayAmount = -displayAmount;
        } else {
            output += "Deposited ";
        }
        output += Account.formatAmount(displayAmount);

        output += " on " + this.timestamp;
        return output;
    }
}
