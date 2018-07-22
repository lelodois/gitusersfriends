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

    public void saveFriend(String login, LocalUserEntity user) {
        try {

            LocalUserFriendEntity friendEntity = this.findByLogin(login, user.getLogin());
            if (friendEntity.isFriendFollower() == false) {
                friendEntity.setFriendFollower(true);
                repository.save(friendEntity);
            }

        } catch (EntityNotFoundException e) {
            repository.save(new LocalUserFriendEntity(login, user, true));
        }
    }

    public LocalUserFriendEntity findByLogin(String login, String userLogin) throws EntityNotFoundException {
        return repository
                .findByLoginAndUserLogin(login, userLogin)
                .orElseThrow(() -> new EntityNotFoundException(login));
    }

}
