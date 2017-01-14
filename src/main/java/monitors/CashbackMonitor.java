package monitors;

import commons.DataStore;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class CashbackMonitor {
    public static void main(String args[]) throws InterruptedException {
        List<String> merchantNames = Arrays.asList("Supermans Juice Counter", "Batmans Juice Counter");
        DataStore dataStore = new DataStore();
        while (true) {
            Thread.sleep(2000);
            System.out.println("fetching cashbacks = " + LocalDateTime.now());
            for (String merchantName : merchantNames) {
                System.out.println(merchantName + " CashbackPercentage() = " + dataStore.getMerchant(merchantName).getCashbackPercentage());
            }
        }
    }
}
