/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


/*interface Distance {implements Distance
  public void headOn();
  public void angled();
}
*/
public class VisionTracking extends SubsystemBase {

  //-------- CONSTANTS --------\\

  // default limelight value :)
  private final double IF_YOU_SEE_THIS_CODE_NO_WORK = 0.12345;
  
  // max angle of the limelight's POV (-27 to 27) :)
  private final double MAXIMUM_ANGLE = 27;

  // the height between the limelight and the target :)

  private final double TARGET_HEIGHT = 1.6764;
  
  // the angle the camera is mounted at on the turret :)
  private final double CAMERA_ANGLE = 45;
  
  // both used for the equasion of the error we found :)
  private final double ERROR_EQ_SLOPE = 0.23638537459;
  private final double ERROR_EQ_INTERCEPT = -.37613082;

  // limelight pipelines
  // TODO: make an enum for these
  public final int PIPELINE_CLOSE = 0;
  public final int PIPELINE_MID = 1;
  public final int PIPELINE_FAR = 2;

  private final double HORIZ_OFFSET_DEFAULT_VALUE = -100;
  private final double VERT_OFFSET_DEFAULT_VALUE = -200;
    
  //--Ports

  //-------- DECLARATIONS --------\\
  
  private NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

  // tv  Whether the limelight has any valid targets (0 or 1)
  private boolean validTarget; 

  // tx  Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
  private double horizontalOffset;

  // ty  Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
  private double verticleOffset;

  // ta  Target Area (0% of image to 100% of image)
  private double percentOfImage;

  // ts  Skew or rotation (-90 degrees to 0 degrees)
  private double skew;

  // logger
  private Logger logger;
  
  // tv  Whether the limelight has any valid targets (0 or 1)
  private NetworkTableEntry tv = limelightTable.getEntry("tv");
  private double validtarget = tv.getDouble(0.12345);
  // tx  Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
  private NetworkTableEntry tx = limelightTable.getEntry("tx");
  private double horizontaloffset = tx.getDouble(0.12345);
  // ty  Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
  private NetworkTableEntry ty = limelightTable.getEntry("ty");
  private double verticleoffset = ty.getDouble(0.12345);
  // ta  Target Area (0% of image to 100% of image)
  private NetworkTableEntry ta = limelightTable.getEntry("ta");
  private double percentofimage = ta.getDouble(0.12345);
  // ts  Skew or rotation (-90 degrees to 0 degrees)
  private NetworkTableEntry ts = limelightTable.getEntry("ts");
  skew = ts.getDouble(0.12345);
  // tl  The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency.
  private NetworkTableEntry tl = limelightTable.getEntry("tl");
  private double latency = tl.getDouble(0.12345);
  private int Distance;

 /*Head-on equation is y = 1.01717625x + 8.02978
 Angled equation is y = 9.3065108x - 14.8083 */
  public VisionTracking() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts").getDouble(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("tl").getDouble(0);

  }
  //-------- METHODS --------\\
  
  public double getHorizontalOffset(){

    logger.entering(getClass().getName(), "getHorizontalOffset()");

    horizontalOffset = limelightTable.getEntry("tx").getDouble(HORIZ_OFFSET_DEFAULT_VALUE);

    logger.log(Level.FINER, "Horizontal Offset = " + horizontalOffset);

    logger.exiting(getClass().getName(), "getHorizontalOffset()");

    return horizontalOffset;

  }

  // the distance between the robot and the goal :)
  public double getDistance(){

    logger.entering(getClass().getName(),  "getDistance()");

    // estimated distance from initial calculation
    double estDistance;

    // distance error based on our initial distance calculation
    double error;

    // sum of estimated distance and error, becomes our actual distance
    double distanceAndError;

    estDistance = TARGET_HEIGHT / Math.tan(CAMERA_ANGLE + getVerticleOffset());
    error = (ERROR_EQ_SLOPE * estDistance) + ERROR_EQ_INTERCEPT;
    distanceAndError = estDistance + error;

    logger.log(Level.FINE, "Estimated distance = " + estDistance);
    logger.log(Level.FINE, "Error = " + error);
    logger.log(Level.FINE, "estDistance + error = " + distanceAndError);

    logger.exiting(getClass().getName(), "getDistance()");

    return distanceAndError;
  }
  public double getValidTargets(){
    validtarget = tv.getDouble(0.12345);
    return validtarget;
  }
  public double getVerticleOffset(){

    logger.entering(getClass().getName(), "getVerticleOffset()");

    verticleOffset = limelightTable.getEntry("ty").getDouble(VERT_OFFSET_DEFAULT_VALUE);

    logger.log(Level.FINER, "Verticle Offset = " + verticleOffset);

    logger.exiting(getClass().getName(), "getVerticleOffset()");

    return verticleOffset;

  }

  // sets the current limelight pipeline (current settings and configuration)
  public void setPipeline(int pipeline){

    logger.entering(getClass().getName(), "setPipeline()");

    // sets the pipeline
    limelightTable.getEntry("pipeline").setNumber(pipeline);

    logger.log(Level.INFO, "Current Pipeline: " + pipeline);

    logger.exiting(getClass().getName(), "setPipeline()");

  }
 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    }
  }
