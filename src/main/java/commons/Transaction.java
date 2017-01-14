package commons;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity("payments_transactions")
public class Transaction  {
    public Transaction(){}
    public Transaction(String merchantName, int amount){
        this.merchantName = merchantName;
        this.amount = amount;
    }
    @Id
    private ObjectId id;

    private String merchantName;
    @Property
    private int amount;

    public String getMerchantName() {
        return merchantName;
    }

    public int getAmount() {
        return amount;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
