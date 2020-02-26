/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.utilities;

import java.util.List;
import edu.wpi.cscore.HttpCamera;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardComponent;
import edu.wpi.first.networktables.NetworkTableEntry;
// import frc.robot.commands.colorwheelcommands.rotationalcontrolcommands.RotationalControlCommandGroup;
// import frc.robot.subsystems.ColorSensorSubsystem;
// import frc.robot.subsystems.ColorWheelSpinnerSubsystem;

//-------- CLASS --------\\

public class ShuffleboardUtility {	

	//-------- CONSTANTS --------\\

    //-------- DECLARATIONS --------\\
    
    private ShuffleboardTab testDebugTab;
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
    private ShuffleboardTab driveTab;
    private ShuffleboardTab testingTab;
    //private PIDController pidController;

    //-------- CONSTRUCTOR --------\\

    private ShuffleboardUtility() {
        testDebugTab = Shuffleboard.getTab("Testing & Debugging");
        pidController = testDebugTab.getComponents();
        driveTab = Shuffleboard.getTab("Driver Station");
        testingTab = Shuffleboard.getTab("Testing & Debugging");
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
    }

    private static ShuffleboardUtility instance = null;

	// Singleton
    public static ShuffleboardUtility getInstance() {
        if (instance == null){
            return instance;
        }
        else {
            return instance = new ShuffleboardUtility();
        }
    }

    //------- Drive Tab -------\\


	public void setIntakeIndicator(boolean intakeIndicator){
		this.intakeIndicator = intakeIndicator;
		SmartDashboard.putBoolean("Intaking?", intakeIndicator);
	}
	public void setShootIndicator(boolean shootIndicator){
		this.shootIndicator = shootIndicator;
		SmartDashboard.putBoolean("Shooting?", shootIndicator);
    }
    public void setManualMode(boolean manualMode){
        this.manualMode = manualMode;
        SmartDashboard.putBoolean("Manual Mode?", manualMode);
    }
	public void setDistanceFromTarget(double distanceFromTarget){
		this.distanceFromTarget = distanceFromTarget;
		SmartDashboard.putNumber("Distance from Target", distanceFromTarget);
	}
	public void setShotType(String shotType){
		this.shotType = shotType;
		SmartDashboard.putString("Shot Type", shotType);
    }
    public void setLimelightFeed(HttpCamera limelightCamera){
        this.limelightCamera = limelightCamera;
        driveTab.add("Limelight Feed", limelightCamera);
        testingTab.add("Limelight Feed", limelightCamera);
    }
    public String getFMSColor(){
		fmsColor = SmartDashboard.getString("FMS Color", "No Color Available");
		return fmsColor;
    }

	//----- Testing & Debugging -----\\

    public void setTurretSpeed(double turretSpeed){
        this.turretSpeed = turretSpeed;
        SmartDashboard.putNumber("Turret Speed", turretSpeed);
    }
    public void setHopperSpeed(double hopperSpeed){
        this.hopperSpeed = hopperSpeed;
        SmartDashboard.putNumber("Hopper Speed", hopperSpeed);
    }
    public void setPistonAngle(double pistonAngle){
        this.pistonAngle = pistonAngle;
        SmartDashboard.putNumber("Piston Angle", pistonAngle);
    }
	public void getLogger(String logger){
		this.logger = logger;
		SmartDashboard.putString("Logger Level", logger);
	}
	public void setPistonRPM(double pistonRPM){
		this.pistonRPM = pistonRPM;
		SmartDashboard.putNumber("Piston RPM", pistonRPM);
	}
	public void setTurretEncoderPosition(double turretEncoderPosition){
		this.turretEncoderPosition = turretEncoderPosition;
		SmartDashboard.putNumber("Turret Encoder Pos", turretEncoderPosition);
    }
    public void setGyroYaw(double gyroYaw){
        this.gyroYaw = gyroYaw;
        SmartDashboard.putNumber("Gyro Yaw (LtoR Rotation)", gyroYaw);
    }
    // TODO: figure out how to get PID values into code
    public String getShooterP(){
        //  return string : set to error string, changes to valid string if object is found.
        String rtn = "Nothing to Return...";
        if (pidController.size() > 0){
            for (int i = 0; i < pidController.size(); i++){
                if (pidController.get(i).getTitle() == "Shooter PID"){
                    rtn = pidController.get(0).toString();
                }
            }
        }
        return rtn;
    }
    public String getShooterI(){
        //  return string : set to error string, changes to valid string if object is found.
        String rtn = "Nothing to Return...";
        if (pidController.size() > 0){
            for (int i = 0; i < pidController.size(); i++){
                if (pidController.get(i).getTitle() == "Shooter PID"){
                    rtn = pidController.get(1).toString();
                }
            }
        }
        return rtn;
    }
    public String getShooterD(){
        //  return string : set to error string, changes to valid string if object is found.
        String rtn = "Nothing to Return...";
        if (pidController.size() > 0){
            for (int i = 0; i < pidController.size(); i++){
                if (pidController.get(i).getTitle() == "Shooter PID"){
                    rtn = pidController.get(2).toString();
                }
            }
        }
        return rtn;
    }
    public String getShooterF(){
        //  return string : set to error string, changes to valid string if object is found.
        String rtn = "Nothing to Return...";
        if (pidController.size() > 0){
            for (int i = 0; i < pidController.size(); i++){
                if (pidController.get(i).getTitle() == "Shooter PID"){
                    rtn = pidController.get(3).toString();
                }
            }
        }
        return rtn;
    }
    public String getShooterSetpoint(){
        //  return string : set to error string, changes to valid string if object is found.
        String rtn = "Nothing to Return...";
        if (pidController.size() > 0){
            for (int i = 0; i < pidController.size(); i++){
                if (pidController.get(i).getTitle() == "Shooter PID"){
                    rtn = pidController.get(1).toString();
                }
            }
        }
        return rtn;
    }
    public void setShooterP(double kP){
        this.kP = kP;
        SmartDashboard.putNumber("Shooter PID", kP);
    }
    public void setShooterI(double kI){
        this.kI = kI;
        SmartDashboard.putNumber("Shooter PID", kI);
    }
    public void setShooterD(double kD){
        this.kD = kD;
        SmartDashboard.putNumber("Shooter PID", kD);
    }
    public void setShooterF(double kF){
        this.kF = kF;
        SmartDashboard.putNumber("Shooter PID", kF);
    }
    public void setShooterSetpoint(double kSetpoint){
        this.kSetpoint = kSetpoint;
        SmartDashboard.putNumber("Shooter PID", kSetpoint);
    }

	//-------- Autonomous --------\\

} //end of class Shuffleboard