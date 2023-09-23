package com.example.mockitobasicuse.classtotest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class SpyMePleaseTest {

    private SpyMePlease spyMePlease = new SpyMePlease();

    @Test
    @DisplayName("Testeando metodo hola, y no arroja ninguna excepcion")
    void hola() {
        assertThatCode(() -> spyMePlease.hola()).doesNotThrowAnyException();
    }
}