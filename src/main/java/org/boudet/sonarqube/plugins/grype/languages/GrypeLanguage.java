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
package org.boudet.sonarqube.plugins.grype.languages;

import org.sonar.api.config.Configuration;
import org.sonar.api.resources.AbstractLanguage;

/**
 * This class defines the fictive Grype language.
 */
public final class GrypeLanguage extends AbstractLanguage {

  public static final String NAME = "Grype";
  public static final String KEY = "grype";

  public GrypeLanguage(Configuration config) {
    super(KEY, NAME);
  }


  @Override
  public String[] getFileSuffixes() {
    return new String[0];
  }
}
