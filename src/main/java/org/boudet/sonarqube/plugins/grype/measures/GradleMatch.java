package org.boudet.sonarqube.plugins.grype.measures;

import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.TextRange;

public class GradleMatch {
    private final InputFile inputFile;
    private final TextRange textRange;

    public GradleMatch(InputFile gradleFile, TextRange textRange) {
        this.inputFile = gradleFile;
        this.textRange = textRange;
    }

    public InputFile getInputFile() {
        return inputFile;
    }

    public TextRange getTextRange() {
        return textRange;
    }
}
