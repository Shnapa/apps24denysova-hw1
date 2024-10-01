package ua.edu.ucu.tempseries;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import ua.edu.ucu.apps.tempseries.TempSummaryStatistics;

public class TempSummaryStatisticsTest {

    @Test
    public void testConstructorAndGetters() {
        double avgTemp = 25.0;
        double devTemp = 5.0;
        double minTemp = 20.0;
        double maxTemp = 30.0;

        TempSummaryStatistics stats = new TempSummaryStatistics(avgTemp, devTemp, minTemp, maxTemp);

        assertEquals("Average temperature should match", avgTemp, stats.getAvgTemp(), 0.001);
        assertEquals("Deviation temperature should match", devTemp, stats.getDevTemp(), 0.001);
        assertEquals("Minimum temperature should match", minTemp, stats.getMinTemp(), 0.001);
        assertEquals("Maximum temperature should match", maxTemp, stats.getMaxTemp(), 0.001);
    }

    @Test
    public void testNegativeTemperatureValues() {
        double avgTemp = -10.0;
        double devTemp = 2.0;
        double minTemp = -15.0;
        double maxTemp = -5.0;

        TempSummaryStatistics stats = new TempSummaryStatistics(avgTemp, devTemp, minTemp, maxTemp);

        assertEquals("Average temperature should match", avgTemp, stats.getAvgTemp(), 0.001);
        assertEquals("Deviation temperature should match", devTemp, stats.getDevTemp(), 0.001);
        assertEquals("Minimum temperature should match", minTemp, stats.getMinTemp(), 0.001);
        assertEquals("Maximum temperature should match", maxTemp, stats.getMaxTemp(), 0.001);
    }

    @Test
    public void testEqualMinMaxTemperatures() {
        double avgTemp = 15.0;
        double devTemp = 0.0;
        double minTemp = 10.0;
        double maxTemp = 10.0;

        TempSummaryStatistics stats = new TempSummaryStatistics(avgTemp, devTemp, minTemp, maxTemp);

        assertEquals("Average temperature should match", avgTemp, stats.getAvgTemp(), 0.001);
        assertEquals("Deviation temperature should match", devTemp, stats.getDevTemp(), 0.001);
        assertEquals("Minimum temperature should match", minTemp, stats.getMinTemp(), 0.001);
        assertEquals("Maximum temperature should match", maxTemp, stats.getMaxTemp(), 0.001);
    }
}
