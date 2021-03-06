package producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import commons.JacksonReadingSerializer;
import commons.Transaction;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class TransactionEventProducer {
    public static void main(String args[]) throws IOException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        Producer<String, String> producer = new KafkaProducer<>(props);
        System.out.println("Producing");
        for (int i = 0; i < 100; i++) {
            int value = generateHighValueTransaction(i) ? 1500 : 500;
            System.out.println("Transaction created of amount" + value);
            if(generateHighValueTransaction(i)) {
                Thread.sleep(500);
            }
            Transaction transaction = new Transaction("Supermans Juice Counter", new Date(), value);
            byte[] serialize = new JacksonReadingSerializer(new ObjectMapper()).serialize("", transaction);
            producer.send(new ProducerRecord<>("transactions", new String(serialize)));
        }

        producer.close();
    }

    private static boolean generateHighValueTransaction(int i) {
        return i % 10 == 0;
    }
}
