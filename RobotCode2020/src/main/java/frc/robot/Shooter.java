package frc.robot;

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

public class Shooter {

    // static flag variable
    private static Shooter instance = null;

    // motor controllers for the 775 motors on the shooter
    private VictorSPX motor1;
    private  VictorSPX motor2;
    private  VictorSPX motor3;
    private  VictorSPX motor4;

    // solenoid dedicated to moving the turret up and down to have a close and far range
    private  Solenoid solenoid1;

    // button one for the turning on and off of the shooter
    private  int buttonA; // remove when inserted into the robot
    private boolean shooterStatus;
    // button two for the switch between position angle 1 (low score slot) and 2 (high score slot)
    private  int buttonB; // remove when inserted into the robot
    private boolean solenoidStatus;

    private  Joystick tempDriver; // remove when inserted into the robot
    private  int DRIVER_CONTROL_ID; // remove when inserted into the robot

    private double speed;

    private Shooter() {
        motor1 = new VictorSPX(3);
        motor2 = new VictorSPX(12);
        motor3 = new VictorSPX(14);
        motor4 = new VictorSPX(15);

        solenoid1 = new Solenoid(0);

        buttonA = 1; // remove when inserted into the robot
        shooterStatus = false;
        buttonB = 2; // remove when inserted into the robot
        solenoidStatus = false;

        DRIVER_CONTROL_ID = 0; // remove when inserted into the robot
        tempDriver = new Joystick(this.DRIVER_CONTROL_ID); // remove when inserted into the robot
    }

    //constructor THIS NEEDS TO BE FIXED, CAUSE APPARENTLY IT IS WRONG - MATT THE MENTOR
    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
            return instance;
        } else {
            return instance;
        }

    }

    public void init() {
        motor2.follow(motor1);
        motor3.follow(motor1);
        motor4.follow(motor1);

    }

    public void run() {
/*
        // if button a is pressed and the shooterStatus is false, set the status to true and turn on the shooter's motors.
        if (tempDriver.getRawButton(buttonA) && shooterStatus == false) {
            shooterStatus = true;
            motor1.set(ControlMode.PercentOutput, 1.0);

        }
        // if button a is pressed and the shooterStatus is true, set the status to true and turn off the shooter's motors
        if (tempDriver.getRawButton(buttonA) && shooterStatus == true) {
            shooterStatus = false;
            motor1.set(ControlMode.PercentOutput, 1.0);

        }

        // if button b is pressed and solenoidStatus is false, set the status to true and extend the solenoid.
        if (tempDriver.getRawButton(buttonB) && solenoidStatus == false) {
            solenoidStatus = true;
            solenoid1.set(true);

        }
        // if button b is pressed and solenoidStatus is true, set the status to false and retract the solenoid.
        if (tempDriver.getRawButton(buttonB) && solenoidStatus == true) {
            solenoidStatus = false;
            solenoid1.set(false);

        }
*/       
        speed = tempDriver.getRawAxis(1);
        motor1.set(ControlMode.PercentOutput, speed);
    }
}