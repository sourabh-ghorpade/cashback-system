package seeds;

import commons.DatastoreSingleton;

import java.util.Arrays;
import java.util.List;

/**
 * Created by apple on 14/01/17.
 */
public class SeedData {
    public static void main(String args[]) {
        List<commons.Merchant> merchants = Arrays.asList(new commons.Merchant("Supermans Juice Counter", 10),
                new commons.Merchant("Batmans Juice Counter", 10));
        DatastoreSingleton.getInstance().save(merchants);
    }
}
