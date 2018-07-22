package br.com.lelo.gitusersfriends.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitUserDto {

    @NotEmpty
    private String login;
    private Long id;
    private String type;

    public GitUserDto() {
    }

    public GitUserDto(String login, Long id, String type) {
        this.login = login;
        this.id = id;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isUser() {
        return "user".equalsIgnoreCase(this.getType());
    }
}
