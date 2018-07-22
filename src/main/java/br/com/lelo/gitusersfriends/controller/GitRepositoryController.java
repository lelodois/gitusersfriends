package br.com.lelo.gitusersfriends.controller;

import br.com.lelo.gitusersfriends.service.LocalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gitfriends/git")
public class GitRepositoryController {

    @Autowired
    private LocalUserService business;

    @PutMapping(value = "friends/{login}")
    public ResponseEntity<Void> discovery(@PathVariable String login) {
        business.discovery(login);
        return ResponseEntity.ok().build();
    }

}
