package br.com.lelo.gitusersfriends.controller;

import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import br.com.lelo.gitusersfriends.service.LocalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gitfriends/local/users")
public class LocalUserController {

    @Autowired
    private LocalUserService service;

    @GetMapping(value = "/{login}")
    public ResponseEntity<LocalUserEntity> get(@PathVariable String login) {
        return ResponseEntity.ok(service.findByLogin(login));
    }

}
