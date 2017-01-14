package consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import commons.JacksonReadingSerializer;
import commons.Transaction;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class TransactionEventConsumer {
    public static void main(String args[]) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("transactions"));
        MerchantCashbackDataStore cashbackStore = new MerchantCashbackDataStore();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                Transaction transaction = new JacksonReadingSerializer(new ObjectMapper())
                        .deserialize("", record.value().getBytes());
                System.out.printf("offset = %d, merchantId = %s, amount = %s\n", record.offset(),
                        transaction.getMerchantId(), transaction.getAmount());
                cashbackStore.update(transaction.getMerchantId(), 10);
            }
        }
    }
}
