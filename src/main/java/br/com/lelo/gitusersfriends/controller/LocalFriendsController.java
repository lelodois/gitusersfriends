package br.com.lelo.gitusersfriends.controller;

import br.com.lelo.gitusersfriends.domain.entity.LocalFriendEntity;
import br.com.lelo.gitusersfriends.business.LocalFriendBusiness;
import io.swagger.annotations.Api;
import org.hibernate.validator.constraints.NotEmpty;
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
    private LocalFriendBusiness service;

    @GetMapping(value = "/{login}")
    public ResponseEntity<List<LocalFriendEntity>> get(
            @PathVariable @NotEmpty String login) {

        return ResponseEntity.ok(service.findTopFriends(login));
    }

}
