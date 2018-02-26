package com.github.basp1.extralib;

public interface AccumulatedError {
    double getAverage();

    double getMax();

    void add(double expected, double actual);
}