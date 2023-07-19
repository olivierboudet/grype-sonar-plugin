/*
 * Example Plugin for SonarQube
 * Copyright (C) 2009-2020 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.boudet.sonarqube.plugins.grype;

import org.boudet.sonarqube.plugins.grype.languages.GrypeLanguage;
import org.boudet.sonarqube.plugins.grype.languages.GrypeQualityProfile;
import org.boudet.sonarqube.plugins.grype.measures.*;
import org.boudet.sonarqube.plugins.grype.metrics.GrypeMetrics;
import org.boudet.sonarqube.plugins.grype.rules.*;
import org.boudet.sonarqube.plugins.grype.web.GrypeReportPageDefinition;
import org.sonar.api.Plugin;

/**
 * This class is the entry point for all extensions. It is referenced in pom.xml.
 */
public class GrypePlugin implements Plugin {

  @Override
  public void define(Context context) {

    context.addExtensions(GrypeLanguage.class, GrypeQualityProfile.class);
    context.addExtensions(CveRuleDefinition.class, GrypeSensor.class, GrypeMetrics.class);

    context.addExtension(GrypeReportPageDefinition.class);
  }
}
