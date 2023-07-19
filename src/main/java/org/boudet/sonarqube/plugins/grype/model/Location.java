
package org.boudet.sonarqube.plugins.grype.model;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "path",
    "layerID"
})
public class Location {

    @JsonProperty("path")
    private String path;
    @JsonProperty("layerID")
    private String layerID;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("layerID")
    public String getLayerID() {
        return layerID;
    }

    @JsonProperty("layerID")
    public void setLayerID(String layerID) {
        this.layerID = layerID;
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
