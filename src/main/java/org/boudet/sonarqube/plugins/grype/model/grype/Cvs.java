
package org.boudet.sonarqube.plugins.grype.model.grype;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "version",
    "vector",
    "metrics",
    "vendorMetadata"
})
public class Cvs {

    @JsonProperty("version")
    private String version;
    @JsonProperty("vector")
    private String vector;
    @JsonProperty("metrics")
    private Metrics metrics;
    @JsonProperty("vendorMetadata")
    private VendorMetadata vendorMetadata;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("vector")
    public String getVector() {
        return vector;
    }

    @JsonProperty("vector")
    public void setVector(String vector) {
        this.vector = vector;
    }

    @JsonProperty("metrics")
    public Metrics getMetrics() {
        return metrics;
    }

    @JsonProperty("metrics")
    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    @JsonProperty("vendorMetadata")
    public VendorMetadata getVendorMetadata() {
        return vendorMetadata;
    }

    @JsonProperty("vendorMetadata")
    public void setVendorMetadata(VendorMetadata vendorMetadata) {
        this.vendorMetadata = vendorMetadata;
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
