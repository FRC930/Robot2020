//-------- IMPORTS --------\\

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;

import java.util.logging.Logger;

import frc.robot.Constants;

//-------- SUBSYSTEM CLASS --------\\

/**
 * <h3>CompressorSubsystem</h3>
 * 
 * This class controls the compressor
 */
public class CompresserSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    /**
     * Boolean constant for compressor on
     */
    private final boolean COMPRESSOR_ON = true;
    /**
     * Boolean constant for compressor off
     */
    private final boolean COMPRESSOR_OFF = false;

    /**
     * The logger that we will use to show information to the user
     */
    private static final Logger logger = Logger.getLogger(CompresserSubsystem.class.getName());

    //-------- DECLARATIONS --------\\

    /**
     * This compressor is used for moving the intake and turret angle.
     */
    private Compressor compressorUtility;

    //-------- CONSTRUCTOR --------\\

    /**
     * This is the default command that sets the {@link #compressorUtility compressor} to the correct hardware
     */
    public CompresserSubsystem() {
        compressorUtility = new Compressor(Constants.COMPRESSOR_PORT);  
    }

    //-------- METHODS --------\\
    
    /**
     * <h3>turnCompressorOn</h3>
     * This method uses the constant {@link #COMPRESSOR_ON} to turn on the compressor
     */
    public void turnCompressorOn() {
        compressorUtility.setClosedLoopControl(COMPRESSOR_ON);
        logger.log(Constants.LOG_LEVEL_FINE, "Compresser on");
    }

    /**
     * <h3>turnCompressorOff</h3>
     * This method uses the constant {@link #COMPRESSOR_OFF} to turn off the compressor
     */
    public void turnCompressorOff(){
        compressorUtility.setClosedLoopControl(COMPRESSOR_OFF);
        logger.log(Constants.LOG_LEVEL_FINE, "Compresser off");
    }

} // end of class CompresserSubsystem
