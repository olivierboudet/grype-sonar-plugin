package org.boudet.sonarqube.plugins.grype.measures;

import java.io.IOException;

public class ParsingException extends Throwable {
    public ParsingException(String message, Throwable e) {
        super(message, e);
    }
}
