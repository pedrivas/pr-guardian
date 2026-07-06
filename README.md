# PR Guardian

Hands-on learning project in **AI Product Engineering**: an AI assistant
that analyzes Pull Requests and returns a structured review (bugs, security
risks, architecture issues, style).

Stack: Java 21 + Spring Boot (backend), React (frontend, future).

The project is built in increasing levels of maturity, documented in
[`docs/AI_Product_Engineer_Handbook.md`](docs/AI_Product_Engineer_Handbook.md):

- [ ] **V1 — Naive**: loose prompt, no structure, no guardrails
- [ ] **V2 — Guardrails**: JSON Schema, validation, retry
- [ ] **V3 — Evals**: golden dataset, metrics, CI
- [ ] **V4 — Agent**: tools (file search, static analysis)
- [ ] **V5 — Product**: usage metrics, feature flags, feedback loop
- [ ] **V6 — Docs**: ADRs, post-mortem, RFC

## Structure

```
src/main/java/com/prguardian/          # Spring Boot application
prompts/pr_guardian/                   # versioned prompts (v1.xml, active_version.txt)
docs/AI_Product_Engineer_Handbook.md   # living learning handbook
PROMPT_CHANGELOG.md                    # prompt change history
```

## Running locally

```bash
export ANTHROPIC_API_KEY=sk-ant-...
./mvnw spring-boot:run
```
