//-------- IMPORTS --------\\

package frc.robot.subsystems;

import frc.robot.Constants;

import java.util.logging.Logger;

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

    // -------- DECLARATIONS --------\\

    /**
     * ClimberArmMotor is a motor controller that makes the arm go up and down for
     * when we climb for endgame
     */
    private VictorSPX climberArmMotor;

    /**
     * <h3>ClimberArmSubsystem</h3> This default constructor initializes the
     * {@link #climberArmMotor motor controller} to the right motor controller.
     */
    public ClimberArmSubsystem() {
        climberArmMotor = new VictorSPX(Constants.CLIMBER_ARM_ID);
    }

    /**
     * <h3>setSpeed</h3>
     * 
     * This method sets the speed of the climber arm motor.
     * 
     * @param speed the speed to run the arm at
     */
    public void setSpeed(double speed) {
        logger.entering(this.getClass().getName(), "Extend");
        logger.log(Constants.LOG_LEVEL_INFO, "motorSpeed: " + speed);

        climberArmMotor.set(ControlMode.PercentOutput, speed);

        logger.exiting(this.getClass().getName(), "Extend");
    }

    /**
     * <h3>getSpeed</h3>
     * 
     * This method gets the current speed of the motor.
     * 
     * @return the arm speed
     */
    public double getSpeed() {
        logger.entering(this.getClass().getName(), "getSpeed");
        logger.log(Constants.LOG_LEVEL_INFO, "motorSpeed: " + climberArmMotor.getMotorOutputPercent());
        logger.exiting(this.getClass().getName(), "getSpeed");

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
}// End of ClimberArm Subsystem