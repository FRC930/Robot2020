package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.*;


/*

4 775 Motors on the shooter.
1 775 Motor on the turret.

1 - 2 Solenoids
 > 1 close range angle and 1 far range angle. Determined by the limelight.

*/

public class ShooterSubsystem extends SubsystemBase {

    // static flag variable
    private static ShooterSubsystem instance = null;

    // motor controllers for the 775 motors on the shooter
    private final VictorSPX motorLead;
    private final VictorSPX motor2;
    private final VictorSPX motor3;
    private final VictorSPX motor4;

    // solenoid dedicated to moving the turret up and down to have a close and far range
    private final Solenoid solenoid1;

    public ShooterSubsystem() {
        motorLead = new VictorSPX(3);
        motor2 = new VictorSPX(12);
        motor3 = new VictorSPX(14);
        motor4 = new VictorSPX(15);

        motor2.follow(motorLead);
        motor3.follow(motorLead);
        motor4.follow(motorLead);

        solenoid1 = new Solenoid(0);

    }

    public void setSpeed(double speed) {
        motorLead.set(ControlMode.PercentOutput, speed);
    }
    public void stop() {
        setSpeed(0.0);
    }
    public void angleChange(boolean solenoidStatus){
        solenoid1.set(true);
    }

    @Override
    public void periodic(){

    }
    

}

// written by ya bois josh and ed