package frc.robot.subsystems;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

 
public class HopperSubsystem extends SubsystemBase {
    // VictorSPX is a motor controller that rotates the hopper, containing the power cells. Works like a nerf hellraiser loader  
    public VictorSPX HopperMotor;
    
    public double speed;

    /**
    * The default constructor
    */
    public HopperSubsystem() {
        HopperMotor = new VictorSPX(Constants.HOPPER_MOTOR_PORT);
    }

    //sets the speed to the Hoppermotor
    public void setSpeed(double speed){
        this.speed = speed;
        HopperMotor.set(ControlMode.PercentOutput, speed);
    }

    // returns the speed
    public double getSpeed(){
        return this.speed;
    }
}