package br.com.lelo.gitusersfriends.business;

import br.com.lelo.gitusersfriends.domain.dao.LocalUserRepository;
import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import br.com.lelo.gitusersfriends.domain.entity.builder.LocalUserBuilder;
import br.com.lelo.gitusersfriends.kafka.comum.KafkaTopicEnum;
import br.com.lelo.gitusersfriends.kafka.producer.GitKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class LocalUserBusiness {

    @Autowired
    private LocalUserRepository userRepository;

    @Autowired
    private GitKafkaProducer producer;

    public LocalUserEntity discovery(String login) {
        LocalUserEntity savedUser = this.save(login);
        producer.send(savedUser, KafkaTopicEnum.values());
        return savedUser;
    }

    public LocalUserEntity save(String login) {
        try {
            LocalUserEntity user = this.findByLogin(login);
            user.setLastUpdated(new Date());
            user.getFriends().clear();
            return userRepository.save(user);
        } catch (EntityNotFoundException e) {
            return userRepository.save(
                    LocalUserBuilder.builder()
                            .withLogin(login)
                            .build());
        }
    }

    public LocalUserEntity findByLogin(String login) throws EntityNotFoundException {
        return userRepository
                .findTop1ByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException(login));
    }

}
