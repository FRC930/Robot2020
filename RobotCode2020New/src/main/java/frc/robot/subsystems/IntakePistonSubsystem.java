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

import java.util.logging.Level;
import java.util.logging.Logger;


public class IntakePistonSubsystem extends SubsystemBase {

private final Logger logger = Logger.getLogger (IntakePistonSubsystem.class.getName());

    //-------- CONSTANTS --------\\
    public static final boolean INTAKE_PISTONS_UP = false; 
    public static final boolean INTAKE_PISTONS_DOWN = !INTAKE_PISTONS_UP; 

    private final int LEFT_PISTON_ID = 0;
    private final int RIGHT_PISTON_ID = 1;
    
    //-------- DECLARATIONS --------\\

    //private Solenoid intakePistonRight;
    //private Solenoid intakePistonLeft;
   

    //-------- CONSTRUCTOR --------\\

    public IntakePistonSubsystem(){
        //intakePistonRight = new Solenoid(RIGHT_PISTON_ID);
       //intakePistonLeft = new Solenoid(LEFT_PISTON_ID);
    }

    //-------- METHODS --------\\
    

   
    public void setIntakePiston(boolean state) {
        //TODO: Fix me
      //  intakePistonLeft.set(state);
        //intakePistonRight.set(state);
        logger.log(Level.FINE,"setIntakePiston");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}//END OF CLASS
