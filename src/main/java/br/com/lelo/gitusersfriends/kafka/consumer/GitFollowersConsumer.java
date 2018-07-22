package br.com.lelo.gitusersfriends.kafka.consumer;

import br.com.lelo.gitusersfriends.kafka.comum.KafkaTopicEnum;
import br.com.lelo.gitusersfriends.service.GitFollowersService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GitFollowersConsumer extends GitConsumer {

    @Autowired
    private GitFollowersService followersService;

    @Override
    @Scheduled(fixedDelay = 2000)
    public void run() {
        for (ConsumerRecord<String, String> record : super.poll()) {
            try {
                followersService.saveFollowers(record.value());
            } catch (Exception e) {
                e.printStackTrace();
                super.sendError(record.value());
            }
        }
    }

    @Override
    public KafkaTopicEnum getTopic() {
        return KafkaTopicEnum.GIT_TOPIC_FOLLOWERS;
    }

}
