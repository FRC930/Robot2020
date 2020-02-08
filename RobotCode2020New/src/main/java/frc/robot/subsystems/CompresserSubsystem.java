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

import java.util.logging.Level;
import java.util.logging.Logger;

//-------- SUBSYSTEM CLASS --------\\

public class CompresserSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    // this is the port number for the compressor
    private final int COMPRESSOR_PORT = 0;
        //values for turning on and off the compressor
    private final boolean COMPRESSOR_ON = true;
    private final boolean COMPRESSOR_OFF = false;

    //-------- DECLARATIONS --------\\

    private final Logger logger = Logger.getLogger(CompresserSubsystem.class.getName());

    //creates a objest of the compressor class
    private Compressor compressorUtility;

    //-------- CONSTRUCTOR --------\\

    //this is the contstructer for the compressor
    public CompresserSubsystem() {
        //TODO: Uncomment this
        //compressorUtility = new Compressor(COMPRESSOR_PORT);  
    }

    //-------- METHODS --------\\
    
    //turns compresser eather on and off
    public void turnCompressorOn() {
        //TODO: Uncomment this
        //compressorUtility.setClosedLoopControl(COMPRESSOR_ON);
        logger.log(Level.FINE,"turning compresser on");
    }

    public void turnCompressorOff(){
        //TODO: Uncomment this
        // compressorUtility.setClosedLoopControl(COMPRESSOR_OFF);
        logger.log(Level.FINE,"turning compresser off");
    }

} // end of class CompresserSubsystem
