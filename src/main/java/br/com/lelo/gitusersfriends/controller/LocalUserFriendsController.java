package br.com.lelo.gitusersfriends.controller;

import br.com.lelo.gitusersfriends.domain.entity.LocalUserFriendEntity;
import br.com.lelo.gitusersfriends.service.LocalUserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("gitfriends/local/friends")
public class LocalUserFriendsController {

    @Autowired
    private LocalUserFriendService service;

    @GetMapping(value = "/{login}")
    public ResponseEntity<List<LocalUserFriendEntity>> get(
            @PathVariable String login) {

        return ResponseEntity.ok(
                service.findByUserLogin(login));
    }

}
