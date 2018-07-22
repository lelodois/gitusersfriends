package br.com.lelo.gitusersfriends.kafka.consumer;

import br.com.lelo.gitusersfriends.kafka.comum.KafkaProperties;
import br.com.lelo.gitusersfriends.kafka.comum.KafkaTopicEnum;
import br.com.lelo.gitusersfriends.kafka.producer.GitKafkaProducer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.UUID;

public abstract class GitConsumer {

    @Autowired
    private KafkaProperties properties;

    @Autowired
    private GitKafkaProducer producer;

    private KafkaConsumer<String, String> consumer;

    @PostConstruct
    public final void go() {
        properties.getProperties().put("group.id", UUID.randomUUID().toString());
        consumer = properties.createConsumer(getTopic().name());
        consumer.seekToBeginning(consumer.assignment());
    }

    protected final void sendError(String login) {
        producer.sendError(login);
    }

    protected final ConsumerRecords<String, String> poll() {
        return consumer.poll(10);
    }

    abstract void run();

    abstract KafkaTopicEnum getTopic();
}