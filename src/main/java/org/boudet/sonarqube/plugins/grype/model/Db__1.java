
package org.boudet.sonarqube.plugins.grype.model;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "built",
    "schemaVersion",
    "location",
    "checksum",
    "error"
})
public class Db__1 {

    @JsonProperty("built")
    private String built;
    @JsonProperty("schemaVersion")
    private Integer schemaVersion;
    @JsonProperty("location")
    private String location;
    @JsonProperty("checksum")
    private String checksum;
    @JsonProperty("error")
    private Object error;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("built")
    public String getBuilt() {
        return built;
    }

    @JsonProperty("built")
    public void setBuilt(String built) {
        this.built = built;
    }

    @JsonProperty("schemaVersion")
    public Integer getSchemaVersion() {
        return schemaVersion;
    }

    @JsonProperty("schemaVersion")
    public void setSchemaVersion(Integer schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("checksum")
    public String getChecksum() {
        return checksum;
    }

    @JsonProperty("checksum")
    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    @JsonProperty("error")
    public Object getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(Object error) {
        this.error = error;
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
