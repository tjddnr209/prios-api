package com.furyjoker.priosapi.ai.controller;

import com.furyjoker.priosapi.ai.client.ClaudeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class AiTestController {

    private final ClaudeClient claudeClient;

    @GetMapping("/claude")
    public String testClaude(@RequestParam String prompt) {
        return claudeClient.getPrioritizedList(prompt).toString();
    }
}
