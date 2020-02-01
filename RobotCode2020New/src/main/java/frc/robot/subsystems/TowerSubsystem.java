package frc.robot.subsystems;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

 
public class TowerSubsystem extends SubsystemBase {
    // VictorSPX is a motor controller that makes the conveor belt  
    //take the power cell up to the shooter
    public VictorSPX TowerMotor;
    private Logger logger = Logger.getLogger(getClass().getName());
    private double speed;
    
    /**
     * The default constructor
     */
    public TowerSubsystem() {
        TowerMotor = new VictorSPX(3);
    }

    //sets the speed to the tower motor
    public void setSpeed(double speed){
        logger.log(Level.INFO, "motorSpeed: "+ speed );
        this.speed = speed;
        TowerMotor.set(ControlMode.PercentOutput, speed);
    }

    // returns the speed
    public double getSpeed(){
        return this.speed;
    }
} //end of class 
    
