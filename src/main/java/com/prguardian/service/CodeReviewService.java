package com.prguardian.service;

import com.prguardian.model.ReviewFinding;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
public class CodeReviewService {

    private final RestClient restClient;
    private final String apiKey;
    private final String model;

    public CodeReviewService(
            RestClient.Builder restClientBuilder,
            @Value("${openai.api-url}") String apiUrl,
            @Value("${openai.api-key}") String apiKey,
            @Value("${openai.model}") String model
    ) {
        this.restClient = restClientBuilder.baseUrl(apiUrl).build();
        this.apiKey = apiKey;
        this.model = model;
    }

    public List<ReviewFinding> review(int prNumber, String filePath, String diffContent) throws IOException {

        // STEP 1 (ties to handbook 1.5.2 - prompt versioning)
        // - read prompts/pr_guardian/active_version.txt -> active version name (e.g. "v1")
        // - read prompts/pr_guardian/{version}.xml -> raw template (String)
        // Decision already agreed: path relative to the working directory (naive on purpose
        // for V1 - breaks if packaged into a jar run from elsewhere; guardrail comes in V2).
        String version = Files.readString(Path.of("prompts/pr_guardian/active_version.txt")).trim();
        String promptTemplate = Files.readString(Path.of("prompts/pr_guardian/" + version + ".xml"));

        // STEP 2 (ties to handbook 1.4.4 and 1.4.6 - nesting + anti prompt-injection)
        // - generate a unique diffId per call (e.g. UUID.randomUUID().toString().substring(0, 8))
        // - substitute in the template: {{PR_NUMBER}}, {{FILE_PATH}}, {{DIFF_ID}}, {{DIFF_CONTENT}}
        // Note: the SAME diffId goes in both the opening and closing <diff id="..."> tags
        String diffId = UUID.randomUUID().toString().substring(0, 8);
        String assembledPrompt = promptTemplate
                .replace("{{PR_NUMBER}}", String.valueOf(prNumber))
                .replace("{{FILE_PATH}}", filePath)
                .replace("{{DIFF_ID}}", diffId)
                .replace("{{DIFF_CONTENT}}", diffContent);

        // STEP 3 (new - plain HTTP mechanics, no prompt engineering principle here)
        // - build the OpenAI Chat Completions API body:
        //   { "model": model, "messages": [ { "role": "user", "content": assembledPrompt } ] }
        // - header: Authorization: "Bearer " + apiKey
        // - restClient.post().uri("").header(...).body(...).retrieve().body(String.class)
        String rawResponse = null; // TODO

        // STEP 4 (ties to handbook 1.3 - CoT: <analysis> separated from <output>)
        // - extract only the content inside <output>...</output> from rawResponse
        // - this is deliberately fragile in V1 (naive/no guardrails) - if the model strays
        //   from the format, this breaks. That observed fragility is the "failure" that drives V2.
        String rawJson = null; // TODO

        // STEP 5 (plain mechanics - Jackson already comes with spring-boot-starter-web)
        // - deserialize rawJson into List<ReviewFinding> (the record already mirrors the prompt schema)
        List<ReviewFinding> findings = null; // TODO

        return findings;
    }
}
