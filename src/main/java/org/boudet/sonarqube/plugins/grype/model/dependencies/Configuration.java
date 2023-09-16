package org.boudet.sonarqube.plugins.grype.model.dependencies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown= true)
public class Configuration {
    private String name;

    private List<Dependency> dependencies;

    public String getName() {
        return name;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }
}
