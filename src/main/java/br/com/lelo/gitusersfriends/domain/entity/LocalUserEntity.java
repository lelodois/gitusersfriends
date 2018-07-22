package br.com.lelo.gitusersfriends.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.assertj.core.util.Lists;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class LocalUserEntity implements Serializable {

    @Id
    @GeneratedValue
    @JoinColumn(name = "id_user")
    private Long id;

    @NotNull
    private String login;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date lastUpdated;

    @OneToMany(orphanRemoval = true, mappedBy = "user", cascade = CascadeType.ALL)
    private List<LocalFriendEntity> friends = Lists.newArrayList();

    public LocalUserEntity() {

    }


    public LocalUserEntity(String login) {
        this.login = login;
        this.lastUpdated = new Date();
    }

    public List<LocalFriendEntity> getFriends() {
        return friends;
    }

    public void setFriends(List<LocalFriendEntity> friends) {
        this.friends = friends;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
