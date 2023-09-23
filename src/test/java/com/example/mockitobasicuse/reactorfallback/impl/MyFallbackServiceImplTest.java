package com.example.mockitobasicuse.reactorfallback.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;


@ExtendWith(MockitoExtension.class)
class MyFallbackServiceImplTest {

    @InjectMocks
    private MyFallbackServiceImpl myFallbackService;

    @Test
    void fallback() {
        StepVerifier.create(myFallbackService.fallback())
                .expectNext(-1)
                .verifyComplete();
    }
}