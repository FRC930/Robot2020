/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Constants;


public class IntakeSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    private final int LEFT_PISTON_ID = 0;
    private final int RIGHT_PISTON_ID = 1;
    private final int INTAKE_TALONSRX_ID = 6;

    //-------- DECLARATIONS --------\\

    private Solenoid intakePistonRight;
    private Solenoid intakePistonLeft;
    private TalonSRX intakeMotorController; 

    //-------- CONSTRUCTOR --------\\

    public IntakeSubsystem() {
        setControllers();
    }

    //-------- METHODS --------\\

    public void setControllers() {
        setControllers(
            new Solenoid(LEFT_PISTON_ID),
            new Solenoid(RIGHT_PISTON_ID),
            new TalonSRX(INTAKE_TALONSRX_ID)
        );
    }

    public void setControllers(Solenoid pistonControllerRight, Solenoid pistonContollerLeft, TalonSRX motorController) {
       intakePistonRight = pistonControllerRight;
       intakePistonLeft = pistonContollerLeft;
        intakeMotorController = motorController;
    }

    public void setMotorSpeed(double speed) {
        intakeMotorController.set(ControlMode.PercentOutput, speed);
    }

    public double getMotorSpeed() {
        //return intakeMotorController.get(); //TODO: Fix me
        return 0.0;
    }

    public void setIntakePiston(boolean state) {
        //TODO: Fix me
        intakePistonLeft.set(state);
        intakePistonRight.set(state);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
