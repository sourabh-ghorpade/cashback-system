package producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import commons.JacksonReadingSerializer;
import commons.Transaction;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.util.Properties;

public class TransactionEventProducer {
    public static void main(String args[]) throws IOException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        Producer<String, String> producer = new KafkaProducer<>(props);
        System.out.println("Producing");
        for (int i = 0; i < 100; i++) {
            System.out.println("Producing with amount" + i);
            Transaction transaction = new Transaction(1, i);
            byte[] serialize = new JacksonReadingSerializer(new ObjectMapper()).serialize("", transaction);
            producer.send(new ProducerRecord<>("transactions", new String(serialize)));
        }

        producer.close();
    }
}
