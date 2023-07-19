
package org.boudet.sonarqube.plugins.grype.model;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "using-cpes"
})
public class Javascript {

    @JsonProperty("using-cpes")
    private Boolean usingCpes;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("using-cpes")
    public Boolean getUsingCpes() {
        return usingCpes;
    }

    @JsonProperty("using-cpes")
    public void setUsingCpes(Boolean usingCpes) {
        this.usingCpes = usingCpes;
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
