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
