/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * FIFOStack
 */
public class SmootherStack {
    private List<Double> values = new ArrayList<>();
    private int length;
    private double sumValues = 0;

    public SmootherStack(int numOfValues) {
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