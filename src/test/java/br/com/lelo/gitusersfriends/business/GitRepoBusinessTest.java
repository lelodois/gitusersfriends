package br.com.lelo.gitusersfriends.business;

import br.com.lelo.gitusersfriends.domain.dto.GitRepoDto;
import br.com.lelo.gitusersfriends.domain.dto.GitUserDto;
import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import br.com.lelo.gitusersfriends.service.LocalFriendService;
import br.com.lelo.gitusersfriends.service.LocalUserService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GitRepoBusinessTest {

    @Mock
    private GitHttpBusiness gitHttpService;

    @Mock
    private LocalUserService userService;

    @Mock
    private LocalFriendService friendService;

    @InjectMocks
    private GitRepoBusiness gitRepoBusiness;

    @Test
    public void saveFollowers() throws Exception {
        String login = "lelodois";
        Mockito.when(userService.findByLogin(login))
                .thenReturn(new LocalUserEntity(login));

        Mockito.when(gitHttpService.findRepo(login))
                .thenReturn(Lists.newArrayList(
                        new GitRepoDto("repo1", 1l, 2, "", false),
                        new GitRepoDto("repo2", 2l, 0, "", false),
                        new GitRepoDto("repo3", 3l, 2, "", false),
                        new GitRepoDto("repo4", 4l, 2, "", true)
                ));

        Mockito.when(gitHttpService.findRepoStar(
                Mockito.anyString(), Mockito.anyString()
        )).thenReturn(Lists.newArrayList(
                new GitUserDto("rock", 1l, "user"),
                new GitUserDto("rock2", 2l, "user")
        ));

        gitRepoBusiness.saveStars(login);

        Mockito.verify(friendService, Mockito.times(4))
                .saveWithStar(Mockito.anyString(), Mockito.any(LocalUserEntity.class));
    }

}
