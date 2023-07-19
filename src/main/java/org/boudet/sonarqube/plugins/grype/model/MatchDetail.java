
package org.boudet.sonarqube.plugins.grype.model;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "matcher",
    "searchedBy",
    "found"
})
public class MatchDetail {

    @JsonProperty("type")
    private String type;
    @JsonProperty("matcher")
    private String matcher;
    @JsonProperty("searchedBy")
    private SearchedBy searchedBy;
    @JsonProperty("found")
    private Found found;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("matcher")
    public String getMatcher() {
        return matcher;
    }

    @JsonProperty("matcher")
    public void setMatcher(String matcher) {
        this.matcher = matcher;
    }

    @JsonProperty("searchedBy")
    public SearchedBy getSearchedBy() {
        return searchedBy;
    }

    @JsonProperty("searchedBy")
    public void setSearchedBy(SearchedBy searchedBy) {
        this.searchedBy = searchedBy;
    }

    @JsonProperty("found")
    public Found getFound() {
        return found;
    }

    @JsonProperty("found")
    public void setFound(Found found) {
        this.found = found;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
