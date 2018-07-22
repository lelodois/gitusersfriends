package br.com.lelo.gitusersfriends.controller;

import br.com.lelo.gitusersfriends.service.LocalFriendService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LocalFriendsController.class)
public class LocalFriendsControllerTest {

    @MockBean
    private LocalFriendService service;

    @Autowired
    private MockMvc mockMvc;

    private static final String URL = "/gitfriends/local/top-friends/";

    @Test
    public void getSuccess() throws Exception {
        Mockito.when(service.findTopFriends(Mockito.any(String.class)))
                .thenReturn(Lists.newArrayList());

        mockMvc.perform(get(URL.concat("lelodois")))
                .andExpect(status().isOk());
    }

    @Test
    public void getEmptyParameters() throws Exception {
        mockMvc.perform(get(URL))
                .andExpect(status().isNotFound());
    }

}