package br.com.lelo.gitusersfriends.service;

import br.com.lelo.gitusersfriends.domain.dao.LocalUserRepository;
import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import br.com.lelo.gitusersfriends.kafka.comum.KafkaTopicEnum;
import br.com.lelo.gitusersfriends.kafka.producer.GitKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class LocalUserService {

    @Autowired
    private LocalUserRepository gitUserRepository;

    @Autowired
    private GitKafkaProducer producer;

    public LocalUserEntity discovery(String login) {
        LocalUserEntity savedUser = this.save(login);
        producer.send(savedUser, KafkaTopicEnum.values());
        return savedUser;
    }

    public LocalUserEntity save(String login) {
        try {
            return this.findByLogin(login);
        } catch (EntityNotFoundException e) {
            return gitUserRepository.save(new LocalUserEntity(login));
        }
    }

    public LocalUserEntity findByLogin(String login) throws EntityNotFoundException {
        return gitUserRepository
                .findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException(login));
    }

}
