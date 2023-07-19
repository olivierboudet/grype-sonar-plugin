
package org.boudet.sonarqube.plugins.grype.model;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "java",
    "dotnet",
    "golang",
    "javascript",
    "python",
    "ruby",
    "stock"
})
public class Match__1 {

    @JsonProperty("java")
    private Java java;
    @JsonProperty("dotnet")
    private Dotnet dotnet;
    @JsonProperty("golang")
    private Golang golang;
    @JsonProperty("javascript")
    private Javascript javascript;
    @JsonProperty("python")
    private Python python;
    @JsonProperty("ruby")
    private Ruby ruby;
    @JsonProperty("stock")
    private Stock stock;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("java")
    public Java getJava() {
        return java;
    }

    @JsonProperty("java")
    public void setJava(Java java) {
        this.java = java;
    }

    @JsonProperty("dotnet")
    public Dotnet getDotnet() {
        return dotnet;
    }

    @JsonProperty("dotnet")
    public void setDotnet(Dotnet dotnet) {
        this.dotnet = dotnet;
    }

    @JsonProperty("golang")
    public Golang getGolang() {
        return golang;
    }

    @JsonProperty("golang")
    public void setGolang(Golang golang) {
        this.golang = golang;
    }

    @JsonProperty("javascript")
    public Javascript getJavascript() {
        return javascript;
    }

    @JsonProperty("javascript")
    public void setJavascript(Javascript javascript) {
        this.javascript = javascript;
    }

    @JsonProperty("python")
    public Python getPython() {
        return python;
    }

    @JsonProperty("python")
    public void setPython(Python python) {
        this.python = python;
    }

    @JsonProperty("ruby")
    public Ruby getRuby() {
        return ruby;
    }

    @JsonProperty("ruby")
    public void setRuby(Ruby ruby) {
        this.ruby = ruby;
    }

    @JsonProperty("stock")
    public Stock getStock() {
        return stock;
    }

    @JsonProperty("stock")
    public void setStock(Stock stock) {
        this.stock = stock;
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
