package br.com.lelo.gitusersfriends.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class GitUser implements Serializable {

    @Id
    @GeneratedValue
    @JoinColumn(name = "id_user")
    private Long id;

    @NotNull
    private String login;

    @NotNull
    private Date lastUpdated;

    public GitUser() {

    }

    public GitUser(String login) {
        this.login = login;
        this.lastUpdated = new Date();
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
