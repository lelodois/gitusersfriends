package br.com.lelo.gitusersfriends.controller;

import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import br.com.lelo.gitusersfriends.service.LocalUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gitfriends/local/users")
@Api(value = "Usuários locais", description = "Usuários extraídos do git")
@CrossOrigin(value = "*")
public class LocalUsersController {

    @Autowired
    private LocalUserService service;

    @GetMapping(value = "/{login}")
    public ResponseEntity<LocalUserEntity> get(@PathVariable String login) {
        return ResponseEntity.ok(service.findByLogin(login));
    }

}
