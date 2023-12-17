package com.unittest.codecoverage.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrafficTest {

    private Traffic traffic;

    @BeforeEach
    public void setUp() {
        traffic = new Traffic();
    }

    @Test
    public void testCurrentTrafficLight() {
        TrafficLigth expected = TrafficLigth.YELLOW;
        traffic.setCurrentTrafficLight(expected);
        TrafficLigth actual = traffic.getCurrentTrafficLight();
        assertEquals(expected, actual);
    }

    @Test
    public void testIntenseCarTraffic() {
        boolean expected = true;
        traffic.setIntenseCarTraffic(expected);
        boolean actual = traffic.intenseCarTraffic();
        assertEquals(expected, actual);
    }

    @Test
    public void testMaxSpeedAllowed() {
        short expected = 60;
        traffic.setMaxSpeedAllowed(expected);
        short actual = traffic.getMaxSpeedAllowed();
        assertEquals(expected, actual);
    }

    @Test
    public void testMinSpeedAllowed() {
        short expected = 20;
        traffic.setMinSpeedAllowed(expected);
        short actual = traffic.getMinSpeedAllowed();
        assertEquals(expected, actual);
    }

    @Test
    public void testStreetDirectionFlow() {
        StreetDirectionFlow expected = StreetDirectionFlow.ONE_WAY;
        traffic.setStreetDirectionFlow(expected);
        StreetDirectionFlow actual = traffic.getStreetDirectionFlow();
        assertEquals(expected, actual);
        traffic.setStreetDirectionFlow(StreetDirectionFlow.TWO_WAY);
        actual = traffic.getStreetDirectionFlow();
        assertEquals(StreetDirectionFlow.TWO_WAY, actual);
    }
}
