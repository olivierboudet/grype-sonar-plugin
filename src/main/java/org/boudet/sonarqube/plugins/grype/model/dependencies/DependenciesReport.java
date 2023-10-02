package org.boudet.sonarqube.plugins.grype.model.dependencies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown= true)
public class DependenciesReport {
    private String name;

    private Project project;

    public String getName() {
        return name;
    }

    public Project getProject() {
        return project;
    }
}
