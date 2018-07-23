package br.com.lelo.gitusersfriends.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RequestDto {

    private String login;
    private boolean ready;

    public RequestDto() {

    }

    public RequestDto(String login, boolean ready) {
        this.login = login;
        this.ready = ready;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}   

