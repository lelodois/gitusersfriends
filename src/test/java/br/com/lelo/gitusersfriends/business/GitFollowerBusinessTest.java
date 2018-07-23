package br.com.lelo.gitusersfriends.business;

import br.com.lelo.gitusersfriends.domain.dto.GitUserDto;
import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GitFollowerBusinessTest {

    @Mock
    private GitHttpBusiness gitHttpService;

    @Mock
    private LocalUserBusiness userService;

    @Mock
    private LocalFriendBusiness friendService;

    @InjectMocks
    private GitFollowerBusiness gitFollowerBusiness;

    @Test
    public void saveFollowers() throws Exception {
        String login = "lelodois";
        Mockito.when(userService.findByLogin(login))
                .thenReturn(new LocalUserEntity(login));

        Mockito.when(gitHttpService.findFollowers(login))
                .thenReturn(Lists.newArrayList(
                        new GitUserDto("rock", 1l, "user"),
                        new GitUserDto("rock2", 2l, "user"),
                        new GitUserDto("rock3", 3l, "bot")
                ));

        gitFollowerBusiness.saveFollowers(login);

        Mockito.verify(friendService, Mockito.times(2))
                .save(Mockito.anyString(), Mockito.any(LocalUserEntity.class));
    }

}
