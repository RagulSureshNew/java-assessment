package com.telstra.codechallenge.gitsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GitSearchController {

    @Autowired GitSearchService gitSearchService;
    @GetMapping("get-hot-repo-last-week")
    public ResponseEntity<List<GetHotRepoResponse>> gitSearch(
        @RequestParam(value="numberOfRepos") Integer numberOfRepos
    ){
        List<GetHotRepoResponse> response;
        response = gitSearchService.getHotRepoLastWeek(numberOfRepos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
