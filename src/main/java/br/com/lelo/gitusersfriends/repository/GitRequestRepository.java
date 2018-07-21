package br.com.lelo.gitusersfriends.repository;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class GitRequestRepository {

    @Value("${myproperties.url.git.repository}")
    private String repoUrl;

    @Value("${myproperties.url.git.repository.stars}")
    private String repoStars;

    @Value("${myproperties.url.git.followers}")
    private String followersUrl;

    public Request findRepositories(String login) {
        return Request.Get(
                StringUtils
                        .replace(repoUrl, "{login}", login)
        );
    }

    public Request findRepositoriesStar(String login, String repo) {
        return Request.Get(
                StringUtils
                        .replace(repoStars, "{login}", login)
                        .replace("{repo}", repo)
        );
    }

    public Request findFollowers(String login) {
        return Request.Get(
                StringUtils
                        .replace(followersUrl, "{login}", login)
        );
    }


}
