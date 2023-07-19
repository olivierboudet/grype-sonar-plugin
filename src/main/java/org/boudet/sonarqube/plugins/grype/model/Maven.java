
package org.boudet.sonarqube.plugins.grype.model;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "searchUpstreamBySha1",
    "baseUrl"
})
public class Maven {

    @JsonProperty("searchUpstreamBySha1")
    private Boolean searchUpstreamBySha1;
    @JsonProperty("baseUrl")
    private String baseUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("searchUpstreamBySha1")
    public Boolean getSearchUpstreamBySha1() {
        return searchUpstreamBySha1;
    }

    @JsonProperty("searchUpstreamBySha1")
    public void setSearchUpstreamBySha1(Boolean searchUpstreamBySha1) {
        this.searchUpstreamBySha1 = searchUpstreamBySha1;
    }

    @JsonProperty("baseUrl")
    public String getBaseUrl() {
        return baseUrl;
    }

    @JsonProperty("baseUrl")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
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
