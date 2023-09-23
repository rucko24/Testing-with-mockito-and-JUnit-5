package com.example.mockitobasicuse.reactorfallback.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@DisplayName("Manejo de excepcion por fallback")
@ExtendWith(MockitoExtension.class)
class MyReactorServiceImplTest {

    @InjectMocks
    private MyReactorServiceImpl myReactorService;

    @Mock
    private MyFallbackServiceImpl service;

    @Test
    @DisplayName("Cuando es 6 se produce error y retornamos -1")
    void createTenItem() {

        when(service.fallback()).thenReturn(Mono.just(-1));

        StepVerifier.create(myReactorService.createTenItem())
                .expectNext(1, 2, 3, 4, 5, -1, 7, 8, 9, 10)
                .verifyComplete();

        Mockito.verify(service, Mockito.times(1)).fallback();
    }

    @Test
    @DisplayName("Al introducir 6 internamente se produce un Mono.empty()")
    void launchError() {

        StepVerifier.create(myReactorService.launchError(6))
                .verifyComplete();

    }

}