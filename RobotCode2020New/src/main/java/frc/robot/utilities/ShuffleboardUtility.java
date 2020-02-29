/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.utilities;

import java.util.List;
import java.util.Map;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardComponent;
import edu.wpi.first.networktables.NetworkTableEntry;

//-------- CLASS --------\\

public class ShuffleboardUtility {	

	//-------- CONSTANTS --------\\

    //-------- DECLARATIONS --------\\
    
    private List<ShuffleboardComponent<?>> pidController;
    private double kP;
    private double kI;
    private double kD;
    private double kF;
    private double kSetpoint;
	private boolean intakeIndicator;
    private boolean shootIndicator;
    private boolean manualMode;
	// private double turretEncoder;
	private double distanceFromTarget;
	private String shotType;
    private String fmsColor;
    private String logger;
    private String fmsColorDebug;
    private double hopperSpeed;
    private double shooterRPM;
    private double pistonAngle;
    private double pistonRPM;
    private double turretSpeed;
    private double turretEncoderPosition;
    private HttpCamera limelightCamera;
    private double gyroYaw;
    private ShuffleboardTab testDebugTab;
    private ShuffleboardTab driverStationTab;
    private NetworkTableEntry intakingEntry;
    private NetworkTableEntry shootingEntry;
    private NetworkTableEntry manualModeEntry;
    private NetworkTableEntry distanceFromTargetEntry;
    private NetworkTableEntry shotTypeEntry;
    

    //-------- CONSTRUCTOR --------\\

    private ShuffleboardUtility() {
        // Gets the pid controller data
        pidController = testDebugTab.getComponents();
        
        // sets stating values -------------------------------------------
        intakeIndicator = false;
        shootIndicator = false;
        manualMode = false;
        // turretEncoder = 0.0;
        distanceFromTarget = 0.0;
        shotType = "";
        fmsColor = "";
        logger = "";
        fmsColorDebug = "";
        hopperSpeed = 0.0;
        shooterRPM = 0.0;
        pistonAngle = 0.0;
        pistonRPM = 0.0;
        turretSpeed = 0.0;
        turretEncoderPosition = 0.0;
        gyroYaw = 0.0;
        limelightCamera = new HttpCamera("limelight", "http://10.9.30.11:5801/stream.mjpg");
        kP = 0.0;
        kI = 0.0;
        kD = 0.0;
        kF = 0.0;
        kSetpoint = 0.0;
        //end of stating values ---------------------------------------------------------

        //gets all the tab data
        driverStationTab = Shuffleboard.getTab("Driver Station");
        testDebugTab = Shuffleboard.getTab("Testing & Debugging");

        // data to add to shuffle board
        intakingEntry = driverStationTab.add("Intaking?", intakeIndicator).getEntry();
        shootingEntry = driverStationTab.add("Shooting?", shootIndicator).getEntry();
        manualModeEntry = driverStationTab.add("Manual Mode?", manualMode).getEntry();
        distanceFromTargetEntry = driverStationTab.add("Distance from Target", distanceFromTarget).getEntry();
        shotTypeEntry = driverStationTab.add("Distance from Target", shotType).getEntry();
    }

    private static ShuffleboardUtility instance = null;

	// Singleton
    public static ShuffleboardUtility getInstance() {
        if (instance == null){
            instance = new ShuffleboardUtility();
        }
        return instance;
    }

    //------- Drive Tab -------\\

    // TODO: set methods to respective commands
	public void setIntakeIndicator(boolean IntakeIndicator){
        intakeIndicator = IntakeIndicator;
        //sends the state to shuffle board
        intakingEntry.setBoolean(intakeIndicator);
	}
	public void setShootIndicator(boolean ShootIndicator){
        shootIndicator = ShootIndicator;
        //sends the state to shuffle board
		shootingEntry.setBoolean(shootIndicator);
    }
    public void setManualMode(boolean ManualMode){
        manualMode = ManualMode;
        //sends the state to shuffle board
        manualModeEntry.setBoolean(shootIndicator);
    }
	public void setDistanceFromTarget(double DistanceFromTarget){
        distanceFromTarget = DistanceFromTarget;
        //sends the distance to shuffle board
        distanceFromTargetEntry.setNumber(distanceFromTarget);
    }
    // TODO: find method for shot types
	public void setShotType(String ShotType){
        shotType = ShotType;
        //sends the type to shuffle board
		shotTypeEntry.getString(shotType);
    }
    public void setLimelightFeed(HttpCamera LimelightCamera){
        limelightCamera = LimelightCamera;
        //sends the limelight feed
        driverStationTab.add("Limelight Feed", limelightCamera);
        testDebugTab.add("Limelight Feed", limelightCamera);
    }
    // public String getFMSColor(){
	// 	fmsColor = SmartDashboard.getString("FMS Color", "No Color Available");
	// 	return fmsColor;
    // }

	//----- Testing & Debugging -----\\

    public void setTurretSpeed(double turretSpeed){
        this.turretSpeed = turretSpeed;
        //sends the speed to shuffle board
        SmartDashboard.putNumber("Turret Speed", turretSpeed);
    }
    public void setHopperSpeed(double hopperSpeed){
        this.hopperSpeed = hopperSpeed;
        //sends the speed to shuffle board
        SmartDashboard.putNumber("Hopper Speed", hopperSpeed);
    }
    // travis and josh are fixing rn
    public void setPistonAngle(double pistonAngle){
        this.pistonAngle = pistonAngle;
        //sends the state to shuffle board
        SmartDashboard.putNumber("Piston Angle", pistonAngle);
    }
	// public void getLogger(String logger){
	// 	this.logger = logger;
	// 	SmartDashboard.putString("Logger Level", logger);
	// }
	public void setTurretEncoderPosition(double turretEncoderPosition){
        this.turretEncoderPosition = turretEncoderPosition;
        // sends the encoder pos to shuffle board
		SmartDashboard.putNumber("Turret Encoder Pos", turretEncoderPosition);
    }

    public void setGyroYaw(double gyroYaw){
        this.gyroYaw = gyroYaw;
        //sends the yaw to shuffle board
        SmartDashboard.putNumber("Gyro Yaw (LtoR Rotation)", gyroYaw);
    }
    // TODO: figure out how to get PID values into code
    // public String getShooterP(){
    //     //  return string : set to error string, changes to valid string if object is found.
    //     String rtn = "Nothing to Return...";
    //     if (pidController.size() > 0){
    //         for (int i = 0; i < pidController.size(); i++){
    //             if (pidController.get(i).getTitle() == "Flywheel PID"){
    //                 rtn = pidController.get(0).toString();
    //             }
    //         }
    //     }
    //     return rtn;
    // }
    // public String getShooterI(){
    //     //  return string : set to error string, changes to valid string if object is found.
    //     String rtn = "Nothing to Return...";
    //     if (pidController.size() > 0){
    //         for (int i = 0; i < pidController.size(); i++){
    //             if (pidController.get(i).getTitle() == "Flywheel PID"){
    //                 rtn = pidController.get(1).toString();
    //             }
    //         }
    //     }
    //     return rtn;
    // }
    // public String getShooterD(){
    //     //  return string : set to error string, changes to valid string if object is found.
    //     String rtn = "Nothing to Return...";
    //     if (pidController.size() > 0){
    //         for (int i = 0; i < pidController.size(); i++){
    //             if (pidController.get(i).getTitle() == "Flywheel PID"){
    //                 rtn = pidController.get(2).toString();
    //             }
    //         }
    //     }
    //     return rtn;
    // }
    // public String getShooterF(){
    //     //  return string : set to error string, changes to valid string if object is found.
    //     String rtn = "Nothing to Return...";
    //     if (pidController.size() > 0){
    //         for (int i = 0; i < pidController.size(); i++){
    //             if (pidController.get(i).getTitle() == "Flywheel PID"){
    //                 rtn = pidController.get(3).toString();
    //             }
    //         }
    //     }
    //     return rtn;
    // }
    // public String getShooterSetpoint(){
    //     //  return string : set to error string, changes to valid string if object is found.
    //     String rtn = "Nothing to Return...";
    //     if (pidController.size() > 0){
    //         for (int i = 0; i < pidController.size(); i++){
    //             if (pidController.get(i).getTitle() == "Flywheel PID"){
    //                 rtn = pidController.get(4).toString();
    //             }
    //         }
    //     }
    //     return rtn;
    // }
    /**
     * May or may not be applied later
     */
    public void setShooterP(double kP){
         if (pidController.size() > 0){
             for (int i = 0; i < pidController.size(); i++){
                 if (pidController.get(i).getTitle() == "Flywheel PID"){
                     pidController.get(i).withProperties(Map.of("P", kP));
                 }
             }
         }
    }
    public void setShooterI(double kI){
        if (pidController.size() > 0){
            for (int i = 0; i < pidController.size(); i++){
                if (pidController.get(i).getTitle() == "Flywheel PID"){
                    pidController.get(i).withProperties(Map.of("I", kI));
                }
            }
        }
    }
    public void setShooterD(double kD){
        if (pidController.size() > 0){
            for (int i = 0; i < pidController.size(); i++){
                if (pidController.get(i).getTitle() == "Flywheel PID"){
                    pidController.get(i).withProperties(Map.of("D", kD));
                }
            }
        }
    }
    public void setShooterF(double kF){
        if (pidController.size() > 0){
            for (int i = 0; i < pidController.size(); i++){
                if (pidController.get(i).getTitle() == "Flywheel PID"){
                    pidController.get(i).withProperties(Map.of("F", kF));
                }
            }
        }
    }
    public void setShooterSetpoint(double kSetpoint){
        if (pidController.size() > 0){
            for (int i = 0; i < pidController.size(); i++){
                if (pidController.get(i).getTitle() == "Flywheel PID"){
                    pidController.get(i).withProperties(Map.of("Setpoint", kSetpoint));
                }
            }
        }
    }
    
	//-------- Autonomous --------\\

} //end of class Shuffleboard