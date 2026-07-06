package com.prguardian.model;

/**
 * Representa um único problema encontrado pela IA em um diff.
 * Espelha exatamente o formato definido no prompt (prompts/pr_guardian_v1.xml):
 * {"category": "...", "severity": "...", "explanation": "..."}
 */
public record ReviewFinding(
        String category,   // bug | security | style | architecture
        String severity,   // low | medium | high
        String explanation
) {
}
