/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import com.revrobotics.*;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controlling drivetrain during driver control
 */
public class Drive {

    //-------- CONSTANTS --------\\

    //-------- DECLARATIONS --------\\

    //Drive instance
    private static Drive lastInstance = null;

    //-------- CONSTRUCTOR --------\\

    //Please write your intializations/instantiations in here
    private Drive() {
        
    }

    public static Drive getInstance() {    //NOTE: Keep this method named "getInstance" 
        if (lastInstance == null) {
            lastInstance = new Drive();
        }
        return lastInstance;
    }

    //-------- METHODS --------\\

    public void init() {

    }

    public void run() {

    }
}