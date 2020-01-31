package main.java.frc.robot.utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * FIFOStack
 */
public class FIFOStack {
    private List<Double> values = new ArrayList<>();
    private int length;
    private double sumValues = 0;

    public FIFOStack(int numOfValues) {
        this.length = numOfValues;
    }

    public void insert(double newValue) {
        newValue /= this.length;
        if (values.size() < this.length) {
            this.values.add(newValue);
        } else {
            this.values.remove(0);
            this.values.add(newValue);
        }
        this.sum();
    }

    private void sum() {
        double total = 0;
        for (double current : this.values) {
            total += current;
        }
        this.sumValues = total;
    }

    public double getSum() {
        return this.sumValues;
    }
}