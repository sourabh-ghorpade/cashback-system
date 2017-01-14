package commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Closeable;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class JacksonReadingSerializer implements Closeable, AutoCloseable, Serializer<Transaction>, Deserializer<Transaction> {
    private ObjectMapper mapper;

    public JacksonReadingSerializer() {
        this(null);
    }

    public JacksonReadingSerializer(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public void configure(Map<String, ?> map, boolean b) {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
    }

    @Override
    public byte[] serialize(String s, Transaction transaction) {
        try {
            return mapper.writeValueAsBytes(SerializationHelper.from(transaction));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Transaction deserialize(String s, byte[] bytes) {
        try {
            System.out.println("bytes = " + bytes.toString());
            return mapper.readValue(bytes, SerializationHelper.class).to();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }


    @Override
    public void close() {
        mapper = null;
    }

    public static class SerializationHelper {
        public String merchantId;
        public int amount;
        public Date transactedAt;

        public static SerializationHelper from(Transaction transaction) {
            SerializationHelper helper = new SerializationHelper();
            helper.merchantId = transaction.getMerchantName();
            helper.transactedAt = transaction.getTransactedAt();
            helper.amount = transaction.getAmount();

            return helper;
        }

        public Transaction to() {
            return new Transaction(merchantId, transactedAt, amount);
        }
    }
}