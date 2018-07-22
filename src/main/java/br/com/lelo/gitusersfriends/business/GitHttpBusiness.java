package br.com.lelo.gitusersfriends.business;


import br.com.lelo.gitusersfriends.domain.dto.GitRepoDto;
import br.com.lelo.gitusersfriends.domain.dto.GitUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.replace;

@Repository
public class GitHttpBusiness {

    @Value("${myproperties.url.git.repository}")
    private String repoUrl;

    @Value("${myproperties.url.git.repository.stars}")
    private String repoStars;

    @Value("${myproperties.url.git.followers}")
    private String followersUrl;

    @Autowired
    private ObjectMapper mapper;

    public List<GitRepoDto> findRepo(String login) throws Exception {
        Request request = Request.Get(replace(repoUrl, "{login}", login));

        return Arrays.asList(mapper.readValue(this.convert(request), GitRepoDto[].class));
    }

    public List<GitUserDto> findRepoStar(String login, String repo) throws Exception {
        Request request =
                Request.Get(replace(repoStars, "{login}", login).replace("{repo}", repo));

        return Arrays.asList(mapper.readValue(this.convert(request), GitUserDto[].class));
    }

    public List<GitUserDto> findFollowers(String login) throws Exception {
        Request request = Request.Get(replace(followersUrl, "{login}", login));

        return Arrays.asList(mapper.readValue(this.convert(request), GitUserDto[].class));
    }

    private String convert(Request request) throws IOException {
        return request.connectTimeout(10000)
                .execute().returnContent().asString();
    }

}
