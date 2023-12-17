package com.unittest.codecoverage.models;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FootpassengerTest {

    private Footpassenger footpassenger;

    @BeforeEach
    public void setUp() {
        footpassenger = new Footpassenger();
    }

    @Test
    public void testAge() {
        footpassenger.setCrossedTheCrosswalk(true);
        boolean actual = footpassenger.crossedTheCrosswalk();
        assertTrue(actual);
    }
}