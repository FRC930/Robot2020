package frc.robot.subsystems;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

 
public class HopperSubsystem extends SubsystemBase {
    // VictorSPX is a motor controller that rotates the hopper, containing the power cells. Works like a nerf hellraiser loader  
    public VictorSPX HopperMotor;
    
    public double speed;

    private Logger logger;

    /**
    * The default constructor
    */
    public HopperSubsystem() {
        HopperMotor = new VictorSPX(Constants.HOPPER_MOTOR_PORT);

        logger = Logger.getLogger(HopperSubsystem.class.getName());
    }

    //sets the speed to the Hoppermotor
    public void setSpeed(double speed){
        //Logs the setSpeed method as INFO returning, "setSpeed()"
        logger.entering(getClass().getName(), "setSpeed()");

        this.speed = speed;
        HopperMotor.set(ControlMode.PercentOutput, speed);

        logger.log(Level.INFO, "Set shooter speed to " + speed);
        logger.exiting(getClass().getName(), "setSpeed()");
    }

    // returns the speed
    public double getSpeed(){
        //Logs the getSpeed method as INFO returning, "getSpeed()"
        logger.entering(getClass().getName(), "getSpeed()");
        logger.log(Level.INFO, "Get shooter speed to " + speed);
        logger.exiting(getClass().getName(), "getSpeed()");

        return this.speed;
    }
}