package br.com.lelo.gitusersfriends.kafka.consumer;

import br.com.lelo.gitusersfriends.kafka.comum.KafkaTopicEnum;
import br.com.lelo.gitusersfriends.service.GitFollowersService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GitFollowersConsumer extends GitConsumer {

    @Autowired
    private GitFollowersService followersService;

    @Override
    protected void doConsumer(ConsumerRecord<String, String> record)
            throws Exception {
        followersService.saveFollowers(record.value());
    }

    @Override
    public KafkaTopicEnum getTopic() {
        return KafkaTopicEnum.GIT_TOPIC_FOLLOWERS;
    }

}
