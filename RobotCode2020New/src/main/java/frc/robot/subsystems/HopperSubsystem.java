//-------- IMPORTS --------\\

package frc.robot.subsystems;

import frc.robot.Constants;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

//-------- SUBSYSTEM CLASS --------\\

/**
 * <h3>HopperSubsystem</h3>
 * 
 * This class controls the hopper
 */
public class HopperSubsystem extends SubsystemBase {

    // -------- CONSTANTS --------\\

    /**
     * This is the logger that will be used to output information to the user
     */
    private static final Logger logger = Logger.getLogger(HopperSubsystem.class.getName());

    // -------- DECLARATIONS --------\\

    /**
     * This VictorSPX is the motor that controls the hopper
     */
    private VictorSPX hopperMotor;

    // -------- CONSTRUCTOR --------\\

    /**
     * This constructor initializes the {@link #hopperMotor} to the correct motor
     * <p>It also sets the motor to be in inverted mode</p>
     */
    public HopperSubsystem() {
        hopperMotor = new VictorSPX(Constants.HOPPER_ID);
        hopperMotor.setInverted(true);
    }

    // -------- METHODS --------\\

    /**
     * <h3>setSpeed</h3>
     * 
     * This method setst the speed of the hopper
     * 
     * @param the speed that the motor will run at
     */
    public void setSpeed(double speed) {
        // Logs the setSpeed method as INFO returning, "setSpeed()"
        logger.entering(HopperSubsystem.class.getName(), "setSpeed()");

        hopperMotor.set(ControlMode.PercentOutput, speed);

        logger.log(Constants.LOG_LEVEL_INFO, "Set shooter speed to " + hopperMotor.getMotorOutputPercent());
        logger.exiting(HopperSubsystem.class.getName(), "setSpeed()");
    }

    /**
     * <h3>getSpeed</h3>
     * 
     * This method will return the current speed of the motor
     * 
     * @return the current speed of the motor
     */
    public double getSpeed() {
        // Logs the getSpeed method as INFO returning, "getSpeed()"
        logger.entering(HopperSubsystem.class.getName(), "getSpeed()");
        logger.log(Constants.LOG_LEVEL_INFO, "Get shooter speed to " + hopperMotor.getMotorOutputPercent());
        logger.exiting(HopperSubsystem.class.getName(), "getSpeed()");
        return hopperMotor.getMotorOutputPercent();
    }
} // end of class HopperSubsystem