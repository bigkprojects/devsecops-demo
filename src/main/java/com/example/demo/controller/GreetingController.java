package com.example.demo.controller;
import com.example.demo.service.GreetingService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GreetingController {
    private final GreetingService greetingService;
    private final MeterRegistry meterRegistry;

    public GreetingController(GreetingService greetingService, MeterRegistry meterRegistry) {
        this.greetingService = greetingService;
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/hello")
    @Timed(value = "greeting.request.duration")
    public ResponseEntity<Map<String, String>> hello(@RequestParam(defaultValue = "World") String name) {
        meterRegistry.counter("greeting.requests.total", "name", name).increment();
        return ResponseEntity.ok(Map.of("message", greetingService.greet(name)));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP", "version", "1.0.0"));
    }
}
