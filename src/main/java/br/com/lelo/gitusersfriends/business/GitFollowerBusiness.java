package br.com.lelo.gitusersfriends.business;

import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import br.com.lelo.gitusersfriends.service.LocalUserFriendService;
import br.com.lelo.gitusersfriends.service.LocalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GitFollowerBusiness {

    @Autowired
    private GitHttpBusiness gitHttpService;

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
                .forEach(friend -> friendService.save(friend.getLogin(), user));
    }

}
