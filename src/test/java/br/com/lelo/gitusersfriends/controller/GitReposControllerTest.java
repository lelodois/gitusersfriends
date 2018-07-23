package br.com.lelo.gitusersfriends.controller;

import br.com.lelo.gitusersfriends.business.LocalUserBusiness;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
public class GitReposControllerTest {

    @Mock
    private LocalUserBusiness service;

    @InjectMocks
    private GitReposController controller;

    private MockMvc mockMvc;

    private static String URL = "/gitfriends/git/friends";

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void discoverySuccess() throws Exception {
        JSONObject json = new JSONObject();
        json.put("login", "lelodois");
        mockMvc.perform(
                put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString())
        ).andExpect(status().isOk());
    }

    @Test
    public void discoveryInvalidParameters() throws Exception {
        JSONObject json = new JSONObject();
        json.put("logi11n", "lelodois");
        mockMvc.perform(
                put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString())
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void discoveryEmptyParameters() throws Exception {
        JSONObject json = new JSONObject();
        json.put("login", "");
        mockMvc.perform(
                put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString())
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void validServiceMock() {
        Assert.assertNotNull(service);
    }

}