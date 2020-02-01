package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;

public class Compresser extends SubsystemBase {
    // this is the port number for the compressor
    private final int COMPRESSOR_PORT = 0;
    //values for turning on and off the compressor
    private final boolean COMPRESSOR_ON = true;
    private final boolean COMPRESSOR_OFF = false;

    //creates a objest of the compressor class
    private Compressor compressorUtility;

    //this is the contstructer for the compressor
    public Compresser() {
        setControllers();
    }
    //this sends the value to the compressor object
    public void setControllers() {
        setControllers(
           // new Compressor(COMPRESSOR_PORT)
           null
        );
    }
    //this sets the compressor constructer to the compressor object
    public void setControllers(Compressor compressor) {
       // compressorUtility = compressor;
    }
    //turns compresser eather on and off
    public void turnCompressorOn(){
      //  compressorUtility.setClosedLoopControl(COMPRESSOR_ON);
    }
    public void turnCompressorOff(){
       // compressorUtility.setClosedLoopControl(COMPRESSOR_OFF);
    }
}
