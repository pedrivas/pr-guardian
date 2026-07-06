# PR Guardian

Projeto de aprendizado prático em **AI Product Engineering**: um assistente de IA
que analisa Pull Requests e devolve uma revisão estruturada (bugs, riscos de
segurança, problemas de arquitetura, estilo).

Stack: Java 17 + Spring Boot (backend), React (frontend, futuro).

O projeto é construído em níveis crescentes de maturidade, documentados em
[`docs/AI_Product_Engineer_Handbook.md`](docs/AI_Product_Engineer_Handbook.md):

- [ ] **V1 — Naive**: prompt solto, sem estrutura, sem guardrails
- [ ] **V2 — Guardrails**: JSON Schema, validação, retry
- [ ] **V3 — Evals**: golden dataset, métricas, CI
- [ ] **V4 — Agent**: ferramentas (buscar arquivos, análise estática)
- [ ] **V5 — Product**: métricas de uso, feature flags, feedback loop
- [ ] **V6 — Docs**: ADRs, post-mortem, RFC

## Estrutura

```
src/main/java/com/prguardian/          # aplicação Spring Boot
prompts/pr_guardian/                   # prompts versionados (v1.xml, active_version.txt)
docs/AI_Product_Engineer_Handbook.md   # handbook vivo do aprendizado
PROMPT_CHANGELOG.md                    # histórico de mudanças de prompt
```

## Rodando localmente

```bash
export ANTHROPIC_API_KEY=sk-ant-...
./mvnw spring-boot:run
```
