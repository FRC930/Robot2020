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

import frc.robot.Constants;

//-------- SUBSYSTEM CLASS --------\\

public class IntakePistonSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    private final Logger logger = Logger.getLogger(IntakePistonSubsystem.class.getName());

    //-------- DECLARATIONS --------\\

    private Solenoid intakePistonController;
   
    //-------- CONSTRUCTOR --------\\

    public IntakePistonSubsystem() {
        System.out.println("YYYYY");
        intakePistonController = new Solenoid(Constants.INTAKE_SOLENOID_ID);
    }

    //-------- METHODS --------\\
    
    public void setIntakePistonState(boolean state) {
        intakePistonController.set(state);
        
        logger.log(Level.FINE,"setIntakePistonState: " + state);
    }

    public boolean getIntakePistonState() {
        logger.log(Level.FINE,"getIntakePistonState: " + intakePistonController.get());

        return intakePistonController.get();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
    
} // end of class IntakePistonSubsystem
