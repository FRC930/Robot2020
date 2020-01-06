/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class TeleopHandler {
    
    //-------- CONSTANTS --------\\

    //--Button Mapping        //Refer to http://team358.org/files/programming/ControlSystem2009-/XBoxControlMapping.jpg
    private final int AXIS_LEFT_X = 0;
    private final int AXIS_LEFT_Y = 1;
    private final int AXIS_RIGHT_X = 4;
    private final int AXIS_RIGHT_Y = 5;
    private final int AXIS_LT = 2;
    private final int AXIS_RT = 3;

    //--Ports
    private final int CODRIVER_CONTROLLER_ID = 1;
    private final int DRIVER_CONTROLLER_ID = 0;

    //--Deadbands
    private final double TRIGGER_PRESSED_THRESHOLD = 0.4;

    //-------- DECLARATIONS --------\\

    //Teleop instance
    private static TeleopHandler lastInstance = null;

    //--Joysticks
    private static Joystick driver;     
    private static Joystick coDriver;

    //--Subsystems
    private Drive drive;

    //-------- CONSTRUCTOR --------\\

    //--To be initialized at start of teleop period
    private TeleopHandler() {
        //--Instantiate Joysticks
        driver = new Joystick(DRIVER_CONTROLLER_ID);
        coDriver = new Joystick(CODRIVER_CONTROLLER_ID);

        //--Get singleton subsystems
        drive = Drive.getInstance(); 

    } //end of constructor TeleopHandler()

    public static TeleopHandler getInstance() {    //NOTE: Keep this method named "getInstance" 
        if (lastInstance == null) {
            lastInstance = new TeleopHandler();
        }
        return lastInstance;
    } 

    //-------- METHODS --------\\

    //--To be run during teleop periodic (remember, this runs on intervals (around every 10 ms))
    public void run() {
      
        //--------DRIVETRAIN
        drive.run(driver.getRawAxis(AXIS_RIGHT_X), driver.getRawAxis(AXIS_LEFT_Y));    
        //--------
    }   //end of method run()

    //--Checks to see if a trigger is pressed
    private boolean isTriggerPressed(double axisValue) {

        // If the axis is above the deadband, it return true. If else, return false
        return (axisValue >= TRIGGER_PRESSED_THRESHOLD);
    }


} // end of class TeleopHandler