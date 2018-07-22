package br.com.lelo.gitusersfriends.exception;

public class LocalUserNotFound extends Exception {
    public LocalUserNotFound(String login) {
        super(login);
    }
}
