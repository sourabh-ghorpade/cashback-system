package commons;

public class Transaction  {
    public Transaction(int merchantId, int amount){
        this.merchantId = merchantId;
        this.amount = amount;
    }

    private int merchantId;
    private int amount;

    public int getMerchantId() {
        return merchantId;
    }

    public int getAmount() {
        return amount;
    }
}
