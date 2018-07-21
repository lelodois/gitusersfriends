package br.com.lelo.gitusersfriends.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class GitUserFriendEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_user_friend")
    private Long userFriendId;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private GitUserEntity user;

    @NotNull
    private String friendLogin;

    @NotNull
    private Integer friendRepoStars;

    private boolean friendFollower;

    public Long getUserFriendId() {
        return userFriendId;
    }

    public void setUserFriendId(Long userFriendId) {
        this.userFriendId = userFriendId;
    }

    public GitUserEntity getUser() {
        return user;
    }

    public void setUser(GitUserEntity user) {
        this.user = user;
    }

    public String getFriendLogin() {
        return friendLogin;
    }

    public void setFriendLogin(String friendLogin) {
        this.friendLogin = friendLogin;
    }

    public Integer getFriendRepoStars() {
        return friendRepoStars;
    }

    public void setFriendRepoStars(Integer friendRepoStars) {
        this.friendRepoStars = friendRepoStars;
    }

    public boolean isFriendFollower() {
        return friendFollower;
    }

    public void setFriendFollower(boolean friendFollower) {
        this.friendFollower = friendFollower;
    }
}
