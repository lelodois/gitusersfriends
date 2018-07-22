package br.com.lelo.gitusersfriends.kafka.consumer;

import br.com.lelo.gitusersfriends.kafka.comum.KafkaProperties;
import br.com.lelo.gitusersfriends.kafka.comum.KafkaTopicEnum;
import br.com.lelo.gitusersfriends.kafka.producer.GitKafkaProducer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

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

    @Scheduled(fixedDelay = 2000)
    protected void run() {
        for (ConsumerRecord<String, String> record : consumer.poll(10)) {
            try {
                this.doConsumer(record);
            } catch (Exception e) {
                producer.sendError(record.value());
            }
        }
    }

    abstract void doConsumer(ConsumerRecord<String, String> record)
            throws Exception;

    abstract KafkaTopicEnum getTopic();
}