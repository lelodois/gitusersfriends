package br.com.lelo.gitusersfriends.business;

import br.com.lelo.gitusersfriends.domain.dao.LocalFriendRepository;
import br.com.lelo.gitusersfriends.domain.entity.LocalFriendEntity;
import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.Comparator.comparingInt;

@Service
public class LocalFriendBusiness {

    @Autowired
    private LocalFriendRepository repository;

    public void saveWithStar(String loginFriend, LocalUserEntity user) {
        try {
            repository.save(this.findBy(loginFriend, user).increaseStar());
        } catch (EntityNotFoundException e) {
            repository.save(new LocalFriendEntity(loginFriend, user, true, 1));
        }
    }

    public void save(String friendLogin, LocalUserEntity user) {
        try {

            LocalFriendEntity savedFriend = this.findBy(friendLogin, user);

            if (!savedFriend.isFriendFollower()) {
                savedFriend.setFriendFollower(true);
                repository.save(savedFriend);
            }

        } catch (EntityNotFoundException e) {
            repository.save(new LocalFriendEntity(friendLogin, user, true, 0));
        }
    }

    public LocalFriendEntity findBy(
            String friendLogin, LocalUserEntity user)
            throws EntityNotFoundException {

        return repository
                .findByFriendLoginAndUserLogin(friendLogin, user.getLogin())
                .orElseThrow(() -> new EntityNotFoundException(friendLogin));
    }

    @Retryable(maxAttempts = 5, value = RuntimeException.class, backoff = @Backoff(delay = 2000))
    public List<LocalFriendEntity> findTopFriends(String userLogin) {
        List<LocalFriendEntity> friends = repository.findByUserLogin(userLogin);
        if (friends.isEmpty()) throw new RuntimeException("Friends not loaded");

        friends.sort(comparingInt(LocalFriendEntity::getStars).reversed());
        friends.parallelStream()
                .map(friend -> friend.copyStars());

        return friends.size() > 4 ? friends.subList(0, 4) : friends;
    }
}
