package br.com.lelo.gitusersfriends.repository;

import br.com.lelo.gitusersfriends.domain.entity.GitUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GitUserRepository extends CrudRepository<GitUser, Long> {

    public Optional<GitUser> findByLogin(String login);
}
