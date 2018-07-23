package br.com.lelo.gitusersfriends.domain.entity.builder;

import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;

import java.util.Date;

public class LocalUserBuilder {

    private final LocalUserEntity entity;

    private LocalUserBuilder() {
        this.entity = new LocalUserEntity();
        entity.setLastUpdated(new Date());
    }

    public LocalUserEntity build() {
        return entity;
    }

    public static LocalUserBuilder builder() {
        return new LocalUserBuilder();
    }

    public LocalUserBuilder withLogin(String login) {
        entity.setLogin(login);
        return this;
    }

    public LocalUserBuilder withId(long id) {
        entity.setId(id);
        return this;
    }
}
