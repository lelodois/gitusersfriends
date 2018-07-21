package br.com.lelo.gitusersfriends.domain.dao;

import br.com.lelo.gitusersfriends.domain.entity.GitUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GitUserRepository
        extends CrudRepository<GitUserEntity, Long> {

    Optional<GitUserEntity> findByLogin(String login);
}
