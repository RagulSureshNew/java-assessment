package com.telstra.codechallenge.gitsearch;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetHotRepoResponse {
    @JsonProperty("html_url")
    String html_url;
    @JsonProperty("watchers_count")
    Integer watchers_count;
    @JsonProperty("language")
    String language;
    @JsonProperty("description")
    String description;
    @JsonProperty("name")
    String name;

    public String getHtml_url() {
        return html_url;
    }

    public Integer getWatchers_count() {
        return watchers_count;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
    public void setWatchers_count(Integer watchers_count) {
        this.watchers_count = watchers_count;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public void setName(String name) {
        this.name = name;
    }
}
