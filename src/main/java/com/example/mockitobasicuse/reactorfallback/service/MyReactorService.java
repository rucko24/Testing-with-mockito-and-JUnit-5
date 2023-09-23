package com.example.mockitobasicuse.reactorfallback.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MyReactorService {
    Flux<Integer> createTenItem();

    Mono<Integer> launchError(final Integer item);
}
