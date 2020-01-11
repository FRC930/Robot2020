/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

/**
 * Controlling drivetrain during driver control
 */
public class Drive {

    //-------- CONSTANTS --------\\
    private final double DEADBAND = 0.001;
    private final double MOTOR_LIMITER = 0.73;
    private final int LEFT_ONE_PORT_NUM = 1;
    private final int LEFT_TWO_PORT_NUM = 2;
    private final int RIGHT_ONE_PORT_NUM = 3;
    private final int RIGHT_TWO_PORT_NUM = 4;

    //-------- DECLARATIONS --------\\  
    // TalonFX left motor controllers
    private WPI_TalonFX left1;
    private WPI_TalonFX left2;

    // TalonFX left motor controllers
    private WPI_TalonFX right1;
    private WPI_TalonFX right2;

    // Drive instance
    private static Drive lastInstance = null;

    //-------- CONSTRUCTOR --------\\
    
    // Please write your intializations/instantiations in here
    private Drive() {
        
    }   // End of Drive() constructor 

    public static Drive getInstance() {    //NOTE: Keep this method named "getInstance" 
        if (lastInstance == null) {
            lastInstance = new Drive();
        }
        return lastInstance;
    } 

    // Sets default port numbers to default ports for TalonFX's
    public void setMotorControllers() {
        setMotorControllers(new WPI_TalonFX(LEFT_ONE_PORT_NUM),
                new WPI_TalonFX(LEFT_TWO_PORT_NUM),
                new WPI_TalonFX(RIGHT_ONE_PORT_NUM),
                new WPI_TalonFX(RIGHT_TWO_PORT_NUM));
    }

    // Allows user to input their own TalonFX port numbers
    public void setMotorControllers(WPI_TalonFX Left1, WPI_TalonFX Left2, WPI_TalonFX Right1, WPI_TalonFX Right2){
        // Gives each TalonFX their proper values
        left1 = Left1;
        left2 = Left2;

        right1 = Right1;
        right2 = Right2;
        
        // Mirror primary motor controllers on each side
        left2.follow(left1);
        
        right2.follow(right1);
    }

    //-------- METHODS --------\\

    public void run(double stickX, double stickY) {
        // Joystick deadband
        if (Math.abs(stickX) < DEADBAND) {
            stickX = 0;
        }
        if (Math.abs(stickY) < DEADBAND) {
            stickY = 0;
        }
        
        // Cubing values to create smoother function
        stickX = -Math.pow(stickX, 3);
        stickY = Math.pow(stickY, 3);

        // Limits the motor's power to 73%
        stickX *= MOTOR_LIMITER;

        // Sets for use in motor controllers as Arcade values
        runAt((stickY + stickX), -(stickY - stickX));
    } // End of run method

    // Given Arcade value arguments and sends to motor controllers
    public void runAt(double leftSpeed, double rightSpeed) {
        left1.set(leftSpeed);
        right1.set(rightSpeed);
    }

    // Returns left speed
    public double getleftSpeed() {
        return left1.get();
    }

    // Returns right speed
    public double getrightSpeed() {
        return right1.get();
    }

} // End of Drive class