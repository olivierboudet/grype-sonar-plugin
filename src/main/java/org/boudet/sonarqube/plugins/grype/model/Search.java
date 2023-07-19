
package org.boudet.sonarqube.plugins.grype.model;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "scope",
    "unindexed-archives",
    "indexed-archives"
})
public class Search {

    @JsonProperty("scope")
    private String scope;
    @JsonProperty("unindexed-archives")
    private Boolean unindexedArchives;
    @JsonProperty("indexed-archives")
    private Boolean indexedArchives;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("scope")
    public String getScope() {
        return scope;
    }

    @JsonProperty("scope")
    public void setScope(String scope) {
        this.scope = scope;
    }

    @JsonProperty("unindexed-archives")
    public Boolean getUnindexedArchives() {
        return unindexedArchives;
    }

    @JsonProperty("unindexed-archives")
    public void setUnindexedArchives(Boolean unindexedArchives) {
        this.unindexedArchives = unindexedArchives;
    }

    @JsonProperty("indexed-archives")
    public Boolean getIndexedArchives() {
        return indexedArchives;
    }

    @JsonProperty("indexed-archives")
    public void setIndexedArchives(Boolean indexedArchives) {
        this.indexedArchives = indexedArchives;
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
