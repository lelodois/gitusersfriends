package br.com.lelo.gitusersfriends.domain.dao;

import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalUserRepository
        extends CrudRepository<LocalUserEntity, Long> {

    Optional<LocalUserEntity> findTop1ByLogin(String login);
}
