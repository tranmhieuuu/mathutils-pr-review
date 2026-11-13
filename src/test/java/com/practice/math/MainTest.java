package com.practice.math;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

/** Kiem tra luong main de bao phu ca code khoi dong ung dung. */
class MainTest {

    @Test
    void shouldRunMainSuccessfully() {
        assertDoesNotThrow(() -> Main.main(new String[0]));
    }
}
