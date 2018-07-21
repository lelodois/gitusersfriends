package br.com.lelo.gitusersfriends.kafka.consumer;

import br.com.lelo.gitusersfriends.kafka.comum.KafkaProperties;
import br.com.lelo.gitusersfriends.kafka.comum.KafkaTopicEnum;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class GitFollowersConsumer implements MyConsumer {

    @Autowired
    private KafkaProperties properties;

    @PostConstruct
    public void go() {
        properties.getProperties().put("group.id", UUID.randomUUID().toString());
        new Thread(this::run).start();
    }

    @Override
    public String getTopicName() {
        return KafkaTopicEnum.GIT_TOPIC_FOLLOWERS.name();
    }

    private void run() {
        try (KafkaConsumer<String, String> kafkaConsumer =
                     properties.createConsumer(this.getTopicName())) {

            kafkaConsumer.seekToBeginning(kafkaConsumer.assignment());
            while (true) {
                for (ConsumerRecord<String, String> record : kafkaConsumer.poll(10)) {
                }
            }
        }
    }
}
