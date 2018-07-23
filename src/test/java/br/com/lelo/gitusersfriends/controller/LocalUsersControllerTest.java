package br.com.lelo.gitusersfriends.controller;

import br.com.lelo.gitusersfriends.domain.entity.LocalUserEntity;
import br.com.lelo.gitusersfriends.business.LocalUserBusiness;
import br.com.lelo.gitusersfriends.domain.entity.builder.LocalUserBuilder;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(LocalUsersController.class)
public class LocalUsersControllerTest {

    @MockBean
    private LocalUserBusiness service;

    @Autowired
    private MockMvc mockMvc;

    private final String URL = "/gitfriends/local/users/";

    @Test
    public void getSuccess() throws Exception {
        Mockito.when(service.findByLogin(Mockito.anyString()))
                .thenReturn(LocalUserBuilder.builder().withLogin("lelodois").build());

        mockMvc.perform(
                MockMvcRequestBuilders.get(URL.concat(" ")))
                .andExpect(
                        MockMvcResultMatchers.status().
                                isOk()
                )
                .andExpect(MockMvcResultMatchers.jsonPath
                        ("$.login", Matchers.equalTo("lelodois"))
                );
    }
}
