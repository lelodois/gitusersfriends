package br.com.lelo.gitusersfriends.service;

import br.com.lelo.gitusersfriends.kafka.producer.GitKafkaProducer;
import br.com.lelo.gitusersfriends.domain.entity.GitUser;
import br.com.lelo.gitusersfriends.repository.GitUserRepository;
import br.com.lelo.gitusersfriends.domain.dto.KafkaTopicEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GitUserService {

    @Autowired
    private GitUserRepository gitUserRepository;

    @Autowired
    private GitKafkaProducer producer;

    public GitUser start(String login) {
        Optional<GitUser> gitUser = gitUserRepository.findByLogin(login);

        if (gitUser.isPresent() == false) {
            gitUser = Optional.of(gitUserRepository.save(new GitUser(login)));
        }

        producer.send(gitUser.get(), KafkaTopicEnum.values());

        return gitUser.get();
    }

}
