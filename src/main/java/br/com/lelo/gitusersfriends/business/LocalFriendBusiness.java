package br.com.lelo.gitusersfriends.business;

import br.com.lelo.gitusersfriends.domain.dao.LocalFriendRepository;
import br.com.lelo.gitusersfriends.domain.entity.LocalFriendEntity;
import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import br.com.lelo.gitusersfriends.domain.entity.builder.LocalFriendBuilder;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.Comparator.comparingInt;

@Service
public class LocalFriendBusiness {

    @Autowired
    private LocalFriendRepository repository;

    public void saveWithStar(String friendLogin, LocalUserEntity user) {
        try {
            repository.save(this.findBy(friendLogin, user).increaseStar());
        } catch (EntityNotFoundException e) {
            repository.save(LocalFriendBuilder.builder()
                    .withFriendLogin(friendLogin)
                    .withUser(user)
                    .withFriendFollower(true)
                    .withFriendRepoStar(1)
                    .build());
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
            repository.save(LocalFriendBuilder.builder()
                    .withFriendLogin(friendLogin)
                    .withUser(user)
                    .withFriendFollower(true)
                    .withFriendRepoStar(1)
                    .build());
        }
    }

    public LocalFriendEntity findBy(
            String friendLogin, LocalUserEntity user)
            throws EntityNotFoundException {

        return repository
                .findByFriendLoginAndUserLogin(friendLogin, user.getLogin())
                .orElseThrow(() -> new EntityNotFoundException(friendLogin));
    }

    public List<LocalFriendEntity> findTopFriends(String userLogin) {
        List<LocalFriendEntity> friends = repository.findByUserLogin(userLogin);
        if (friends.isEmpty()) return Lists.newArrayList();

        friends.sort(comparingInt(LocalFriendEntity::getStars).reversed());
        friends.parallelStream()
                .map(LocalFriendEntity::copyStars);

        return friends.size() > 4 ? friends.subList(0, 4) : friends;
    }
}
