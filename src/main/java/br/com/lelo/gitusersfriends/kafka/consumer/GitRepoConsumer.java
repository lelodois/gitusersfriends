package br.com.lelo.gitusersfriends.kafka.consumer;

import br.com.lelo.gitusersfriends.business.GitRepoBusiness;
import br.com.lelo.gitusersfriends.kafka.comum.KafkaTopicEnum;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GitRepoConsumer extends GitConsumer {

    @Autowired
    private GitRepoBusiness repoBusiness;

    @Override
    public KafkaTopicEnum getTopic() {
        return KafkaTopicEnum.GIT_TOPIC_REPOSITORY;
    }

    @Override
    protected void doConsumer(ConsumerRecord<String, String> record)
            throws Exception {
        repoBusiness.saveStars(record.value());
    }
}
