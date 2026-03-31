package com.example.demo;
import com.example.demo.service.GreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GreetingServiceTest {
    private GreetingService greetingService;

    @BeforeEach
    void setUp() { greetingService = new GreetingService(); }

    @Test
    void greet_returnsExpectedMessage() {
        assertEquals("Hello, Kalan! This is the DevSecOps demo app.", greetingService.greet("Kalan"));
    }

    @Test
    void greet_withWorld_returnsExpectedMessage() {
        assertTrue(greetingService.greet("World").contains("World"));
    }

    @Test
    void greet_withBlankName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> greetingService.greet(""));
    }

    @Test
    void greet_withNullName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> greetingService.greet(null));
    }
}
