package com.prguardian.model;

/**
 * Represents a single issue found by the AI in a diff.
 * Mirrors exactly the format defined in the prompt (prompts/pr_guardian/v1.xml):
 * {"category": "...", "severity": "...", "explanation": "..."}
 */
public record ReviewFinding(
        String category,   // bug | security | style | architecture
        String severity,   // low | medium | high
        String explanation
) {
}
