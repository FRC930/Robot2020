/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Constants;


public class IntakeSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    private final int PISTON_FORWARD_CHANNEL = 0;
    private final int PISTON_REVERSE_CHANNEL = 7;
    private final int INTAKE_VICTORSPX_ID = 4;

    //-------- DECLARATIONS --------\\

    private DoubleSolenoid intakePistonController;
    private VictorSPX intakeMotorController; 

    //-------- CONSTRUCTOR --------\\

    public IntakeSubsystem() {
        setControllers();
    }

    //-------- METHODS --------\\

    public void setControllers() {
        setControllers(
            new DoubleSolenoid(PISTON_FORWARD_CHANNEL, PISTON_REVERSE_CHANNEL),
            new VictorSPX(INTAKE_VICTORSPX_ID)
        );
    }

    public void setControllers(DoubleSolenoid pistonController, VictorSPX motorController) {
        intakePistonController = pistonController;
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
        //(state) ? intakePistonController.set(Value.kForward) : intakePistonController.set(Value.kReverse);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
