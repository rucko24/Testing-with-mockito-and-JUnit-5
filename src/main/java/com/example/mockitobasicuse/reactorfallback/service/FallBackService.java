package com.example.mockitobasicuse.reactorfallback.service;

import reactor.core.publisher.Mono;

public interface FallBackService {

    Mono<Integer> fallback();

}
