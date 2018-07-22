package br.com.lelo.gitusersfriends.controller;

import br.com.lelo.gitusersfriends.domain.entity.LocalFriendEntity;
import br.com.lelo.gitusersfriends.service.LocalFriendService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gitfriends/local/top-friends")
@Api(value = "Amigos de usuários do git", description = "Amigos favoritos e seguidores")
@CrossOrigin(value = "*")
public class LocalFriendsController {

    @Autowired
    private LocalFriendService service;

    @GetMapping(value = "/{login}")
    public ResponseEntity<List<LocalFriendEntity>> get(
            @PathVariable String login) {

        return ResponseEntity.ok(service.findTopFriends(login));
    }

}
