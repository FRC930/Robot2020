/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import com.revrobotics.*;
//import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controlling drivetrain during driver control
 */
public class Drive {

    //-------- CONSTANTS --------\\

    //-------- DECLARATIONS --------\\  
    private CANSparkMax left1;
    private CANSparkMax left2;
    private CANSparkMax left3;

    private CANSparkMax right1;
    private CANSparkMax right2;
    private CANSparkMax right3;
    

    //Drive instance
    private static Drive lastInstance = null;

    //-------- CONSTRUCTOR --------\\

    //Please write your intializations/instantiations in here
    private Drive() {
        
    }   //end of constructor Drive() 

    public static Drive getInstance() {    //NOTE: Keep this method named "getInstance" 
        if (lastInstance == null) {
            lastInstance = new Drive();
        }
        return lastInstance;
    } 

    public void setMotorControllers() {
        setMotorControllers(new CANSparkMax(1, MotorType.kBrushless),
                new CANSparkMax(2, MotorType.kBrushless),
                new CANSparkMax(3, MotorType.kBrushless),
                new CANSparkMax(4, MotorType.kBrushless),
                new CANSparkMax(5, MotorType.kBrushless),
                new CANSparkMax(6, MotorType.kBrushless));
    }

    public void setMotorControllers(CANSparkMax Left1, CANSparkMax Left2, CANSparkMax Left3, CANSparkMax Right1,
            CANSparkMax Right2, CANSparkMax Right3) {
        // Gives each Spark Max their proper values
        left1 = Left1;
        left2 = Left2;
        left3 = Left3;

        right1 = Right1;
        right2 = Right2;
        right3 = Right3;
        // Mirror primary motor controllers on each side
        left2.follow(left1);
        left3.follow(left1);

        right2.follow(right1);
        right3.follow(right1);
    }

    //-------- METHODS --------\\

    public void run(double stickX, double stickY) {
        //TODO: Arcade drive 
        // Joystick deadband
        if (Math.abs(stickX) < 0.001) {
            stickX = 0;
        }
        if (Math.abs(stickY) < 0.001) {
            stickY = 0;
        }
        // Cubing values to create smoother function
        stickX = -Math.pow(stickX, 3);
        stickY = Math.pow(stickY, 3);
        stickX *= 0.73;
        // Sets for use in motor controllers as Arcade values
        runAt((stickY + stickX), -(stickY - stickX));
    }

    // Given Arcade value arguments and sends to motor controllers
    public void runAt(double leftSpeed, double rightSpeed) {
        left1.set(leftSpeed);
        right1.set(rightSpeed);
    }

    // Returns left speed
    public double getLeftSpeed() {
        return left1.get();
    }

    // Returns right speed
    public double getRightSpeed() {
        return right1.get();
    }

} // end of method run()

   /// end of class Drive 