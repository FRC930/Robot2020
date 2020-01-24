package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * <h2>TurretSubsystem
 * 
 * <p>
 * TurretSubsystem is a PID subsystem that controls the rotation of the turret
 */

 /**
  * Other details:
  * - The encoder is a "planetary encoder."
  */
public class TurretSubsystem extends SubsystemBase {
    // The current speed of the motor
    private double speed;
    // The motor controller that will control the turret
    private CANSparkMax mc;

    /**
     * The default constructor
     */
    public TurretSubsystem() {
        // TODO: Find out the device ID
        this(new CANSparkMax(0, MotorType.kBrushless));
    }

    /**
     * This constructor will allow the passing of a motor controller used for testing purposes
     */
    public TurretSubsystem(CANSparkMax turret) {
        this.speed = 0;
        this.mc = turret;
    }

    public void setSpeed(double motorSpeed) {
        this.speed = motorSpeed;
        this.mc.set(this.speed);
    }

    public double getSpeed() {
        return this.speed;
    }
}