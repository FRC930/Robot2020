package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * <h3>ShooterSubsystem</h3>
 * <p>
 * The shooter subsystem controls the hardware for the shooter
 * </p><p>
 * There are two constructors: one with hardware arguments used for mocking, and
 * one that does not receive hardware and sets the motor controllers to a default value
 * </p>
 */
public class ShooterSubsystem extends SubsystemBase {
    // motor controllers for the 775 motors on the shooter
    private final CANSparkMax motorLead;
    private final CANSparkMax motor2;
    private final CANSparkMax motor3;
    private final CANSparkMax motor4;

    // solenoid dedicated to moving the turret up and down to have a close and far
    // range
    private final Solenoid solenoid;

    public ShooterSubsystem() {
        motorLead = new CANSparkMax(3, MotorType.kBrushless);
        motor2 = new CANSparkMax(12, MotorType.kBrushless);
        motor3 = new CANSparkMax(14, MotorType.kBrushless);
        motor4 = new CANSparkMax(15, MotorType.kBrushless);

        motor2.follow(motorLead);
        motor3.follow(motorLead);
        motor4.follow(motorLead);

        solenoid = new Solenoid(0);

    }

    public void setSpeed(double speed) {
        motorLead.set(speed);
    }

    public void stop() {
        setSpeed(0.0);
    }

    public void angleChange(boolean solenoidStatus) {
        solenoid.set(true);
    }

    public boolean getAngle() {
        return solenoid.get();
    }

    @Override
    public void periodic() {

    }
} // End ShooterSubsystem

// written by ya bois josh and ed