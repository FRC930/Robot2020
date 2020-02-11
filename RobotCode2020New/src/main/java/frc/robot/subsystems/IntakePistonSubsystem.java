/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;

import java.util.logging.Level;
import java.util.logging.Logger;

//-------- SUBSYSTEM CLASS --------\\

public class IntakePistonSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    private final int LEFT_PISTON_ID = 0;
    private final int RIGHT_PISTON_ID = 1;

   

    //-------- DECLARATIONS --------\\

    private final Logger logger = Logger.getLogger (IntakePistonSubsystem.class.getName());

    private Solenoid intakePistonRight;
    private Solenoid intakePistonLeft;
   
    //-------- CONSTRUCTOR --------\\

    public IntakePistonSubsystem(){
        intakePistonRight = new Solenoid(RIGHT_PISTON_ID);
        intakePistonLeft = new Solenoid(LEFT_PISTON_ID);
    }

    //-------- METHODS --------\\
    
    public void setIntakePiston(boolean state) {
        intakePistonLeft.set(state);
        intakePistonRight.set(state);
        logger.log(Level.FINE,"setIntakePiston");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
} // end of class IntakePistonSubsystem
