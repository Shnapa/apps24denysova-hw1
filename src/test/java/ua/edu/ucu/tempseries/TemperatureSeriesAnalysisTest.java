package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import ua.edu.ucu.apps.tempseries.TemperatureSeriesAnalysis;


public class TemperatureSeriesAnalysisTest {

    @Test
    public void test() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAverageWithOneElementArray() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageOnEmptySeries() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis();
        series.average();
    }

    @Test
    public void testMin() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {3.0, -1.0, 5.0, 7.0});
        double expected = -1.0;
        assertEquals(expected, series.min(), 0.00001);
    }

    @Test
    public void testMinWithSingleValue() {
        double[] temperatureSeries = {25.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 25.0;

        double actualResult = seriesAnalysis.min();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMinWithAllPositiveValues() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {3.0, 5.0, 9.0});
        double expected = 3.0;
        assertEquals(expected, series.min(), 0.00001);
    }

    @Test
    public void testMinWithAllNegativeValues() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {-3.0, -5.0, -1.0});
        double expected = -5.0;
        assertEquals(expected, series.min(), 0.00001);
    }

    @Test
    public void testFindTempClosestToZeroWithEqualDistances() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {-0.1, 0.1, 0.2, -0.2});
        double expected = 0.1;
        assertEquals(expected, series.findTempClosestToZero(), 0.00001);
    }

    @Test
    public void testFindTempClosestToZeroWithSingleValue() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {5.0});
        double expected = 5.0;
        assertEquals(expected, series.findTempClosestToZero(), 0.00001);
    }

    @Test
    public void testFindTempClosestToZeroWithMultipleValues() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {-2.0, 3.0, 1.5, -1.5});
        double expected = 1.5;
        assertEquals(expected, series.findTempClosestToZero(), 0.00001);
    }

    @Test
    public void testAddTemps() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {1.0, 2.0});
        int total = series.addTemps(3.0, 4.0, 5.0);
        assertEquals(5, total);
    }

    @Test
    public void testAddTempsWithNegativeValues() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {-1.0, -2.0});
        int total = series.addTemps(-3.0, -4.0);
        assertEquals(4, total);
    }

    @Test
    public void testFindTempsLessThan() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {1.0, 3.0, -2.0, 5.0});
        double[] expected = {1.0, -2.0};
        assertArrayEquals(expected, series.findTempsLessThan(3.0), 0.00001);
    }

    @Test
    public void testFindTempsLessThanWithNoValues() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {});
        double[] expected = {};
        assertArrayEquals(expected, series.findTempsLessThan(3.0), 0.00001);
    }

    @Test
    public void testFindTempsLessThanWithAllValuesGreater() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {4.0, 5.0, 6.0});
        double[] expected = {};
        assertArrayEquals(expected, series.findTempsLessThan(4.0), 0.00001);
    }

    @Test
    public void testDeviation() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {1.0, 2.0, 3.0});
        double expected = Math.sqrt(2.0 / 3.0);
        assertEquals(expected, series.deviation(), 0.00001);
    }

    @Test
    public void testDeviationWithSingleValue() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {5.0});
        assertEquals(0.0, series.deviation(), 0.00001); // Deviation should be 0 for a single value
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {});
        series.deviation();
    }

    @Test
    public void testMaxWithMultipleValues() {
        double[] temperatureSeries = {10.0, 20.0, 30.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 30.0;

        double actualResult = seriesAnalysis.max();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMaxWithSingleValue() {
        double[] temperatureSeries = {25.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 25.0;

        double actualResult = seriesAnalysis.max();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMaxWithAllNegativeValues() {
        double[] temperatureSeries = {-5.0, -1.0, -10.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        double actualResult = seriesAnalysis.max();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAverageWithAllNegativeValues() {
        double[] temperatureSeries = {-5.0, -10.0, -15.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -10.0;
        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test
    public void testFindTempsLessThanWithAllValuesLess() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {1.0, 2.0, 3.0});
        double[] expected = {};
        assertArrayEquals(expected, series.findTempsLessThan(0.0), 0.00001);
    }

    @Test
    public void testDeviationWithMixedValues() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {1.0, -1.0, 2.0, -2.0});
        double expected = Math.sqrt(2.5);
        assertEquals(expected, series.deviation(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZeroWithNoValues() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {});
        series.findTempClosestToZero();
    }

    @Test
    public void testMinWithAllSameValues() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {10.0, 10.0, 10.0});
        double expected = 10.0;
        assertEquals(expected, series.min(), 0.00001);
    }

    @Test
    public void testMaxWithAllSameValues() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {10.0, 10.0, 10.0});
        double expected = 10.0;
        assertEquals(expected, series.max(), 0.00001);
    }

    @Test
    public void testFindTempsLessThanWithNegativeThreshold() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[] {1.0, -1.0, 2.0, -2.0});
        double[] expected = {-1.0, -2.0};
        assertArrayEquals(expected, series.findTempsLessThan(0.0), 0.00001);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempBelowAbsoluteZero() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis();
        series.addTemps(-300);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testDeviationOnEmptySeries() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis();
        series.deviation();
    }

    @Test
    public void testSingleValueStatistics() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[]{25});
        assertEquals(25, series.average(), 0.001);
        assertEquals(0, series.deviation(), 0.001);
        assertEquals(25, series.min(), 0.001);
        assertEquals(25, series.max(), 0.001);
    }

    @Test
    public void testDuplicateValues() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis(new double[]{20, 20, 20});
        assertEquals(20, series.average(), 0.001);
        assertEquals(0, series.deviation(), 0.001);
        assertEquals(20, series.min(), 0.001);
        assertEquals(20, series.max(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinOnEmptySeries() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis();
        series.min();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxOnEmptySeries() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis();
        series.max();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZeroOnEmptySeries() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis();
        series.findTempClosestToZero();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValueOnEmptySeries() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis();
        series.findTempClosestToValue(25);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsOnEmptySeries() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis();
        series.summaryStatistics();
    }
}
