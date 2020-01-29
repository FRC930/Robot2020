package frc.robot.subsystems;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * <h2>TurretSubsystem
 * 
 * <p>
 * TurretSubsystem is a PID subsystem that controls the rotation of the turret
 */

/**
 * Other details: - The encoder is a "planetary encoder."
 */
public class TurretSubsystem extends SubsystemBase {
    // The current speed of the motor
    private double speed;
    // The motor controller that will control the turret
    private TalonSRX mc;
    private Logger logger = Logger.getLogger(TurretSubsystem.class.getName());

    /**
     * The default constructor
     */
    public TurretSubsystem() {
        this(new TalonSRX(5));
    }

    /**
     * This constructor will allow the passing of a motor controller used for
     * testing purposes
     */
    public TurretSubsystem(TalonSRX turret) {
        this.speed = 0;
        this.mc = turret;
        this.mc.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
        this.mc.setSelectedSensorPosition(0);
        this.logger.log(Level.INFO, "Starting TurretSubsystem");
    }

    public void setSpeed(double motorSpeed) {
        this.speed = motorSpeed;
        // TODO: Figure out the position needed for 380° of rotation
        if (speed < 0) {
            if (this.mc.getSelectedSensorPosition() > 1500) {
                speed = 0;
            }
        } else if (speed > 0) {
            if (this.mc.getSelectedSensorPosition() < -1500) { 
                speed = 0;
            }
        }
        this.mc.set(ControlMode.PercentOutput, this.speed);
        this.logger.log(Level.INFO, "Set speed to " + this.speed);
    }

    public double getSpeed() {
        return this.speed;
    }

    public int getEncoderPosition() {
        return this.mc.getSelectedSensorPosition();
    }
}
