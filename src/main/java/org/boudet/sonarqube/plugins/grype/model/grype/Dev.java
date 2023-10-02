
package org.boudet.sonarqube.plugins.grype.model.grype;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "profile-cpu",
    "profile-mem"
})
public class Dev {

    @JsonProperty("profile-cpu")
    private Boolean profileCpu;
    @JsonProperty("profile-mem")
    private Boolean profileMem;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("profile-cpu")
    public Boolean getProfileCpu() {
        return profileCpu;
    }

    @JsonProperty("profile-cpu")
    public void setProfileCpu(Boolean profileCpu) {
        this.profileCpu = profileCpu;
    }

    @JsonProperty("profile-mem")
    public Boolean getProfileMem() {
        return profileMem;
    }

    @JsonProperty("profile-mem")
    public void setProfileMem(Boolean profileMem) {
        this.profileMem = profileMem;
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
