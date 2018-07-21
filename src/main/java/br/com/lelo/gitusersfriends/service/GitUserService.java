package br.com.lelo.gitusersfriends.service;

import br.com.lelo.gitusersfriends.kafka.producer.GitKafkaProducer;
import br.com.lelo.gitusersfriends.domain.entity.GitUserEntity;
import br.com.lelo.gitusersfriends.domain.dao.GitUserRepository;
import br.com.lelo.gitusersfriends.kafka.comum.KafkaTopicEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GitUserService {

    @Autowired
    private GitUserRepository gitUserRepository;

    @Autowired
    private GitKafkaProducer producer;

    public GitUserEntity start(String login) {
        Optional<GitUserEntity> gitUser = gitUserRepository.findByLogin(login);

        if (gitUser.isPresent() == false) {
            gitUser = Optional.of(gitUserRepository.save(new GitUserEntity(login)));
        }

        producer.send(gitUser.get(), KafkaTopicEnum.values());

        return gitUser.get();
    }

}
