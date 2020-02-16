/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.IntakePistonSubsystem;

public class ShuffleboardUtility {

	//-------- CONSTANTS --------\\
	
	private final IntakePistonSubsystem intakePistonsSubsystem;

	//-------- DECLARATIONS --------\\
	
    private boolean intakeIndicator;
    private boolean shootIndicator;
    private String shotType;
    private String fmsColor;
    private double turretEncoder;
	private double distanceFromTarget;
	private boolean gameCubeBoolean;
	private boolean xboxBoolean;
    private String logger;
    private String fmsColorDebug;
    private double towerSpeed;
    private double hopperSpeed;
    private double shooterRPM;
    private double shooterAngle;
    private double turretSpeed;
	private double turretEncoderPosition;
	private double hue;
	private double targetArea;
	private double saturation;
	private double targetFullness;
	private double value;
	private double targetAspectRatio;
	private double erosionDilation;
	private double distanceFromTargetDebugger;
	private double horizontalOffset;
	private double verticalOffset;

    //-------- CONSTRUCTOR --------\\

    public ShuffleboardUtility(){
		intakePistonsSubsystem = new IntakePistonSubsystem();
    }

    private static ShuffleboardUtility instance = null;

	// Singleton
    public static ShuffleboardUtility getInstance(){
        if (instance == null){
            return instance;
        }
        else {
            return instance = new ShuffleboardUtility();
        }
    }

    //----- Drive Tab -----\\

	public void setIntakeIndicator(boolean intakeIndicator){
		this.intakeIndicator = intakeIndicator;
		SmartDashboard.putBoolean("Intaking?", intakeIndicator);
	}
	public void setShootIndicator(boolean shootIndicator){
		this.shootIndicator = shootIndicator;
		SmartDashboard.putBoolean("Shooting?", shootIndicator);
	}
	public void setShotType(String shotType){
		this.shotType = shotType;
		SmartDashboard.putString("Shot Type", shotType);
	}
	public String getFMSColor(){
		fmsColor = SmartDashboard.getString("FMS Color", "No Color Available");
		return fmsColor;
	}
	// public void setTurretEncoder(double turretEncoder){
	// 	this.turretEncoder = turretEncoder;
	// 	SmartDashboard.putNumber("Turret Encoder", turretEncoder);
	// }
	// public void setDistanceFromTarget(double distanceFromTarget){
	// 	this.distanceFromTarget = distanceFromTarget;
	// 	SmartDashboard.putNumber("Distance from Target", distanceFromTarget);
	// }
	// public void setDriverControllerGameCube(boolean gameCubeBoolean){
	// 	this.gameCubeBoolean = gameCubeBoolean;
	// 	SmartDashboard.putBoolean("GameCube", gameCubeBoolean);
	// }
	// public void setDriverControllerXBox(boolean xboxBoolean){
	// 	this.xboxBoolean = xboxBoolean;
	// 	SmartDashboard.putBoolean("Xbox", xboxBoolean);
	// }

	// //----- Testing & Debugging -----\\

	// public void setLogger(String logger){
	// 	this.logger = logger;
	// 	SmartDashboard.putString("Logger", logger);
	// }
	// public void setFMSColorDebug(String fmsColorDebug){
	// 	this.fmsColorDebug = fmsColorDebug;
	// 	SmartDashboard.putString("FMS Color Debugger", fmsColorDebug);
	// }
	// public void setTowerSpeed (double towerSpeed){
	// 	this.towerSpeed = towerSpeed;
	// 	SmartDashboard.putNumber("Tower Speed", towerSpeed);
	// }
	// public void setHopperSpeed(double hopperSpeed){
	// 	this.hopperSpeed = hopperSpeed;
	// 	SmartDashboard.putNumber("Hopper Speed", hopperSpeed);
	// }
	// public void setShooterRPM(double shooterRPM){
	// 	this.shooterRPM = shooterRPM;
	// 	SmartDashboard.putNumber("RPM", shooterRPM);
	// }
	// public void setShooterAngle(double shooterAngle){
	// 	this.shooterAngle = shooterAngle;
	// 	SmartDashboard.putNumber("Angle", shooterAngle);
	// }
	// public void setTurretSpeed(double turretSpeed){
	// 	this.turretSpeed = turretSpeed;
	// 	SmartDashboard.putNumber("Turret Speed", turretSpeed);
	// }
	// public void setTurretEncoderPosition(double turretEncoderPosition){
	// 	this.turretEncoderPosition = turretEncoderPosition;
	// 	SmartDashboard.putNumber("Encoder Pos", turretEncoderPosition);
	// }
	// public void setLimelightHue(double hue){
	// 	this.hue = hue;
	// 	SmartDashboard.putNumber("Hue", hue);
	// }
	// public void setTargetArea(double targetArea){
	// 	this.targetArea = targetArea;
	// 	SmartDashboard.putNumber("Target Area", targetArea);
	// }
	// public void setLimelightSaturation(double saturation){
	// 	this.saturation = saturation;
	// 	SmartDashboard.putNumber("Saturation", saturation);
	// }
	// public void setLimelightTargetFullness(double targetFullness){
	// 	this.targetFullness = targetFullness;
	// 	SmartDashboard.putNumber("Target Fullness", targetFullness);
	// }
	// public void setLimelightValue(double value){
	// 	this.value = value;
	// 	SmartDashboard.putNumber("Value", value);
	// }
	// public void setLimelightTargetAspectRatio(double targetAspectRatio){
	// 	this.targetAspectRatio = targetAspectRatio;
	// 	SmartDashboard.putNumber("Target Aspect Ratio", targetAspectRatio);
	// }
	// public void setLimelightErosionDilation(double erosionDilation){
	// 	this.erosionDilation = erosionDilation;
	// 	SmartDashboard.putNumber("Erosion & Dilation", erosionDilation);
	// }
	// public void setLimelightDistanceFromTargetDebugger(double distanceFromTargetDebugger){
	// 	this.distanceFromTargetDebugger = distanceFromTargetDebugger;
	// 	SmartDashboard.putNumber("Distance from Target", distanceFromTargetDebugger);
	// }
	// public void setLimelightHorizontalOffset(double horizontalOffset){
	// 	this.horizontalOffset = horizontalOffset;
	// 	SmartDashboard.putNumber("Horizontal Offset", horizontalOffset);
	// }
	// public void setLimelightVerticalOffset(double verticalOffset){
	// 	this.verticalOffset = verticalOffset;
	// 	SmartDashboard.putNumber("Vertical Offset", verticalOffset);
	// }

	//-------- Autonomous --------\\

} //end of class Shuffleboard
		