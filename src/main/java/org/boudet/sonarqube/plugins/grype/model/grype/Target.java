
package org.boudet.sonarqube.plugins.grype.model.grype;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "userInput",
    "imageID",
    "manifestDigest",
    "mediaType",
    "tags",
    "imageSize",
    "layers",
    "manifest",
    "config",
    "repoDigests",
    "architecture",
    "os"
})
public class Target {

    @JsonProperty("userInput")
    private String userInput;
    @JsonProperty("imageID")
    private String imageID;
    @JsonProperty("manifestDigest")
    private String manifestDigest;
    @JsonProperty("mediaType")
    private String mediaType;
    @JsonProperty("tags")
    private List<String> tags;
    @JsonProperty("imageSize")
    private Integer imageSize;
    @JsonProperty("layers")
    private List<Layer> layers;
    @JsonProperty("manifest")
    private String manifest;
    @JsonProperty("config")
    private String config;
    @JsonProperty("repoDigests")
    private List<String> repoDigests;
    @JsonProperty("architecture")
    private String architecture;
    @JsonProperty("os")
    private String os;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("userInput")
    public String getUserInput() {
        return userInput;
    }

    @JsonProperty("userInput")
    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    @JsonProperty("imageID")
    public String getImageID() {
        return imageID;
    }

    @JsonProperty("imageID")
    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    @JsonProperty("manifestDigest")
    public String getManifestDigest() {
        return manifestDigest;
    }

    @JsonProperty("manifestDigest")
    public void setManifestDigest(String manifestDigest) {
        this.manifestDigest = manifestDigest;
    }

    @JsonProperty("mediaType")
    public String getMediaType() {
        return mediaType;
    }

    @JsonProperty("mediaType")
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("imageSize")
    public Integer getImageSize() {
        return imageSize;
    }

    @JsonProperty("imageSize")
    public void setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
    }

    @JsonProperty("layers")
    public List<Layer> getLayers() {
        return layers;
    }

    @JsonProperty("layers")
    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }

    @JsonProperty("manifest")
    public String getManifest() {
        return manifest;
    }

    @JsonProperty("manifest")
    public void setManifest(String manifest) {
        this.manifest = manifest;
    }

    @JsonProperty("config")
    public String getConfig() {
        return config;
    }

    @JsonProperty("config")
    public void setConfig(String config) {
        this.config = config;
    }

    @JsonProperty("repoDigests")
    public List<String> getRepoDigests() {
        return repoDigests;
    }

    @JsonProperty("repoDigests")
    public void setRepoDigests(List<String> repoDigests) {
        this.repoDigests = repoDigests;
    }

    @JsonProperty("architecture")
    public String getArchitecture() {
        return architecture;
    }

    @JsonProperty("architecture")
    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    @JsonProperty("os")
    public String getOs() {
        return os;
    }

    @JsonProperty("os")
    public void setOs(String os) {
        this.os = os;
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
