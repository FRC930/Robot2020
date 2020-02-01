/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.Solenoid;

import frc.robot.Constants;


public class IntakePistons extends SubsystemBase {

    //-------- CONSTANTS --------\\

    private final int LEFT_PISTON_ID = 0;
    private final int RIGHT_PISTON_ID = 1;
    
    //-------- DECLARATIONS --------\\

    //private Solenoid intakePistonRight;
    //private Solenoid intakePistonLeft;
   

    //-------- CONSTRUCTOR --------\\

    public IntakePistons() {
        setControllers();
    }

    //-------- METHODS --------\\

    public void setControllers() {
        setControllers(
            //new Solenoid(LEFT_PISTON_ID),
           //new Solenoid(RIGHT_PISTON_ID)
           null,null
        );
    }

    public void setControllers(Solenoid pistonControllerRight, Solenoid pistonContollerLeft) {
     //  intakePistonRight = pistonControllerRight;
       //intakePistonLeft = pistonContollerLeft;
       
    }

   
    public void setIntakePiston(boolean state) {
        //TODO: Fix me
      //  intakePistonLeft.set(state);
        //intakePistonRight.set(state);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
