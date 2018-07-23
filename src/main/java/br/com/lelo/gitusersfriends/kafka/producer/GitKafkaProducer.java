package br.com.lelo.gitusersfriends.kafka.producer;

import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import br.com.lelo.gitusersfriends.domain.entity.builder.LocalUserBuilder;
import br.com.lelo.gitusersfriends.kafka.comum.KafkaProperties;
import br.com.lelo.gitusersfriends.kafka.comum.KafkaTopicEnum;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class GitKafkaProducer {

    private KafkaProducer<String, String> producer;

    public GitKafkaProducer(@Autowired KafkaProperties properties) {
        producer = properties.createProducer();
    }

    public void send(LocalUserEntity message, KafkaTopicEnum[] topics) {
        for (KafkaTopicEnum topicName : topics) {
            producer.send(
                    new ProducerRecord<String, String>(
                            topicName.name(),
                            message.getLogin())
            );
        }
    }

    public void sendError(String login) {
        this.send(
                LocalUserBuilder.builder().withLogin(login).build(),
                new KafkaTopicEnum[]{KafkaTopicEnum.GIT_TPC_ERRORS}
        );

    }
}
