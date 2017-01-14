package commons;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;

/**
 * Created by apple on 14/01/17.
 */
public class Datastore {

    private static org.mongodb.morphia.Datastore datastore;

    public static org.mongodb.morphia.Datastore getInstance() {
        if (datastore == null) {
            final Morphia morphia = new Morphia();
            morphia.mapPackage("commons");
            datastore = morphia.createDatastore(new MongoClient(), "payments_system");
            datastore.ensureIndexes();
            return datastore;
        } else {
            return datastore;
        }

    }
}
