package br.com.lelo.gitusersfriends.business;

import br.com.lelo.gitusersfriends.domain.dto.GitRepoDto;
import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GitRepoBusiness {

    @Autowired
    private GitHttpBusiness gitHttpBusiness;

    @Autowired
    private LocalUserBusiness userBusiness;

    @Autowired
    private LocalFriendBusiness friendBusiness;

    public void saveStars(String login) throws Exception {
        LocalUserEntity user = userBusiness.findByLogin(login);
        gitHttpBusiness
                .findRepo(login)
                .parallelStream()
                .filter(GitRepoDto::validRepo)
                .forEach(repo -> this.saveWithStar(user, repo));
    }

    private void saveWithStar(LocalUserEntity user, GitRepoDto repo) {
        try {
            gitHttpBusiness.findRepoStar(user.getLogin(), repo.getName())
                    .parallelStream()
                    .filter(star -> !star.getLogin().equals(user.getLogin()))
                    .forEach(star ->
                            friendBusiness.saveWithStar(star.getLogin(), user)
                    );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
