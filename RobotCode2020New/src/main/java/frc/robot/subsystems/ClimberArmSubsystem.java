//-------- IMPORTS --------\\

package frc.robot.subsystems;

import frc.robot.Constants;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//--------SUBSYSTEM CLASS--------\\

/**
 * <h3>ClimberArmSubsystem</h3>
 * <p>
 * This class abstracts the hardware used in the climber arm.
 * </p>
 */
public class ClimberArmSubsystem extends SubsystemBase {

    // --------CONSTANTS--------\\
    
    /**
     * The logger we will us to output information to the userss
     */
    private static final Logger logger = Logger.getLogger(ClimberArmSubsystem.class.getName());

    //-------- DECLARATIONS --------\\

    private DutyCycleEncoder encoder;
    
    /**
     * ClimberArmMotor is a motor controller that makes the arm go up and down for
     * when we climb for endgame
     */
    private VictorSPX climberArmMotor;

    // -------- CONSTRUCTOR --------\\

    /**
     * <h3>ClimberArmSubsystem</h3> This default constructor initializes the
     * {@link #climberArmMotor motor controller} to the right motor controller.
     */
    public ClimberArmSubsystem() {
        climberArmMotor = new VictorSPX(Constants.CLIMBER_ARM_ID);
        this.encoder = new DutyCycleEncoder(Constants.CLIMBER_ENCODER_PORT_ID);
        this.encoder.reset();
    }

    // -------- METHODS --------\\

    /**
     * <h3>setSpeed</h3>
     * 
     * This method sets the speed of the climber arm motor.
     * 
     * @param speed the speed to run the arm at
     */
    public void setSpeed(double speed) {
        logger.entering(ClimberArmSubsystem.class.getName(), "Extend");
        logger.log(Constants.LOG_LEVEL_INFO, "motorSpeed: " + speed);

        climberArmMotor.set(ControlMode.PercentOutput, speed);

        logger.exiting(ClimberArmSubsystem.class.getName(), "Extend");
    }

    /**
     * <h3>getSpeed</h3>
     * 
     * This method gets the current speed of the motor.
     * 
     * @return the arm speed
     */
    public double getSpeed() {
        logger.entering(ClimberArmSubsystem.class.getName(), "getSpeed");
        logger.log(Constants.LOG_LEVEL_INFO, "motorSpeed: " + climberArmMotor.getMotorOutputPercent());
        logger.exiting(ClimberArmSubsystem.class.getName(), "getSpeed");

        return climberArmMotor.getMotorOutputPercent();
    }

    /**
     * <h3>stopMotor</h3>
     * 
     * This method calls {@link #setSpeed(double) setSpeed} with an argumnet of 0.0,
     * effectively stopping the motor
     */
    public void stopMotor() {
        this.setSpeed(0.0);
    }

    public double getRawEncoderPosition(){
        return this.encoder.get();
    }
}  // End of ClimberArm Subsystem
