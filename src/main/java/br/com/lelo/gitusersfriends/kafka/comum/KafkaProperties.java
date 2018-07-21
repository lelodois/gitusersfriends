package br.com.lelo.gitusersfriends.kafka.comum;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class KafkaProperties {

    private Properties properties;

    @Value("${myproperties.kafka.url}")
    private String servers;

    @PostConstruct
    public void inicializar() {
        properties = new Properties();
        properties.put("bootstrap.servers", servers);

        properties.put("key.serializer", StringSerializer.class.getCanonicalName());
        properties.put("key.deserializer", StringDeserializer.class.getCanonicalName());

        properties.put("value.serializer", StringSerializer.class.getCanonicalName());
        properties.put("value.deserializer", StringDeserializer.class.getCanonicalName());
    }

    public final KafkaConsumer<String, String> createConsumer(String topicName) {
        List<String> topics = new ArrayList();
        topics.add(topicName);
        KafkaConsumer<String, String> consumer = new KafkaConsumer(properties);
        consumer.subscribe(topics);
        return consumer;
    }

    public final KafkaProducer<String, String> createProducer() {
        return new KafkaProducer(properties);
    }

    public final String getServers() {
        return servers;
    }

    public final Properties getProperties() {
        return properties;
    }

}
