package com.example.mockitobasicuse.reactorfallback.impl;

import com.example.mockitobasicuse.reactorfallback.service.MyReactorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyReactorServiceImpl implements MyReactorService {

    private final MyFallbackServiceImpl service;

    public Flux<Integer> createTenItem() {
        return Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)// Flux.range :D
                .doOnError(error -> log.info("Error with item {}", error))
                .flatMap(item -> this.launchError(item)
                        .switchIfEmpty(Mono.defer(this.service::fallback)))
                .doOnNext(onNext -> log.info("onNext {}", onNext));
    }

    public Mono<Integer> launchError(final Integer item) {
        if (item == 6) {
            return Mono.empty();
        }
        return Mono.just(item);
    }

}
