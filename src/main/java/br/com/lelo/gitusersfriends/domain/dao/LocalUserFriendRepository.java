package br.com.lelo.gitusersfriends.domain.dao;

import br.com.lelo.gitusersfriends.domain.entity.LocalUserFriendEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalUserFriendRepository
        extends CrudRepository<LocalUserFriendEntity, Long> {

    @Query(" From LocalUserFriendEntity " +
            "where friendLogin = :friendLogin " +
            "  and user.login = :userLogin")
    Optional<LocalUserFriendEntity> findByFriendLoginAndUserLogin(
            @Param("friendLogin") String friendLogin,
            @Param("userLogin") String userLogin
    );


    @Query("From LocalUserFriendEntity where user.login = :userLogin")
    List<LocalUserFriendEntity> findByUserLogin(
            @Param("userLogin") String userLogin
    );
}
