
package org.boudet.sonarqube.plugins.grype.model;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "enable",
    "maven"
})
public class ExternalSources {

    @JsonProperty("enable")
    private Boolean enable;
    @JsonProperty("maven")
    private Maven maven;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("enable")
    public Boolean getEnable() {
        return enable;
    }

    @JsonProperty("enable")
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @JsonProperty("maven")
    public Maven getMaven() {
        return maven;
    }

    @JsonProperty("maven")
    public void setMaven(Maven maven) {
        this.maven = maven;
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
