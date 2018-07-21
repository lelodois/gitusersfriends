package br.com.lelo.gitusersfriends.controller;

import br.com.lelo.gitusersfriends.service.GitUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gitfriends")
public class GitRepositoryController {

    @Autowired
    private GitUserService business;

    @PutMapping(value = "matriz/{login}")
    public ResponseEntity<Void> matrix(@PathVariable String login) throws Exception {
        business.start(login);
        return ResponseEntity.ok().build();
    }

}
