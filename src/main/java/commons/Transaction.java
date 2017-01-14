package commons;

public class Transaction  {
    public Transaction(String merchantId, int amount){
        this.merchantId = merchantId;
        this.amount = amount;
    }

    private String merchantId;
    private int amount;

    public String getMerchantId() {
        return merchantId;
    }

    public int getAmount() {
        return amount;
    }
}
