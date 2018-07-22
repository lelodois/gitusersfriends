package br.com.lelo.gitusersfriends.service;

import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GitFollowersService {

    @Autowired
    private GitHttpService gitHttpService;

    @Autowired
    private LocalUserService userService;

    @Autowired
    private LocalUserFriendService friendService;

    public void saveFollowers(String login) throws Exception {
        LocalUserEntity user = userService.findByLogin(login);
        gitHttpService
                .findFollowers(login)
                .parallelStream()
                .filter(friend -> friend.isUser())
                .forEach(friend -> friendService.saveFriend(friend.getLogin(), user));
    }

}
