package br.com.lelo.gitusersfriends.controller;

import br.com.lelo.gitusersfriends.domain.dto.GitUserDto;
import br.com.lelo.gitusersfriends.service.LocalUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gitfriends/git")
@Api(value = "Usuários git", description = "Extração de informações de usuários do git")
@CrossOrigin(value = "*")
public class GitReposController {

    @Autowired
    private LocalUserService business;

    @PutMapping(value = "friends")
    public ResponseEntity<String> discovery(@RequestBody GitUserDto user) {

        business.discovery(user.getLogin());

        return ResponseEntity.ok(user.getLogin());
    }

}
