package frc.robot.subsystems;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TowerSubsystem extends SubsystemBase {
    // VictorSPX is a motor controller that makes the conveor belt  
    //Take's the power cell up to the shooter
    public VictorSPX TowerMotor;
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private double speed;

    public TowerSubsystem() {
        TowerMotor = new VictorSPX(4);
        logger.setLevel(Level.FINE);

        //sets the speed to the tower motor 
    
    public void setSpeed(double speed){
        logger.entering(this.getClass().getName(), "setSpeed");
        logger.log(Level.INFO, "motorSpeed: "+ speed );
        this.speed = speed;
        TowerMotor.set(ControlMode.PercentOutput, speed);
        logger.exiting (this.getClass().getName(), "setSpeed");
    }
    // returns the speed from the tower motor
    public double getSpeed(){
    
        logger.entering(this.getClass().getName(),"getSpeed");
        logger.log(Level.INFO, "motorSpeed: " + speed);
        logger.exiting (this.getClass().getName(),"getSpeed");
        return this.speed;
    }
} //end of class 