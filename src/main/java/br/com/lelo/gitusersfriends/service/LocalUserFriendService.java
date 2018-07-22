package br.com.lelo.gitusersfriends.service;

import br.com.lelo.gitusersfriends.domain.dao.LocalUserFriendRepository;
import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import br.com.lelo.gitusersfriends.domain.entity.LocalUserFriendEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class LocalUserFriendService {

    @Autowired
    private LocalUserFriendRepository repository;

    public List<LocalUserFriendEntity> findByUserLogin(String userLogin) {
        return repository.findByUserLogin(userLogin);
    }

    public void saveWithStar(String loginFriend, LocalUserEntity user) {
        try {
            repository.save(
                    this.findBy(loginFriend, user).increaseStar()
            );
        } catch (EntityNotFoundException e) {
            repository.save(
                    new LocalUserFriendEntity(loginFriend, user, true, 1)
            );
        }
    }

    public void save(String friendLogin, LocalUserEntity user) {
        try {

            LocalUserFriendEntity savedFriend = this.findBy(friendLogin, user);

            if (!savedFriend.isFriendFollower()) {
                savedFriend.setFriendFollower(true);
                repository.save(savedFriend);
            }

        } catch (EntityNotFoundException e) {
            repository.save(
                    new LocalUserFriendEntity(friendLogin, user, true, 0)
            );
        }
    }

    public LocalUserFriendEntity findBy(
            String friendLogin, LocalUserEntity user)
            throws EntityNotFoundException {

        return repository
                .findByFriendLoginAndUserLogin(friendLogin, user.getLogin())
                .orElseThrow(() -> new EntityNotFoundException(friendLogin));
    }

}
