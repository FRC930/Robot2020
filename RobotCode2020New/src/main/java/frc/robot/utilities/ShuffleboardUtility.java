/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.utilities;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.networktables.NetworkTableEntry;

//-------- CLASS --------\\

public class ShuffleboardUtility {	

	//-------- CONSTANTS --------\\

    //-------- DECLARATIONS --------\\
    
    private SendableChooser<Command> sendableChooser;
    // private List<ShuffleboardComponent<?>> pidController;
    // private double kP;
    // private double kI;
    // private double kD;
    // private double kF;
    // private double kSetpoint;
	private boolean intakeIndicator;
    private boolean shootIndicator;
    private boolean manualMode;
	private double distanceFromTarget;
    private String shotType;
    private boolean shooterUpToSpeed;
    // private String fmsColor;
    // private String logger;
    // private String fmsColorDebug;
    private double hopperSpeed;
    private double shooterRPM;
    private boolean shooterAngle;
    private double turretSpeed;
    private double turretEncoderPosition;
    private double gyroYaw;
    private double shootSpeed;
    private ShuffleboardTab testDebugTab;
    private ShuffleboardTab driverStationTab;
    private NetworkTableEntry intakingEntry;
    private NetworkTableEntry shootingEntry;
    private NetworkTableEntry manualModeEntry;
    private NetworkTableEntry distanceFromTargetEntry;
    private NetworkTableEntry shotTypeEntry;
    private NetworkTableEntry shooterUpToSpeedEntry;
    private NetworkTableEntry turretSpeedEntry;
    private NetworkTableEntry hopperSpeedEntry;
    private NetworkTableEntry shooterAngleEntry;
    private NetworkTableEntry shooterRPMEntry;
    private NetworkTableEntry turretEncoderPositionEntry;
    private NetworkTableEntry gyroYawEntry;
    private NetworkTableEntry shooterSetEntry;

    //-------- CONSTRUCTOR --------\\

    private ShuffleboardUtility() {

        //pidController = testDebugTab.getComponents();
        intakeIndicator = false;
        shootIndicator = false;
        manualMode = false;
        // turretEncoder = 0.0;
        distanceFromTarget = 0.0;
        shotType = "";
        shooterUpToSpeed = false;
        // fmsColor = "";
        // logger = "";
        // fmsColorDebug = "";
        hopperSpeed = 0.0;
        shooterRPM = 0.0;
        shooterAngle = false;
        turretSpeed = 0.0;
        turretEncoderPosition = 0.0;
        gyroYaw = 0.0;
        // kP = 0.0;
        // kI = 0.0;
        // kD = 0.0;
        // kF = 0.0;
        // kSetpoint = 0.0;
        shootSpeed = Constants.FLYWHEEL_TELEOP_SPEED;
        driverStationTab = Shuffleboard.getTab("Driver Station");
        testDebugTab = Shuffleboard.getTab("Testing & Debugging");
        intakingEntry = driverStationTab.add("Intaking?", intakeIndicator).getEntry();
        shootingEntry = driverStationTab.add("Shooting?", shootIndicator).getEntry();
        manualModeEntry = driverStationTab.add("Manual Mode?", manualMode).getEntry();
        distanceFromTargetEntry = driverStationTab.add("Distance from Target", distanceFromTarget).getEntry();
        shotTypeEntry = driverStationTab.add("Shot Type", shotType).getEntry();
        shooterUpToSpeedEntry = driverStationTab.add("Is Shooter up to speed?", shooterUpToSpeed).getEntry();
        turretSpeedEntry = testDebugTab.add("Turret Speed", turretSpeed).getEntry();
        hopperSpeedEntry = testDebugTab.add("Hopper Speed", hopperSpeed).getEntry();
        shooterAngleEntry = testDebugTab.add("Shooter Position (True = Far)", shooterAngle).getEntry();
        shooterRPMEntry = testDebugTab.add("Shooter RPM", shooterRPM).getEntry();
        turretEncoderPositionEntry = testDebugTab.add("Turret Encoder Position", turretEncoderPosition).getEntry();
        gyroYawEntry = testDebugTab.add("Gyro Yaw", gyroYaw).getEntry();
        shooterSetEntry = driverStationTab.add("set Shooter DEFUALT IS 0.8",shootSpeed).getEntry();

        sendableChooser = new SendableChooser<Command>();
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
	public void putIntakeIndicator(boolean IntakeIndicator){
		intakeIndicator = IntakeIndicator;
        intakingEntry.setBoolean(intakeIndicator);
    }
    
	public void putShootIndicator(boolean ShootIndicator){
		shootIndicator = ShootIndicator;
		shootingEntry.setBoolean(shootIndicator);
    }

    public void putManualMode(boolean ManualMode){
        manualMode = ManualMode;
        manualModeEntry.setBoolean(manualMode);
    }

	public void putDistanceFromTarget(double DistanceFromTarget){
		distanceFromTarget = DistanceFromTarget;
        distanceFromTargetEntry.setNumber(distanceFromTarget);
    }

    // TODO: find method for shot types
	public void putShotType(String ShotType){
		shotType = ShotType;
		shotTypeEntry.setString(shotType);
    }

    public void putHopperFeed(){
        
    }

    public void putShooterUpToSpeed(boolean ShooterUpToSpeed){
        shooterUpToSpeed = ShooterUpToSpeed;
        shooterUpToSpeedEntry.setBoolean(shooterUpToSpeed);
    }

    // public String getFMSColor(){
	// 	fmsColor = SmartDashboard.getString("FMS Color", "No Color Available");
	// 	return fmsColor;
    // }

	//----- Testing & Debugging -----\\

    public void putTurretSpeed(double TurretSpeed){
        turretSpeed = TurretSpeed;
        turretSpeedEntry.setNumber(turretSpeed);
    }

    public void putHopperSpeed(double HopperSpeed){
        hopperSpeed = HopperSpeed;
        hopperSpeedEntry.setNumber(hopperSpeed);
    }

    public void putShooterAngle(boolean ShooterAngle){
        shooterAngle = ShooterAngle;
        shooterAngleEntry.setBoolean(shooterAngle);
    }

	// public void getLogger(String logger){
	// 	this.logger = logger;
	// 	SmartDashboard.putString("Logger Level", logger);
    // }
    
	public void putShooterRPM(double ShooterRPM){
		shooterRPM = ShooterRPM;
		shooterRPMEntry.setNumber(shooterRPM);
    }
    
	public void putTurretEncoderPosition(double TurretEncoderPosition){
		turretEncoderPosition = TurretEncoderPosition;
		turretEncoderPositionEntry.setNumber(turretEncoderPosition);
    }

    public void putGyroYaw(double GyroYaw){
        gyroYaw = GyroYaw;
        gyroYawEntry.setNumber(gyroYaw);
    }

    public double getShooterSpeed(){
        return shooterSetEntry.getDouble(shootSpeed);
    }

    public void addAutonOptions(String pathName,CommandBase autoCommand){
        sendableChooser.addOption(pathName,autoCommand);
    }

    public void setDefaultAutonOptions(String pathName,CommandBase autoCommand){
        sendableChooser.setDefaultOption(pathName,autoCommand);
    }
    public Command getSelectedAutonPath(){
        return sendableChooser.getSelected();
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
    // public void setShooterP(double kP){
    //      if (pidController.size() > 0){
    //          for (int i = 0; i < pidController.size(); i++){
    //              if (pidController.get(i).getTitle() == "Flywheel PID"){
    //                  pidController.get(i).withProperties(Map.of("P", kP));
    //              }
    //          }
    //      }
    // }
    // public void setShooterI(double kI){
    //     if (pidController.size() > 0){
    //         for (int i = 0; i < pidController.size(); i++){
    //             if (pidController.get(i).getTitle() == "Flywheel PID"){
    //                 pidController.get(i).withProperties(Map.of("I", kI));
    //             }
    //         }
    //     }
    // }
    // public void setShooterD(double kD){
    //     if (pidController.size() > 0){
    //         for (int i = 0; i < pidController.size(); i++){
    //             if (pidController.get(i).getTitle() == "Flywheel PID"){
    //                 pidController.get(i).withProperties(Map.of("D", kD));
    //             }
    //         }
    //     }
    // }
    // public void setShooterF(double kF){
    //     if (pidController.size() > 0){
    //         for (int i = 0; i < pidController.size(); i++){
    //             if (pidController.get(i).getTitle() == "Flywheel PID"){
    //                 pidController.get(i).withProperties(Map.of("F", kF));
    //             }
    //         }
    //     }
    // }
    // public void setShooterSetpoint(double kSetpoint){
    //     if (pidController.size() > 0){
    //         for (int i = 0; i < pidController.size(); i++){
    //             if (pidController.get(i).getTitle() == "Flywheel PID"){
    //                 pidController.get(i).withProperties(Map.of("Setpoint", kSetpoint));
    //             }
    //         }
    //     }
    // }    
} //end of class Shuffleboard
