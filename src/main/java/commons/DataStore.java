package commons;

import org.mongodb.morphia.Datastore;

import java.time.LocalDateTime;

/**
 * Created by apple on 14/01/17.
 */
public class DataStore {

    public void updateMerchantCashback(String merchantId, int cashbackPercentage) {
        Merchant merchant = getMerchant(merchantId);
        merchant.setCashbackPercentage(cashbackPercentage);
        dataStore().save(merchant);
    }

    public void updateMerchant(Merchant merchant) {
        dataStore().save(merchant);
    }

    public void addTransaction(Transaction transaction) {
        dataStore().save(transaction);
        Merchant merchant = getMerchant(transaction.getMerchantName());
        merchant.getTransactions().add(transaction);
        dataStore().save(merchant);
    }

    public Merchant getMerchant(String merchantName) {
        return dataStore().find(Merchant.class).field("name").equal(merchantName).get();
    }

    public void getMerchantTransactionsByDate(Merchant merchant, LocalDateTime transactedAt) {
        LocalDateTime lastWeekDayStartTime = transactedAt.minusDays(7).withHour(0);
        LocalDateTime lastWeekDayendTime = transactedAt.minusDays(7).withHour(24);
        dataStore().createQuery(Transaction.class)
                .filter("transactedAt >=", lastWeekDayStartTime)
                .filter("transactedAt <=", lastWeekDayendTime)
                .filter("merchantName = ", merchant.getName());
    }

    private Datastore dataStore() {
        return DatastoreSingleton.getInstance();
    }
}
