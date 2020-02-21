/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;

import java.util.logging.Logger;

import frc.robot.Constants;

//-------- SUBSYSTEM CLASS --------\\

public class CompresserSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    //values for turning on and off the compressor
    private final boolean COMPRESSOR_ON = true;
    private final boolean COMPRESSOR_OFF = false;

    private final Logger logger = Logger.getLogger(CompresserSubsystem.class.getName());

    //-------- DECLARATIONS --------\\

    //creates a objest of the compressor class
    private Compressor compressorUtility;

    //-------- CONSTRUCTOR --------\\

    //this is the contstructer for the compressor
    public CompresserSubsystem() {
        compressorUtility = new Compressor(Constants.COMPRESSOR_PORT);  
    }

    //-------- METHODS --------\\
    
    //turns compresser eather on and off
    public void turnCompressorOn() {
        compressorUtility.setClosedLoopControl(COMPRESSOR_ON);
        logger.log(Constants.LOG_LEVEL_FINE, "Compresser on");
    }

    public void turnCompressorOff(){
        compressorUtility.setClosedLoopControl(COMPRESSOR_OFF);
        logger.log(Constants.LOG_LEVEL_FINE, "Compresser off");
    }

} // end of class CompresserSubsystem
