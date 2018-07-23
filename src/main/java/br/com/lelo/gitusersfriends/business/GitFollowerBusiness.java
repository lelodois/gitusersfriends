package br.com.lelo.gitusersfriends.business;

import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GitFollowerBusiness {

    @Autowired
    private GitHttpBusiness gitHttpBusiness;

    @Autowired
    private LocalUserBusiness userService;

    @Autowired
    private LocalFriendBusiness friendBusiness;

    public void saveFollowers(String login) throws Exception {
        LocalUserEntity user = userService.findByLogin(login);
        gitHttpBusiness
                .findFollowers(login)
                .parallelStream()
                .filter(friend -> friend.isUser())
                .forEach(friend -> friendBusiness.save(friend.getLogin(), user));
    }

}
