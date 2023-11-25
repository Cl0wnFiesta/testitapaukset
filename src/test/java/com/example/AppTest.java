package com.example;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.Time;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @ParameterizedTest(name = "{index} => time={0}, expected={1}")
    @CsvSource({
            "00:00:00, 0",     // Alaraja
            "00:00:01, 1",     // Sekunnin testi
            "00:01:00, 60",    // Minuutin testi
            "01:00:00, 3600",  // Tunnin testi
            "00:01:01, 61",    // Minuutin ja sekunnin testi
            "01:00:01, 3601",  // Tunnin ja sekunnin testi
            "01:01:00, 3660",  // Tunnin ja minuutin testi
            "01:01:01, 3661",  // Tunnin, minuutin ja sekunnin testi
            "23:59:59, 86399", // Suurin mahdollinen aika
            "01:00:00:00, 3600", // Kieltoraja
    })
    public void testTimeToSec(String time, int expected) {
        assertEquals(expected, TimeUtils.timeToSec(time));
    }

    @ParameterizedTest(name = "{index} => a={0}, expected={1}")
    @CsvSource({
            "-1, -1",         // Kielletty raja-arvo
            "0, 0:00:00",     // Alaraja
            "1, 0:00:01",     // Sekunnin testi
            "60, 0:01:00",    // Minuutin testi
            "3600, 1:00:00",  // Tunnin testi
            "61, 0:01:01",    // Minuutin ja sekunnin testi
            "3601, 1:00:01",  // Tunnin ja sekunnin testi
            "3660, 1:01:00",  // Tunnin ja minuutin testi
            "3661, 1:01:01",  // Tunnin, minuutin ja sekunnin testi
            "32000, 8:53:20", // Vanha Kieltoraja
            "31999, 8:53:19", // Suurin Vanha mahdollinen hyväksytty aika
            
            "39599, 10:59:59", // 10h 59m 59s
            "86399, 23:59:59", // Suurin mahdollinen aika päivässä
            "86400, -1",      // Kieltoraja koska 1 day
    })
    public void testSecToTime(int a, String expected) {
        assertEquals(expected, TimeUtils.secToTime(a));
    }

}
