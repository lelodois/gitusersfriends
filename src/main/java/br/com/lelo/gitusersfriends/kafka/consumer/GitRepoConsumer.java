package br.com.lelo.gitusersfriends.kafka.consumer;

import br.com.lelo.gitusersfriends.kafka.comum.KafkaTopicEnum;
import br.com.lelo.gitusersfriends.kafka.comum.KafkaProperties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class GitRepoConsumer implements MyConsumer {

    @Autowired
    private KafkaProperties properties;

    @PostConstruct
    @Override
    public void go() {
        properties.getProperties().put("group.id", UUID.randomUUID().toString());
        new Thread(this::run).start();
    }

    @Override
    public String getTopicName() {
        return KafkaTopicEnum.GIT_TOPIC_REPOSITORY.name();
    }

    private void run() {
        try (KafkaConsumer<String, String> consumer =
                     properties.createConsumer(getTopicName())) {
            consumer.seekToBeginning(consumer.assignment());
            while (true) {

                for (ConsumerRecord<String, String> record : consumer.poll(10)) {
                }
            }
        }
    }
}
