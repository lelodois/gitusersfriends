package br.com.lelo.gitusersfriends.service;


import br.com.lelo.gitusersfriends.dto.GitFollowerDto;
import br.com.lelo.gitusersfriends.dto.GitRepoStarDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Repository
public class GitHttpService {

    @Autowired
    private GitHttpRequestService request;

    @Autowired
    private ObjectMapper mapper;

    public List<Repository> findRepositories(String login) throws Exception {
        return Arrays.asList(
                mapper.readValue(
                        this.convert(request.findRepositories(login)),
                        Repository[].class
                )
        );
    }

    public List<GitRepoStarDto> findRepositoriesStar(String login, String repo) throws Exception {
        return Arrays.asList(
                mapper.readValue(
                        this.convert(request.findRepositoriesStar(login, repo)),
                        GitRepoStarDto[].class
                )
        );
    }

    public List<GitFollowerDto> findFollowers(String login) throws Exception {
        return Arrays.asList(
                mapper.readValue(
                        this.convert(request.findFollowers(login)),
                        GitFollowerDto[].class
                )
        );
    }

    private String convert(Request request) throws IOException {
        return request.execute().returnContent().asString();
    }

}
