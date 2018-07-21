package br.com.lelo.gitusersfriends.kafka.producer;

import br.com.lelo.gitusersfriends.domain.dto.KafkaTopicEnum;
import br.com.lelo.gitusersfriends.domain.entity.GitUser;
import br.com.lelo.gitusersfriends.kafka.KafkaProperties;
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

    public void send(GitUser message, KafkaTopicEnum[] topics) {
        for (KafkaTopicEnum topicName : topics) {
            producer.send(
                    new ProducerRecord<String, String>(
                            topicName.name(),
                            message.getLogin())
            );
        }
    }
}
