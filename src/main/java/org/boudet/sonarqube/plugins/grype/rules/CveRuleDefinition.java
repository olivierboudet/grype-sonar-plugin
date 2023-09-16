package org.boudet.sonarqube.plugins.grype.rules;

import org.sonar.api.code.CodeCharacteristic;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.rule.RuleStatus;
import org.sonar.api.rule.Severity;
import org.sonar.api.rules.RuleType;
import org.sonar.api.server.rule.RuleDescriptionSection;
import org.sonar.api.server.rule.RulesDefinition;

import static org.sonar.api.server.rule.RuleDescriptionSection.RuleDescriptionSectionKeys.HOW_TO_FIX_SECTION_KEY;
import static org.sonar.api.server.rule.RuleDescriptionSection.RuleDescriptionSectionKeys.ROOT_CAUSE_SECTION_KEY;

public class CveRuleDefinition implements RulesDefinition {

    public static final String REPOSITORY = "grype";

    public static final RuleKey RULE_CVE = RuleKey.of(REPOSITORY, "ComponentsWithCVE");

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(REPOSITORY, "grype").setName("grype");

        repository.createRule(RULE_CVE.rule())
            .setType(RuleType.SECURITY_HOTSPOT)
            //.setCharacteristic(CodeCharacteristic.SECURE)
            .addTags("cve", "security", "vulnerability")
            .setName("Components with Known Vulnerabilities")
            .setSeverity(Severity.MAJOR)
            .setStatus(RuleStatus.READY)
            .setHtmlDescription("Including a dependency with a known vulnerability exposes your application to a security breach")
            .addDescriptionSection(descriptionSection(RuleDescriptionSection.RuleDescriptionSectionKeys.INTRODUCTION_SECTION_KEY, "One component is known to be affected by a vulnerability.", null))
            .addDescriptionSection(descriptionSection(ROOT_CAUSE_SECTION_KEY, "The root cause of this issue is this and that.", null))
            .addDescriptionSection(descriptionSection(HOW_TO_FIX_SECTION_KEY, "Upgrade the component", null));

        repository.done();
    }

    private static RuleDescriptionSection descriptionSection(String sectionKey, String htmlContent, org.sonar.api.server.rule.Context context) {
        return RuleDescriptionSection.builder()
                .sectionKey(sectionKey)
                .htmlContent(htmlContent)
                //Optional context - can be any framework or component for which you want to create detailed description
                .context(context)
                .build();
    }
}
