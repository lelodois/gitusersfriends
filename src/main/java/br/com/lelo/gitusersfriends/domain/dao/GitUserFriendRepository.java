package br.com.lelo.gitusersfriends.domain.dao;

import br.com.lelo.gitusersfriends.domain.entity.GitUserFriendEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitUserFriendRepository
        extends CrudRepository<GitUserFriendEntity, Long> {

}
