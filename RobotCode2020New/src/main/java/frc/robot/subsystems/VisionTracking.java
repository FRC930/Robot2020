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
    public final double IF_YOU_SEE_THIS_CODE_NO_WORK = 0.12345;
    public final double HORIZONTAL_ANGLE_THRESHOLD = 0.6;
    public final double DEFAULT_HORIZONTAL_SPEED = 0.4;
    public final double MAXIMUM_ANGLE = 27;
   
    //--Ports

  //-------- DECLARATIONS --------\\
  private NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
  
 // tv  Whether the limelight has any valid targets (0 or 1)
 private NetworkTableEntry tv = limelightTable.getEntry("tv");
 private double validtarget = -1;
 // tx  Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
 private NetworkTableEntry tx = limelightTable.getEntry("tx");
 private double horizontaloffset;
 // ty  Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
 private NetworkTableEntry ty = limelightTable.getEntry("ty");
 private double verticleoffset;
 // ta  Target Area (0% of image to 100% of image)
 private NetworkTableEntry ta = limelightTable.getEntry("ta");
 private double percentofimage;
 // ts  Skew or rotation (-90 degrees to 0 degrees)
 private NetworkTableEntry ts = limelightTable.getEntry("ts");
 private double skew;
 // tl  The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency.
 private NetworkTableEntry tl = limelightTable.getEntry("tl");
 private double latency;

  public VisionTracking() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts").getDouble(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("tl").getDouble(0);

  }
  //-------- METHODS --------\\
  private double rotate(double xAngle, double previousAngle, double targetVisiblity) {
    private double horizontaladjustment = 0;
   
    if(Math.abs(xAngle) > HORIZONTAL_ANGLE_THRESHOLD); {

      horizontaladjustment = DEFAULT_HORIZONTAL_SPEED * (xAngle / MAXIMUM_ANGLE);
    }

    if (validtarget = 0); {

      if(Math.abs(previousAngle) > HORIZONTAL_ANGLE_THRESHOLD) {

        horizontaladjustment = DEFAULT_HORIZONTAL_SPEED * (previousAngle / MAXIMUM_ANGLE); 
      }

    }
    return horizontaladjustment;

  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    }
  }
 