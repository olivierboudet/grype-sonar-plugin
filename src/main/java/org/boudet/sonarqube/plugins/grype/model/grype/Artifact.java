
package org.boudet.sonarqube.plugins.grype.model.grype;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "version",
    "type",
    "locations",
    "language",
    "licenses",
    "cpes",
    "purl",
    "upstreams"
})
public class Artifact {

    @JsonProperty("name")
    private String name;
    @JsonProperty("version")
    private String version;
    @JsonProperty("type")
    private String type;
    @JsonProperty("locations")
    private List<Location> locations;
    @JsonProperty("language")
    private String language;
    @JsonProperty("licenses")
    private List<Object> licenses;
    @JsonProperty("cpes")
    private List<String> cpes;
    @JsonProperty("purl")
    private String purl;
    @JsonProperty("upstreams")
    private List<Upstream> upstreams;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("locations")
    public List<Location> getLocations() {
        return locations;
    }

    @JsonProperty("locations")
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("licenses")
    public List<Object> getLicenses() {
        return licenses;
    }

    @JsonProperty("licenses")
    public void setLicenses(List<Object> licenses) {
        this.licenses = licenses;
    }

    @JsonProperty("cpes")
    public List<String> getCpes() {
        return cpes;
    }

    @JsonProperty("cpes")
    public void setCpes(List<String> cpes) {
        this.cpes = cpes;
    }

    @JsonProperty("purl")
    public String getPurl() {
        return purl;
    }

    @JsonProperty("purl")
    public void setPurl(String purl) {
        this.purl = purl;
    }

    @JsonProperty("upstreams")
    public List<Upstream> getUpstreams() {
        return upstreams;
    }

    @JsonProperty("upstreams")
    public void setUpstreams(List<Upstream> upstreams) {
        this.upstreams = upstreams;
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
