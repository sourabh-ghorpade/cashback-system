package consumers;

import commons.DatastoreSingleton;
import commons.Merchant;
import commons.Transaction;

/**
 * Created by apple on 14/01/17.
 */
public class DataStore {

    public void updateMerchantCashback(String merchantId, int cashbackPercentage) {
        Merchant merchant = getMerchant(merchantId);
        merchant.setCashbackPercentage(cashbackPercentage);

    }

    public void addTransaction(Transaction transaction) {
        DatastoreSingleton.getInstance().save(transaction);
        Merchant merchant = getMerchant(transaction.getMerchantName());
        merchant.getTransactions().add(transaction);
        DatastoreSingleton.getInstance().save(merchant);
    }

    public Merchant getMerchant(String merchantName) {
        return DatastoreSingleton.getInstance().find(Merchant.class).field("name").equal(merchantName).get();
    }
}
