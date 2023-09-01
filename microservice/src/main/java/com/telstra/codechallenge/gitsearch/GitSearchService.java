package com.telstra.codechallenge.gitsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class GitSearchService {
    @Autowired private RestTemplate restTemplate;
    @Value("${gitSearch.repo.url}")
    private String gitSearchRepoURL;

    public List<GetHotRepoResponse> getHotRepoLastWeek (Integer numberOfRepos) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        SimpleDateFormat formatted = new SimpleDateFormat("yyyy-MM-dd");
        String backDate = formatted.format(cal.getTime());
        String dateParam = String.format("created:>%s", backDate);
        String buildUrl = UriComponentsBuilder.fromUriString(gitSearchRepoURL)
                .queryParam("q",dateParam)
                .queryParam("sort", "stars")
                .queryParam("per_page",numberOfRepos)
                .encode().toUriString();
        String url = java.net.URLDecoder.decode(buildUrl, StandardCharsets.UTF_8);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        try{
            var response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(header), GitSearchResponse.class)
                .getBody();
            if(response != null) {
                return buildResponseData(response);
            }else{
                // can be used for logged
                return new ArrayList<GetHotRepoResponse>();
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<GetHotRepoResponse> buildResponseData(GitSearchResponse response){
        var listOfRepos = new ArrayList<GetHotRepoResponse>();
        response.items.forEach(
                repo -> {
                    var list = new GetHotRepoResponse();
                    list.setHtml_url(repo.getHtml_url());
                    list.setWatchers_count(repo.getWatchers_count());
                    list.setLanguage(repo.getLanguage());
                    list.setDescription(repo.getDescription());
                    list.setName(repo.getName());
                    listOfRepos.add(list);
                }
        );
        return listOfRepos;
    }
}
