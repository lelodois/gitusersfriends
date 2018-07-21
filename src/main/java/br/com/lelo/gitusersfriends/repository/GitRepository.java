package br.com.lelo.gitusersfriends.repository;

import br.com.lelo.gitusersfriends.domain.dto.Follower;
import br.com.lelo.gitusersfriends.domain.dto.RepositoryStar;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Repository
public class GitRepository {

    @Autowired
    private GitRequestRepository request;

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

    public List<RepositoryStar> findRepositoriesStar(String login, String repo) throws Exception {
        return Arrays.asList(
                mapper.readValue(
                        this.convert(request.findRepositoriesStar(login, repo)),
                        RepositoryStar[].class
                )
        );
    }

    public List<Follower> findFollowers(String login) throws Exception {
        return Arrays.asList(
                mapper.readValue(
                        this.convert(request.findFollowers(login)),
                        Follower[].class
                )
        );
    }

    private String convert(Request request) throws IOException {
        return request.execute().returnContent().asString();
    }

}
