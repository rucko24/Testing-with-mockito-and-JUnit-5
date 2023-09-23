package com.example.mockitobasicuse.reactorfallback.impl;

import com.example.mockitobasicuse.reactorfallback.service.FallBackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class MyFallbackServiceImpl implements FallBackService {

    public Mono<Integer> fallback() {
        log.info("Ejecutando fallback con {}", -1);
        return Mono.just(-1);
    }

}
