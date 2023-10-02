
package org.boudet.sonarqube.plugins.grype.model.grype;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "structured",
    "level",
    "file"
})
public class Log {

    @JsonProperty("structured")
    private Boolean structured;
    @JsonProperty("level")
    private String level;
    @JsonProperty("file")
    private String file;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("structured")
    public Boolean getStructured() {
        return structured;
    }

    @JsonProperty("structured")
    public void setStructured(Boolean structured) {
        this.structured = structured;
    }

    @JsonProperty("level")
    public String getLevel() {
        return level;
    }

    @JsonProperty("level")
    public void setLevel(String level) {
        this.level = level;
    }

    @JsonProperty("file")
    public String getFile() {
        return file;
    }

    @JsonProperty("file")
    public void setFile(String file) {
        this.file = file;
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
