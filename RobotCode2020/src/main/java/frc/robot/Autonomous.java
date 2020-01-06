/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class Autonomous {
    
    //-------- CONSTANTS --------\\


    //-------- DECLARATIONS --------\\

    //Autonomous instance
    private static Autonomous lastInstance = null;
    //--Subsystems
    private Drive drive;    //I'm assuming you'll need this Nathan

    //-------- CONSTRUCTOR --------\\

    //--To be initialized at start of autonomous period
    private Autonomous() {
        //--Instantiate singleton subsystems
        drive = Drive.getInstance(); 
        //TODO: Grab auton settings from Shuffleboard/SmartDashboard
        
    } //end of constructor Autonomous()

    public static Autonomous getInstance() {    //NOTE: Keep this method named "getInstance" 
        if (lastInstance == null) {
            lastInstance = new Autonomous();
        }
        return lastInstance;
    } 

    //-------- METHODS --------\\

    //--To be run during auton periodic (remember, this runs on intervals (around every 10 ms))
    public void run() {

    } //end of method run()

} // end of class Autonomous