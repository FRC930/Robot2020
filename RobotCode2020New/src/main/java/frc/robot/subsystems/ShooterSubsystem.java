package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;


/*

4 775 Motors on the shooter.
1 775 Motor on the turret.

1 - 2 Solenoids
 > 1 close range angle and 1 far range angle. Determined by the limelight.

*/

public class ShooterSubsystem extends SubsystemBase {
    // motor controllers for the 775 motors on the shooter
    private final CANSparkMax motorLead;
    private final CANSparkMax motor2;
    private final CANSparkMax motor3;
    private final CANSparkMax motor4;

    // solenoid dedicated to moving the turret up and down to have a close and far range
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

    public void angleChange(boolean solenoidStatus){
        solenoid.set(true);
    }

    public boolean getAngle() {
        return solenoid.get();
    }

    @Override
    public void periodic(){

    }
} // End ShooterSubsystem

// written by ya bois josh and ed