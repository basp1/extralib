package com.github.basp1.extralib;

public class MeanAbsoluteError implements AccumulatedError {
    private int count;
    private double sum;
    private double max;

    public MeanAbsoluteError() {
        this.count = 0;
        this.sum = 0;
        this.max = Double.NaN;
    }

    @Override
    public double getAverage() {
        return sum / count;
    }

    @Override
    public double getMax() {
        return max;
    }

    public int size() {
        return count;
    }

    @Override
    public void add(double expected, double actual) {
        double error = 100d * Math.abs((expected - actual) / expected);

        if (Double.isNaN(max) || error > max) {
            max = error;
        }

        count++;

        sum += error;
    }

    @Override
    public String toString() {
        return "MeanAbsoluteError { avg => " + String.format("%.3f%%", getAverage())
                + "; max => " + String.format("%.3f%%", getMax()) + " }";
    }
}
