package br.com.lelo.gitusersfriends.kafka.consumer;

import br.com.lelo.gitusersfriends.kafka.comum.KafkaTopicEnum;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

@Component
public class GitRepoConsumer extends GitConsumer {

    @Override
    public KafkaTopicEnum getTopic() {
        return KafkaTopicEnum.GIT_TOPIC_REPOSITORY;
    }

    @Override
    protected void doConsumer(ConsumerRecord<String, String> record)
            throws Exception {
        //makeAnything
    }
}
