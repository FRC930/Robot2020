/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class JoystickSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    //-------- DECLARATIONS --------\\

    private Joystick driver;
    private Joystick coDriver;

    //-------- CONSTRUCTOR --------\\

    public JoystickSubsystem(Joystick driverJoystick, Joystick coDriverJoystick) {
        driver = driverJoystick;
        coDriver = coDriverJoystick;

    }

    //-------- METHODS --------\\

    //--Driver
    public double getDriverAxis(int axis) {
        return driver.getRawAxis(axis);
    }

    //--CoDriver
    public double getCoDriverAxis(int axis) {
        return coDriver.getRawAxis(axis);
    }

    @Override
    public void periodic() {
    // This method will be called once per scheduler run
    }
}
