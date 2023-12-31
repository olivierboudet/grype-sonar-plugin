SonarQube Grype Plugin
==========

A SonarQube plugin to process Grype reports (JSON and HTML) to track vulnerabilities.

# Compatibility

The following table lists the plugin version compatible with different SonarQube versions.

| SonarQube Version | Plugin Version |
|-------------------|----------------|
| 9.9+              | 1.0.0          |

# Installation

Download the latest jar from releases page, copy it to the `/opt/sonarqube/extensions/plugins/` directory and restart SonarQube.

# Usage

## Getting started

Run a grype analysis with output as JSON and HTML with a template.
```
grype sonarqube:lts -o json=/tmp/report.json -o template=/tmp/report.html -t /tmp/grype.template.html
```

Then import issues to SonarQube by setting following properties:

| Property                    | Description              |
|-----------------------------|--------------------------|
| sonar.grype.reportPath      | Path to the JSON report. |
| sonar.grype.htmlReportPath  | Path to the HTML report. |

See following chapters on usage with you build tool. 

## Using gradle

### Basic usage

Add in your `gradle.properties`:

```
systemProp.sonar.grype.reportPath=grype.json
systemProp.sonar.grype.htmlReportPath=grype.html
```

### Dependencies detection

This plugin can detects dependencies and transitive dependencies in gradle files using reports generated by [dependency-report plugin](https://plugins.gradle.org/plugin/io.github.kota65535.dependency-report).
Add in your `build.gradle` file:

```
plugins {
    id("io.github.kota65535.dependency-report") version "+"
}
```

Then add the `sonar.dependencies.reportPath` property to the sonar scanner.

| Property                      | Description                                                    |
|-------------------------------|----------------------------------------------------------------|
| sonar.dependencies.reportPath | Path to the JSON report generated by dependency-report plugin. |

Add in your `gradle.properties` file:

```
systemProp.sonar.dependencies.reportPath=build/reports/project/dependencies.json
```

At this time, dependency detection in `build.gradle` file does not check for version. Indeed, versions are often declared using variable, which this plugin can not resolve correctly, as `build.gradle` file is only parsed as a raw text file.
