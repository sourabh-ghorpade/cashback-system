package commons;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity("payments_merchants")
public class Merchant {
    public Merchant(){}
    public Merchant(String name, float cashbackPercentage) {
        this.name = name;
        this.cashbackPercentage = cashbackPercentage;
    }
    @Id
    private ObjectId id;
    @Property
    private String name;
    @Property
    private float cashbackPercentage;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCashbackPercentage() {
        return cashbackPercentage;
    }

    public void setCashbackPercentage(float cashbackPercentage) {
        this.cashbackPercentage = cashbackPercentage;
    }

}
