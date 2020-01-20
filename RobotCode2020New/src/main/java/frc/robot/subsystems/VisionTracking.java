/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class VisionTracking extends SubsystemBase {

  //-------- CONSTANTS --------\\
    private final double If_You_See_This_Code_No_Work = 0.12345;

    //--Ports

  //-------- DECLARATIONS --------\\
  
 // tv  Whether the limelight has any valid targets (0 or 1)
 private double tv;
 // tx  Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
 private double tx;
 // ty  Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
 private double ty;
 // ta  Target Area (0% of image to 100% of image)
 private double ta;
 // ts  Skew or rotation (-90 degrees to 0 degrees)
 private double ts;
 // tl  The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency.
 private double tl;
 
  

  public VisionTracking() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts").getDouble(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("tl").getDouble(0);

  }
  //-------- METHODS --------\\
 // Whether the limelight has any valid targets (0 or 1) 
 NetworkTableEntry tv = double.getEntry("tv");
 // Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees) 
 NetworkTableEntry tx = double.getEntry("tx");
 // Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees) 
 NetworkTableEntry ty = double.getEntry("ty");
 // Target Area (0% of image to 100% of image) 
 NetworkTableEntry ta = double.getEntry("ta");
 // Skew or rotation (-90 degrees to 0 degrees) 
 NetworkTableEntry ts = double.getEntry("ts");
 // The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency. 
 NetworkTableEntry tl = double.getEntry("tl");

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    }
  }
