package org.boudet.sonarqube.plugins.grype.model.dependencies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown= true)
public class Dependency {
    private String name;

    private boolean hasConflict;

    private List<Dependency> children;

    public String getName() {
        return name;
    }

    public boolean isHasConflict() {
        return hasConflict;
    }

    public List<Dependency> getChildren() {
        return children;
    }
}
