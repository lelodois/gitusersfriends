package br.com.lelo.gitusersfriends.domain.entity.builder;

import br.com.lelo.gitusersfriends.domain.entity.LocalFriendEntity;
import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;

import java.util.concurrent.atomic.AtomicInteger;

public class LocalFriendBuilder {

    private final LocalFriendEntity entity;

    private LocalFriendBuilder() {
        this.entity = new LocalFriendEntity();
    }

    public LocalFriendEntity build() {
        return entity;
    }

    public static LocalFriendBuilder builder() {
        return new LocalFriendBuilder();
    }

    public LocalFriendBuilder withUser(LocalUserEntity user) {
        entity.setUser(user);
        return this;
    }

    public LocalFriendBuilder withFriendRepoStar(int friendRepoStar) {
        entity.setFriendRepoStars(new AtomicInteger(friendRepoStar));
        return this;
    }

    public LocalFriendBuilder withFriendLogin(String friendLogin) {
        entity.setFriendLogin(friendLogin);
        return this;
    }

    public LocalFriendBuilder withFriendFollower(boolean friendFollower) {
        entity.setFriendFollower(friendFollower);
        return this;
    }

}
