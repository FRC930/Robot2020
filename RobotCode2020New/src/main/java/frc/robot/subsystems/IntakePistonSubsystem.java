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

import java.util.logging.Logger;

import frc.robot.Constants;
//import frc.robot.utilities.ShuffleboardUtility;

//-------- SUBSYSTEM CLASS --------\\

public class IntakePistonSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    private static final Logger logger = Logger.getLogger(IntakePistonSubsystem.class.getName());

    //-------- DECLARATIONS --------\\

    private Solenoid intakePistonController;
    //private ShuffleboardUtility shuffleboardUtility;
   
    //-------- CONSTRUCTOR --------\\

    public IntakePistonSubsystem() {
        intakePistonController = new Solenoid(Constants.INTAKE_SOLENOID_ID);
        //shuffleboardUtility = ShuffleboardUtility.getInstance();
    }

    //-------- METHODS --------\\
    
    public void setIntakePistonState(boolean state) {
        intakePistonController.set(state);
        
        logger.log(Constants.LOG_LEVEL_FINE, "setIntakePistonState: " + state);
    }

    public boolean getIntakePistonState() {
        logger.log(Constants.LOG_LEVEL_FINE, "getIntakePistonState: " + intakePistonController.get());

        return intakePistonController.get();
    }

    // @Override
    // public void periodic() {    // This method will be called once per scheduler run     
    //     //shuffleboardUtility.setIntakeIndicator(getIntakePistonState());
    // }
    
} // end of class IntakePistonSubsystem
