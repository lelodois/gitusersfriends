package br.com.lelo.gitusersfriends.domain.dao;

import br.com.lelo.gitusersfriends.domain.entity.LocalFriendEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalFriendRepository
        extends CrudRepository<LocalFriendEntity, Long> {

    @Query(" From LocalFriendEntity " +
            "where friendLogin = :friendLogin " +
            "  and user.login = :userLogin")
    Optional<LocalFriendEntity> findByFriendLoginAndUserLogin(
            @Param("friendLogin") String friendLogin,
            @Param("userLogin") String userLogin
    );


    @Query("From LocalFriendEntity where user.login = :userLogin")
    List<LocalFriendEntity> findByUserLogin(
            @Param("userLogin") String userLogin
    );
}
