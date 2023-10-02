
package org.boudet.sonarqube.plugins.grype.model.grype;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "insecure-skip-tls-verify",
    "insecure-use-http",
    "auth"
})
public class Registry {

    @JsonProperty("insecure-skip-tls-verify")
    private Boolean insecureSkipTlsVerify;
    @JsonProperty("insecure-use-http")
    private Boolean insecureUseHttp;
    @JsonProperty("auth")
    private List<Object> auth;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("insecure-skip-tls-verify")
    public Boolean getInsecureSkipTlsVerify() {
        return insecureSkipTlsVerify;
    }

    @JsonProperty("insecure-skip-tls-verify")
    public void setInsecureSkipTlsVerify(Boolean insecureSkipTlsVerify) {
        this.insecureSkipTlsVerify = insecureSkipTlsVerify;
    }

    @JsonProperty("insecure-use-http")
    public Boolean getInsecureUseHttp() {
        return insecureUseHttp;
    }

    @JsonProperty("insecure-use-http")
    public void setInsecureUseHttp(Boolean insecureUseHttp) {
        this.insecureUseHttp = insecureUseHttp;
    }

    @JsonProperty("auth")
    public List<Object> getAuth() {
        return auth;
    }

    @JsonProperty("auth")
    public void setAuth(List<Object> auth) {
        this.auth = auth;
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
