package br.com.lelo.gitusersfriends.kafka.consumer;

import br.com.lelo.gitusersfriends.domain.dto.KafkaTopicEnum;
import br.com.lelo.gitusersfriends.kafka.KafkaProperties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class GitFollowersConsumer implements MyConsumer {

    @Autowired
    private KafkaProperties properties;

    private final Logger log = LoggerFactory.getLogger(GitFollowersConsumer.class);

    @PostConstruct
    public void go() {

        properties.getProperties().put("group.id", UUID.randomUUID().toString());

        new Thread(() -> {
            try (KafkaConsumer<String, String> consumer =
                         properties.createConsumer(getTopicName())) {

                consumer.seekToBeginning(consumer.assignment());
                while (true) {

                    log.info(GitFollowersConsumer.class.getSimpleName() + " Looking for message ...");

                    for (ConsumerRecord<String, String> record : consumer.poll(10)) {
                        log.info("item: " + record.value());
                    }
                }
            }
        }).start();
    }

    @Override
    public String getTopicName() {
        return KafkaTopicEnum.FOLLOWERS.name();
    }
}
