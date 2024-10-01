package ua.edu.ucu.apps.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final double ABSOLUTE_ZERO = -273.0;
    private static final int MAX_LINE_LENGTH = 80;
    private double[] temperatures;
    private int size;

    public TemperatureSeriesAnalysis() {
        temperatures = new double[0];
        size = 0;
    }

    public TemperatureSeriesAnalysis(double[] temps) {
    for (double temp : temps) {
        if (temp < ABSOLUTE_ZERO) {
            throw new InputMismatchException(
                "Temperature below absolute zero detected");
        }
    }
    temperatures = temps.clone();
    size = temps.length;
    }

    public double average() {
        if (size == 0) {
            throw new IllegalArgumentException(
                "Temperature series is empty");
        }
        double sum = 0;
        for (double temp : temperatures) {
            sum += temp;
        }
        return sum / size;
    }

    public double deviation() {
        if (size == 0) {
            throw new 
            IllegalArgumentException(
                "Temperature series is empty");
        }
        double avg = average();
        double sum = 0;
        for (double temp : temperatures) {
            sum += (temp - avg) * (temp - avg);
        }
        return Math.sqrt(sum / size);
    }

    public double min() {
        if (size == 0) {
            throw new IllegalArgumentException(
                "Temperature series is empty");
        }
        double minTemp = temperatures[0];
        for (double temp : temperatures) {
            if (temp < minTemp) {
                minTemp = temp;
            }
        }
        return minTemp;
    }

    public double max() {
        if (size == 0) {
            throw new IllegalArgumentException(
                "Temperature series is empty");
        }
        double maxTemp = temperatures[0];
        for (double temp : temperatures) {
            if (temp > maxTemp) {
                maxTemp = temp;
            }
        }
        return maxTemp;
    }

    public double findTempClosestToZero() {
        if (size == 0) {
            throw new IllegalArgumentException(
                "Temperature series is empty");
        }
        double closestTemp = temperatures[0];
        for (double temp : temperatures) {
            if (Math.abs(temp) < Math.abs(closestTemp) 
                || (Math.abs(temp) == Math.abs(closestTemp) 
                 && temp > closestTemp)) {
                closestTemp = temp;
            }
        }
        return closestTemp;
    }

    public double findTempClosestToValue(double tempValue) {
        if (size == 0) {
            throw new IllegalArgumentException(
                "Temperature series is empty");
        }
        double closestTemp = temperatures[0];
        for (double temp : temperatures) {
            if (Math.abs(temp - tempValue) 
                <  Math.abs(closestTemp - tempValue)) {
                closestTemp = temp;
            }
        }
        return closestTemp;
    }

    public double[] findTempsLessThan(double tempValue) {
        return filterTemps(tempValue, true);
    }

    public double[] findTempsGreaterThan(double tempValue) {
        return filterTemps(tempValue, false);
    }

    private double[] filterTemps(double tempValue, boolean lessThan) {
    int count = 0;
    for (double temp : temperatures) {
        if (lessThan) {
            if (temp < tempValue) {
                count++;
            }
        } else {
            if (temp >= tempValue) {
                count++;
            }
        }
    }

    double[] result = new double[count];
    int index = 0;
    for (double temp : temperatures) {
        if (lessThan) {
            if (temp < tempValue) {
                result[index++] = temp;
            }
        } else {
            if (temp >= tempValue) {
                result[index++] = temp;
            }
        }
    }
    return result;
}


    public void reset() {
        temperatures = new double[0];
        size = 0;
    }

    public double[] sortTemps() {
        double[] sortedTemps = temperatures.clone();
        java.util.Arrays.sort(sortedTemps);
        return sortedTemps;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (size == 0) {
            throw new IllegalArgumentException(
                "Temperature series is empty");
        }
        return new TempSummaryStatistics(
            average(), deviation(), min(), max());
    }

    public int addTemps(double ... temps) {
        for (double temp : temps) {
            if (temp < ABSOLUTE_ZERO) {
                throw new 
                InputMismatchException(
                    "Temperature below absolute zero detected");
            }
        }
        if (size + temps.length > temperatures.length) {
            double[] newTemps = new double[(size + temps.length) * 2];
            System.arraycopy(temperatures, 0, newTemps, 0, size);
            temperatures = newTemps;
        }
        System.arraycopy(temps, 0, temperatures, size, temps.length);
        size += temps.length;
        return size;
    }
}
