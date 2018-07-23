package br.com.lelo.gitusersfriends.business;

import br.com.lelo.gitusersfriends.domain.dao.LocalUserRepository;
import br.com.lelo.gitusersfriends.domain.entity.LocalFriendEntity;
import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class LocalUserBusinessTest {

    @Mock
    private LocalUserRepository userRepository;

    @InjectMocks
    private LocalUserBusiness business;

    @Test
    public void saveSuccess() {
        String login = "lelodois";
        LocalUserEntity entity = new LocalUserEntity(login);
        entity.setId(1000l);

        Mockito.when(userRepository.save(Mockito.any(LocalUserEntity.class)))
                .thenReturn(entity);

        Mockito.when(userRepository.findByLogin(Mockito.anyString()))
                .thenReturn(Optional.empty());

        LocalUserEntity saved = business.save(login);

        Assert.assertEquals(saved.getLogin(), login);
        Assert.assertNotNull(saved.getId());
    }


    @Test
    public void updateSuccess() {
        String login = "lelodois";
        LocalUserEntity entity = new LocalUserEntity(login);
        entity.setId(1000l);
        entity.setFriends(Lists.newArrayList(
                new LocalFriendEntity("friend", entity, false, 1)
        ));

        Mockito.when(userRepository.findByLogin(Mockito.anyString()))
                .thenReturn(Optional.of(entity));

        Mockito.when(userRepository.save(Mockito.any(LocalUserEntity.class)))
                .thenReturn(entity);

        LocalUserEntity saved = business.save(login);

        Assert.assertEquals(saved.getLogin(), login);
        Assert.assertNotNull(saved.getId());
        Assert.assertTrue(saved.getFriends().isEmpty());
    }
}
