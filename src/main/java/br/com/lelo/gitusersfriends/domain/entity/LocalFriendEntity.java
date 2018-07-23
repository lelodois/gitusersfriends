package br.com.lelo.gitusersfriends.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class LocalFriendEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_friend")
    private Long friendId;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private LocalUserEntity user;

    @NotNull
    private String friendLogin;

    @NotNull
    private AtomicInteger friendRepoStars;

    private boolean friendFollower;

    @Transient
    private Integer starts;

    public int getStars() {
        return getFriendRepoStars().get();
    }

    public Long getFriendId() {
        return friendId;
    }

    public Integer getStarts() {
        return starts;
    }

    public void setStarts(Integer starts) {
        this.starts = starts;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public LocalUserEntity getUser() {
        return user;
    }

    public void setUser(LocalUserEntity user) {
        this.user = user;
    }

    public String getFriendLogin() {
        return friendLogin;
    }

    public void setFriendLogin(String friendLogin) {
        this.friendLogin = friendLogin;
    }

    public AtomicInteger getFriendRepoStars() {
        return friendRepoStars;
    }

    public void setFriendRepoStars(AtomicInteger friendRepoStars) {
        this.friendRepoStars = friendRepoStars;
    }

    public boolean isFriendFollower() {
        return friendFollower;
    }

    public void setFriendFollower(boolean friendFollower) {
        this.friendFollower = friendFollower;
    }

    public LocalFriendEntity copyStars() {
        this.setStarts(this.getFriendRepoStars().get());
        return this;
    }

    public LocalFriendEntity increaseStar() {
        this.friendRepoStars.incrementAndGet();
        return this;
    }
}
