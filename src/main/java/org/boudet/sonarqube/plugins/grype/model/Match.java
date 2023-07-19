
package org.boudet.sonarqube.plugins.grype.model;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "vulnerability",
    "relatedVulnerabilities",
    "matchDetails",
    "artifact"
})
public class Match {

    @JsonProperty("vulnerability")
    private Vulnerability vulnerability;
    @JsonProperty("relatedVulnerabilities")
    private List<RelatedVulnerability> relatedVulnerabilities;
    @JsonProperty("matchDetails")
    private List<MatchDetail> matchDetails;
    @JsonProperty("artifact")
    private Artifact artifact;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("vulnerability")
    public Vulnerability getVulnerability() {
        return vulnerability;
    }

    @JsonProperty("vulnerability")
    public void setVulnerability(Vulnerability vulnerability) {
        this.vulnerability = vulnerability;
    }

    @JsonProperty("relatedVulnerabilities")
    public List<RelatedVulnerability> getRelatedVulnerabilities() {
        return relatedVulnerabilities;
    }

    @JsonProperty("relatedVulnerabilities")
    public void setRelatedVulnerabilities(List<RelatedVulnerability> relatedVulnerabilities) {
        this.relatedVulnerabilities = relatedVulnerabilities;
    }

    @JsonProperty("matchDetails")
    public List<MatchDetail> getMatchDetails() {
        return matchDetails;
    }

    @JsonProperty("matchDetails")
    public void setMatchDetails(List<MatchDetail> matchDetails) {
        this.matchDetails = matchDetails;
    }

    @JsonProperty("artifact")
    public Artifact getArtifact() {
        return artifact;
    }

    @JsonProperty("artifact")
    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
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
