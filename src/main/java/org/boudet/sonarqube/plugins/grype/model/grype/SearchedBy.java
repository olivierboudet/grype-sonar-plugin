
package org.boudet.sonarqube.plugins.grype.model.grype;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "distro",
    "namespace",
    "package"
})
public class SearchedBy {

    @JsonProperty("distro")
    private Distro distro;
    @JsonProperty("namespace")
    private String namespace;
    @JsonProperty("package")
    private Package _package;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("distro")
    public Distro getDistro() {
        return distro;
    }

    @JsonProperty("distro")
    public void setDistro(Distro distro) {
        this.distro = distro;
    }

    @JsonProperty("namespace")
    public String getNamespace() {
        return namespace;
    }

    @JsonProperty("namespace")
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @JsonProperty("package")
    public Package getPackage() {
        return _package;
    }

    @JsonProperty("package")
    public void setPackage(Package _package) {
        this._package = _package;
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
