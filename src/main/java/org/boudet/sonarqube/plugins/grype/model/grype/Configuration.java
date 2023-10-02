
package org.boudet.sonarqube.plugins.grype.model.grype;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "configPath",
    "verbosity",
    "output",
    "file",
    "distro",
    "add-cpes-if-none",
    "output-template-file",
    "quiet",
    "check-for-app-update",
    "only-fixed",
    "only-notfixed",
    "platform",
    "search",
    "ignore",
    "exclude",
    "db",
    "externalSources",
    "match",
    "dev",
    "fail-on-severity",
    "registry",
    "log",
    "show-suppressed",
    "by-cve",
    "name",
    "default-image-pull-source"
})
public class Configuration {

    @JsonProperty("configPath")
    private String configPath;
    @JsonProperty("verbosity")
    private Integer verbosity;
    @JsonProperty("output")
    private String[] output;
    @JsonProperty("file")
    private String file;
    @JsonProperty("distro")
    private String distro;
    @JsonProperty("add-cpes-if-none")
    private Boolean addCpesIfNone;
    @JsonProperty("output-template-file")
    private String outputTemplateFile;
    @JsonProperty("quiet")
    private Boolean quiet;
    @JsonProperty("check-for-app-update")
    private Boolean checkForAppUpdate;
    @JsonProperty("only-fixed")
    private Boolean onlyFixed;
    @JsonProperty("only-notfixed")
    private Boolean onlyNotfixed;
    @JsonProperty("platform")
    private String platform;
    @JsonProperty("search")
    private Search search;
    @JsonProperty("ignore")
    private Object ignore;
    @JsonProperty("exclude")
    private List<Object> exclude;
    @JsonProperty("db")
    private Db db;
    @JsonProperty("externalSources")
    private ExternalSources externalSources;
    @JsonProperty("match")
    private Match__1 match;
    @JsonProperty("dev")
    private Dev dev;
    @JsonProperty("fail-on-severity")
    private String failOnSeverity;
    @JsonProperty("registry")
    private Registry registry;
    @JsonProperty("log")
    private Log log;
    @JsonProperty("show-suppressed")
    private Boolean showSuppressed;
    @JsonProperty("by-cve")
    private Boolean byCve;
    @JsonProperty("name")
    private String name;
    @JsonProperty("default-image-pull-source")
    private String defaultImagePullSource;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("configPath")
    public String getConfigPath() {
        return configPath;
    }

    @JsonProperty("configPath")
    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    @JsonProperty("verbosity")
    public Integer getVerbosity() {
        return verbosity;
    }

    @JsonProperty("verbosity")
    public void setVerbosity(Integer verbosity) {
        this.verbosity = verbosity;
    }

    @JsonProperty("output")
    public String[] getOutput() {
        return output;
    }

    @JsonProperty("output")
    public void setOutput(String[] output) {
        this.output = output;
    }

    @JsonProperty("file")
    public String getFile() {
        return file;
    }

    @JsonProperty("file")
    public void setFile(String file) {
        this.file = file;
    }

    @JsonProperty("distro")
    public String getDistro() {
        return distro;
    }

    @JsonProperty("distro")
    public void setDistro(String distro) {
        this.distro = distro;
    }

    @JsonProperty("add-cpes-if-none")
    public Boolean getAddCpesIfNone() {
        return addCpesIfNone;
    }

    @JsonProperty("add-cpes-if-none")
    public void setAddCpesIfNone(Boolean addCpesIfNone) {
        this.addCpesIfNone = addCpesIfNone;
    }

    @JsonProperty("output-template-file")
    public String getOutputTemplateFile() {
        return outputTemplateFile;
    }

    @JsonProperty("output-template-file")
    public void setOutputTemplateFile(String outputTemplateFile) {
        this.outputTemplateFile = outputTemplateFile;
    }

    @JsonProperty("quiet")
    public Boolean getQuiet() {
        return quiet;
    }

    @JsonProperty("quiet")
    public void setQuiet(Boolean quiet) {
        this.quiet = quiet;
    }

    @JsonProperty("check-for-app-update")
    public Boolean getCheckForAppUpdate() {
        return checkForAppUpdate;
    }

    @JsonProperty("check-for-app-update")
    public void setCheckForAppUpdate(Boolean checkForAppUpdate) {
        this.checkForAppUpdate = checkForAppUpdate;
    }

    @JsonProperty("only-fixed")
    public Boolean getOnlyFixed() {
        return onlyFixed;
    }

    @JsonProperty("only-fixed")
    public void setOnlyFixed(Boolean onlyFixed) {
        this.onlyFixed = onlyFixed;
    }

    @JsonProperty("only-notfixed")
    public Boolean getOnlyNotfixed() {
        return onlyNotfixed;
    }

    @JsonProperty("only-notfixed")
    public void setOnlyNotfixed(Boolean onlyNotfixed) {
        this.onlyNotfixed = onlyNotfixed;
    }

    @JsonProperty("platform")
    public String getPlatform() {
        return platform;
    }

    @JsonProperty("platform")
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @JsonProperty("search")
    public Search getSearch() {
        return search;
    }

    @JsonProperty("search")
    public void setSearch(Search search) {
        this.search = search;
    }

    @JsonProperty("ignore")
    public Object getIgnore() {
        return ignore;
    }

    @JsonProperty("ignore")
    public void setIgnore(Object ignore) {
        this.ignore = ignore;
    }

    @JsonProperty("exclude")
    public List<Object> getExclude() {
        return exclude;
    }

    @JsonProperty("exclude")
    public void setExclude(List<Object> exclude) {
        this.exclude = exclude;
    }

    @JsonProperty("db")
    public Db getDb() {
        return db;
    }

    @JsonProperty("db")
    public void setDb(Db db) {
        this.db = db;
    }

    @JsonProperty("externalSources")
    public ExternalSources getExternalSources() {
        return externalSources;
    }

    @JsonProperty("externalSources")
    public void setExternalSources(ExternalSources externalSources) {
        this.externalSources = externalSources;
    }

    @JsonProperty("match")
    public Match__1 getMatch() {
        return match;
    }

    @JsonProperty("match")
    public void setMatch(Match__1 match) {
        this.match = match;
    }

    @JsonProperty("dev")
    public Dev getDev() {
        return dev;
    }

    @JsonProperty("dev")
    public void setDev(Dev dev) {
        this.dev = dev;
    }

    @JsonProperty("fail-on-severity")
    public String getFailOnSeverity() {
        return failOnSeverity;
    }

    @JsonProperty("fail-on-severity")
    public void setFailOnSeverity(String failOnSeverity) {
        this.failOnSeverity = failOnSeverity;
    }

    @JsonProperty("registry")
    public Registry getRegistry() {
        return registry;
    }

    @JsonProperty("registry")
    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    @JsonProperty("log")
    public Log getLog() {
        return log;
    }

    @JsonProperty("log")
    public void setLog(Log log) {
        this.log = log;
    }

    @JsonProperty("show-suppressed")
    public Boolean getShowSuppressed() {
        return showSuppressed;
    }

    @JsonProperty("show-suppressed")
    public void setShowSuppressed(Boolean showSuppressed) {
        this.showSuppressed = showSuppressed;
    }

    @JsonProperty("by-cve")
    public Boolean getByCve() {
        return byCve;
    }

    @JsonProperty("by-cve")
    public void setByCve(Boolean byCve) {
        this.byCve = byCve;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("default-image-pull-source")
    public String getDefaultImagePullSource() {
        return defaultImagePullSource;
    }

    @JsonProperty("default-image-pull-source")
    public void setDefaultImagePullSource(String defaultImagePullSource) {
        this.defaultImagePullSource = defaultImagePullSource;
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
