package org.boudet.sonarqube.plugins.grype.measures;

import com.github.packageurl.PackageURL;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import java.io.IOException;
import java.util.Scanner;

public class GradleDependencyResolver {

    private static final Logger LOGGER = Loggers.get(GradleDependencyResolver.class);

    private final InputFile gradleFile;
    private String content = "";

    public GradleDependencyResolver(InputFile gradleFile) {
        LOGGER.debug("Gradle dependency resolver initialized for file {}", gradleFile.uri());
        this.gradleFile = gradleFile;
        try {
            this.content = gradleFile.contents();
            LOGGER.debug("Gradle dependency content : {}", gradleFile.contents());
        } catch (IOException e) {
            LOGGER.warn("Could not read build.gradle file");
        }
    }

    public GradleMatch findLine(PackageURL packageURL) {
        if(packageURL == null) {
            return null;
        }

        try (final Scanner scanner = new Scanner(content)) {
            int linenumber = 0;
            while (scanner.hasNextLine()) {
                final String lineFromFile = scanner.nextLine();
                linenumber++;
                if (lineFromFile.contains(packageURL.getNamespace())
                        && lineFromFile.contains(packageURL.getName())
                        // && lineFromFile.contains(packageURL.getVersion()) // no version test as it is often set with variables instead of raw text
                ) {
                    LOGGER.debug("Found a artifactId, groupId and version match in {}", gradleFile);
                    return new GradleMatch(gradleFile, gradleFile.selectLine(linenumber));
                }
            }
        }
        return null;
    }
}
