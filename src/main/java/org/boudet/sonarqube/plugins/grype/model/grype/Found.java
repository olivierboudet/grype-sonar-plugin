
package org.boudet.sonarqube.plugins.grype.model.grype;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "versionConstraint",
    "vulnerabilityID"
})
public class Found {

    @JsonProperty("versionConstraint")
    private String versionConstraint;
    @JsonProperty("vulnerabilityID")
    private String vulnerabilityID;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("versionConstraint")
    public String getVersionConstraint() {
        return versionConstraint;
    }

    @JsonProperty("versionConstraint")
    public void setVersionConstraint(String versionConstraint) {
        this.versionConstraint = versionConstraint;
    }

    @JsonProperty("vulnerabilityID")
    public String getVulnerabilityID() {
        return vulnerabilityID;
    }

    @JsonProperty("vulnerabilityID")
    public void setVulnerabilityID(String vulnerabilityID) {
        this.vulnerabilityID = vulnerabilityID;
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
