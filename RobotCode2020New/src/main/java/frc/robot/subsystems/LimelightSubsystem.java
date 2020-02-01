/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;


public class LimelightSubsystem extends SubsystemBase {

  //-------- CONSTANTS --------\\

    // default limelight value :)
    public final double IF_YOU_SEE_THIS_CODE_NO_WORK = 0.12345;
    
    // max angle of the limelight's POV (-27 to 27) :)
    public final double MAXIMUM_ANGLE = 27;

    // the height between the limelight and the target :)

    public final double TARGET_HEIGHT = 1.6764;
    
    // the angle the camera is mounted at on the turret :)
    public final double CAMERA_ANGLE = 45;
   
    // both used for the equasion of the error we found :)
    public final double ERROR_EQ_SLOPE = 0.23638537459;
    public final double ERROR_EQ_INTERCEPT = -.37613082;
    
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
 
    // tl  The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency.
    private double latency;

    // logger
    private Logger logger;
 
  
  public LimelightSubsystem() {
    
    logger = Logger.getLogger(LimelightSubsystem.class.getName());
    logger.setLevel(Level.INFO);

  }  
  //-------- METHODS --------\\
  
  // this returns the horizontal angle between the limelights crosshair and the target crosshair :)
  public double getHorizontalOffset(){

    logger.entering(getClass().getName(), "getHorizontalOffset()");

    horizontalOffset = limelightTable.getEntry("tx").getDouble(0.12345);

    logger.log(Level.FINER, "Horizontal Offset = " + horizontalOffset);

    logger.exiting(getClass().getName(), "getHorizontalOffset()");

    return horizontalOffset;

  }

  // the distance between the robot and the goal :)
  public double getDistance( double angleOffset){

    logger.entering(getClass().getName(),  "getDistance()");

    double estDistance;
    double error;
    double distanceAndError;

    estDistance = TARGET_HEIGHT / Math.tan(CAMERA_ANGLE + angleOffset);
    error = (ERROR_EQ_SLOPE * estDistance) + ERROR_EQ_INTERCEPT;
    distanceAndError = estDistance + error;

    logger.log(Level.FINE, "Estimated distance = " + estDistance);
    logger.log(Level.FINE, "Error = " + error);
    logger.log(Level.FINE, "estDistance + error = " + distanceAndError);

    logger.exiting(getClass().getName(), "getDistance()");

    return distanceAndError;
  }

  // whether the limelight sees a target or not :)
  public boolean getValidTargets(){

    logger.entering(getClass().getName(), "getValidTargets()");

    validTarget = limelightTable.getEntry("tv").getBoolean(false);

    logger.log(Level.FINE, "Valid Target?: " + validTarget);

    logger.exiting(getClass().getName(), "getValidTargets()");

    return validTarget;

  }

  // this returns the verticle offset between the limelights crosshair and the target crosshair :)
  public double getVerticleOffset(){

    logger.entering(getClass().getName(), "getVerticleOffset()");

    verticleOffset = limelightTable.getEntry("ty").getDouble(0.12345);

    logger.log(Level.FINER, "Verticle Offset = " + verticleOffset);

    logger.exiting(getClass().getName(), "getVerticleOffset()");

    return verticleOffset;

  }

  /* 
  public double previousXAngle() {

    return 0.0;

  }

  public double previousYAngle() {
    
    return 0.0;

  }
  */
 
  @Override
  public void periodic() {

    // This method will be called once per scheduler run

  }

}
  