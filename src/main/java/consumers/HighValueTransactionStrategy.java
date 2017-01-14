package consumers;

import commons.DataStore;
import commons.Merchant;
import commons.Transaction;

public class HighValueTransactionStrategy {
    public static final int HIGH_AMOUNT_TRANSACTION = 1000;
    private Transaction transaction;

    public HighValueTransactionStrategy(Transaction transaction) {
        this.transaction = transaction;
    }

    public void perform() {
        if(transaction.getAmount() > HIGH_AMOUNT_TRANSACTION) {
            DataStore dataStore = new DataStore();
            Merchant merchant = dataStore.getMerchant(transaction.getMerchantName());
            double updatedCashbackPercentage = merchant.getCashbackPercentage() * 1.1;
            System.out.println("updatedCashbackPercentage = " + updatedCashbackPercentage);
            merchant.setCashbackPercentage(updatedCashbackPercentage);
            dataStore.updateMerchant(merchant);
        }
    }
}
