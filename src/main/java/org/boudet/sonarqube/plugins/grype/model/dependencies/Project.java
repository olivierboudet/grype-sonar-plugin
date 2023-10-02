package org.boudet.sonarqube.plugins.grype.model.dependencies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown= true)
public class Project {
    private List<Configuration> configurations;

    public List<Configuration> getConfigurations() {
        return configurations;
    }
}
