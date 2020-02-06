package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;

import java.util.logging.Level;
import java.util.logging.Logger;



public class CompresserSubsystem extends SubsystemBase {
        // this is the port number for the compressor
    private final int COMPRESSOR_PORT = 0;
        //values for turning on and off the compressor
    private final boolean COMPRESSOR_ON = true;
    private final boolean COMPRESSOR_OFF = false;

    private final Logger logger = Logger.getLogger(CompresserSubsystem.class.getName());

    //creates a objest of the compressor class
    private Compressor compressorUtility;

    //this is the contstructer for the compressor
    public CompresserSubsystem() {
        //compressorUtility = new Compressor(COMPRESSOR_PORT);
      
    }
    //turns compresser eather on and off
    public void turnCompressorOn(){
      //  compressorUtility.setClosedLoopControl(COMPRESSOR_ON);
      logger.log(Level.FINE,"turning compresser on");
    }
    public void turnCompressorOff(){
       // compressorUtility.setClosedLoopControl(COMPRESSOR_OFF);
       logger.log(Level.FINE,"turning compresser off");
    }
}// END OF CLASS
