package seeds;

import java.util.Arrays;
import java.util.List;

/**
 * Created by apple on 14/01/17.
 */
public class Merchant {
    public static void main(String args[]) {
        List<commons.Merchant> merchants = Arrays.asList(new commons.Merchant("Supermans Juice Counter", 0),
                new commons.Merchant("Batmans Juice Counter", 0));
        commons.Datastore.getInstance().save(merchants);
    }
}
