package consumers;

import commons.Merchant;

/**
 * Created by apple on 14/01/17.
 */
public class MerchantCashbackDataStore {

    public void update(String merchantId, int cashbackPercentage) {
        Merchant merchant = commons.Datastore.getInstance().find(Merchant.class).field("name").equal(merchantId).get();
        merchant.setCashbackPercentage(cashbackPercentage);
        commons.Datastore.getInstance().save(merchant);
    }
}
