package commons;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.Date;

@Entity("payments_transactions")
public class Transaction  {
    public Transaction(){}
    public Transaction(String merchantName, Date transactedAt, int amount){
        this.merchantName = merchantName;
        this.transactedAt = transactedAt;
        this.amount = amount;
    }
    @Id
    private ObjectId id;

    private String merchantName;
    @Property
    private int amount;
    @Property
    private Date transactedAt;

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

    public Date getTransactedAt() {
        return transactedAt;
    }

    public void setTransactedAt(Date transactedAt) {
        this.transactedAt = transactedAt;
    }
}
