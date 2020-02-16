/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.IntakePistonSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TowerSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.FlywheelPistonSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.HopperSubsystem;

public class ShuffleboardUtility {	

	//-------- CONSTANTS --------\\

	//-------- DECLARATIONS --------\\

	private final TurretSubsystem turretSubsystem;
	private final IntakePistonSubsystem intakePistonsSubsystem;
	private final FlywheelSubsystem flywheelSubsystem;
	private final LimelightSubsystem limelightSubsystem;
	private final TowerSubsystem towerSubsystem;
    private final HopperSubsystem hopperSubsystem;
    private final DeadbandMath deadbandMath;
    private final FlywheelPistonSubsystem flywheelPistonSubsystem;
	
    private String fmsColor;
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
	private double horizontalOffput;
	private double verticalOffput;

    //-------- CONSTRUCTOR --------\\

    public ShuffleboardUtility(){
		intakePistonsSubsystem = new IntakePistonSubsystem();
		turretSubsystem = new TurretSubsystem();
		flywheelSubsystem = new FlywheelSubsystem();
		limelightSubsystem = new LimelightSubsystem();
		towerSubsystem = new TowerSubsystem();
        hopperSubsystem = new HopperSubsystem();
        flywheelPistonSubsystem = new FlywheelPistonSubsystem();
        deadbandMath = DeadbandMath.getInstance();
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

    //------- Drive Tab -------\\

	public void driveTab(){
		SmartDashboard.putBoolean("Intaking?", intakePistonsSubsystem.getIntakePistonState());
		SmartDashboard.putBoolean("Shooting?", flywheelSubsystem.isFlywheelActive());
		SmartDashboard.putNumber("Turret Encoder", turretSubsystem.getEncoderPosition());
		SmartDashboard.putNumber("Distance from Target", limelightSubsystem.getDistance());
		SmartDashboard.putNumber("Turret Encoder", turretSubsystem.getEncoderPosition());
        SmartDashboard.putNumber("Distance from Target", limelightSubsystem.getDistance());
        SmartDashboard.putBoolean("GameCube", RobotContainer.getUsingGamecube());
        SmartDashboard.putBoolean("Manual Mode", RobotContainer.getInManual());
        putShotType();
    }
    
	private void putShotType() {
        String type = "";

        if(deadbandMath.getDeadbandZone().getType() == 0) {
            type = "NO";
        } else if(deadbandMath.getDeadbandZone().getType() == 1) {
            type = "MAYBE";
        } else if(deadbandMath.getDeadbandZone().getType() == 2) {
            type = "YES";
        }

		SmartDashboard.putString("Can we make the shot?", type);
    }
    
	// public String getFMSColor(){
	// 	fmsColor = SmartDashboard.getString("FMS Color", "No Color Available");
	// 	return fmsColor;
    // }

	//----- Testing & Debugging -----\\

	public void testingTab(){
		SmartDashboard.putNumber("Tower Speed", towerSubsystem.getSpeed());
        SmartDashboard.putNumber("Hopper Speed", hopperSubsystem.getSpeed());
        SmartDashboard.putNumber("Encoder Pos", turretSubsystem.getEncoderPosition());
        SmartDashboard.putNumber("Target Area", limelightSubsystem.getTargetArea());
        SmartDashboard.putNumber("Distance from Target", limelightSubsystem.getDistance());
        SmartDashboard.putNumber("Horizontal Offset", limelightSubsystem.getHorizontalOffset());
        SmartDashboard.putNumber("Vertical Offset", limelightSubsystem.getVerticleOffset());
        putShooterAngle();
	}

	// public void getLogger(String logger){
	// 	this.logger = logger;
	// 	SmartDashboard.putString("Logger", logger);
    // }
    
	public void putShooterAngle(){

        String angle = "";

        if(flywheelPistonSubsystem.get()) {
            angle = "low";
        } else {
            angle = "high";
        }
	
		SmartDashboard.putString("Angle", angle);
	}
    
	// public void putLimelightSaturation(double saturation){
	// 	this.saturation = saturation;
	// 	SmartDashboard.putNumber("Saturation", saturation);
    // }

    //limelight hue
    
	// public void putLimelightTargetFullness(double targetFullness){
	// 	this.targetFullness = targetFullness;
	// 	SmartDashboard.putNumber("Target Fullness", targetFullness);
    // }
    
	// public void putLimelightValue(double value){
	// 	this.value = value;
	// 	SmartDashboard.putNumber("Value", value);
    // }
    
	// public void putLimelightTargetAspectRatio(double targetAspectRatio){
	// 	this.targetAspectRatio = targetAspectRatio;
	// 	SmartDashboard.putNumber("Target Aspect Ratio", targetAspectRatio);
    // }
    
	// public void putLimelightErosionDilation(double erosionDilation){
	// 	this.erosionDilation = erosionDilation;
	// 	SmartDashboard.putNumber("Erosion & Dilation", erosionDilation);
    // }

	//-------- Autonomous --------\\

} //end of class Shuffleboard
		