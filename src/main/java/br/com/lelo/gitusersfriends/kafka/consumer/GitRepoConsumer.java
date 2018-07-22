package br.com.lelo.gitusersfriends.kafka.consumer;

import br.com.lelo.gitusersfriends.kafka.comum.KafkaTopicEnum;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GitRepoConsumer extends GitConsumer {

    @Override
    public KafkaTopicEnum getTopic() {
        return KafkaTopicEnum.GIT_TOPIC_REPOSITORY;
    }

    @Override
    @Scheduled(fixedDelay = 2000)
    public void run() {
        for (ConsumerRecord<String, String> record : super.poll()) {
            try {
                //makeAnything
            } catch (Exception e) {
                super.sendError(record.value());
            }
        }
    }
}
