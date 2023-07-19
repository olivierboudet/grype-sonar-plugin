
package org.boudet.sonarqube.plugins.grype.model;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cache-dir",
    "update-url",
    "ca-cert",
    "auto-update",
    "validate-by-hash-on-start",
    "validate-age",
    "max-allowed-built-age"
})
public class Db {

    @JsonProperty("cache-dir")
    private String cacheDir;
    @JsonProperty("update-url")
    private String updateUrl;
    @JsonProperty("ca-cert")
    private String caCert;
    @JsonProperty("auto-update")
    private Boolean autoUpdate;
    @JsonProperty("validate-by-hash-on-start")
    private Boolean validateByHashOnStart;
    @JsonProperty("validate-age")
    private Boolean validateAge;
    @JsonProperty("max-allowed-built-age")
    private Long maxAllowedBuiltAge;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("cache-dir")
    public String getCacheDir() {
        return cacheDir;
    }

    @JsonProperty("cache-dir")
    public void setCacheDir(String cacheDir) {
        this.cacheDir = cacheDir;
    }

    @JsonProperty("update-url")
    public String getUpdateUrl() {
        return updateUrl;
    }

    @JsonProperty("update-url")
    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    @JsonProperty("ca-cert")
    public String getCaCert() {
        return caCert;
    }

    @JsonProperty("ca-cert")
    public void setCaCert(String caCert) {
        this.caCert = caCert;
    }

    @JsonProperty("auto-update")
    public Boolean getAutoUpdate() {
        return autoUpdate;
    }

    @JsonProperty("auto-update")
    public void setAutoUpdate(Boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
    }

    @JsonProperty("validate-by-hash-on-start")
    public Boolean getValidateByHashOnStart() {
        return validateByHashOnStart;
    }

    @JsonProperty("validate-by-hash-on-start")
    public void setValidateByHashOnStart(Boolean validateByHashOnStart) {
        this.validateByHashOnStart = validateByHashOnStart;
    }

    @JsonProperty("validate-age")
    public Boolean getValidateAge() {
        return validateAge;
    }

    @JsonProperty("validate-age")
    public void setValidateAge(Boolean validateAge) {
        this.validateAge = validateAge;
    }

    @JsonProperty("max-allowed-built-age")
    public Long getMaxAllowedBuiltAge() {
        return maxAllowedBuiltAge;
    }

    @JsonProperty("max-allowed-built-age")
    public void setMaxAllowedBuiltAge(Long maxAllowedBuiltAge) {
        this.maxAllowedBuiltAge = maxAllowedBuiltAge;
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
