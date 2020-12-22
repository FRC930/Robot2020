/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.subsystems;

import java.util.logging.Logger;

//import frc.robot.utilities.SmootherStack;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
//import frc.robot.utilities.ShuffleboardUtility;

//-------- SUBSYSTEM CLASS --------\\

public class LimelightSubsystem extends SubsystemBase {

    // -------- CONSTANTS --------\\

    // logger
    private static final Logger logger = Logger.getLogger(LimelightSubsystem.class.getName());

    // default limelight value :)
    public final double IF_YOU_SEE_THIS_CODE_NO_WORK = 0.12345;

    // max angle of the limelight's POV (-27 to 27)
    public final double MAXIMUM_ANGLE = 27;

    // the height between the limelight and the target
    public final double TARGET_HEIGHT = 1.524;

    // the angle the camera is mounted at on the turret
    public final double CAMERA_ANGLE = 15;

    // both used for the equation of the error we found
    public final double ERROR_EQ_SLOPE = 0.23638537459;
    // NEED the zero for Jabroni's OCD
    public final double ERROR_EQ_INTERCEPT = -0.37613082;

    // default limelight return values
    private final double DEFAULT_HORIZ_ANGLE_OFFSET = -100;
    private final double DEFAULT_VERT_ANGLE_OFFSET = -200;
    private final double DEFAULT_VALID_TARGET = -1;

    // -------- DECLARATIONS --------\\

    // network table that holds the limelight's settings
    private NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

    // shuffleboardUtility
    //private ShuffleboardUtility shuffleboardUtility;

    // tv Whether the limelight has any valid targets (0 or 1)
    private boolean validTarget;

    // tx Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
    private double horizontalOffset;

    // ty Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
    private double verticleOffset;

    private double targetArea;

    private double skew;

    // the Limelight's current pipeline
    private LimelightPipelines currentPipeline;

    //private FIFOStack smoother = new FIFOStack(10);

    // enum for the different limelight pipelines
    public enum LimelightPipelines {

        NO_ZOOM(0), ZOOM_2X(1);

        private final int pipelineNumber;

        LimelightPipelines(int pipelineNumber) {
            this.pipelineNumber = pipelineNumber;
        }

        public int getPipeline() {
            return this.pipelineNumber;
        }
    }

    //-------- CONSTRUCTOR --------\\

    public LimelightSubsystem() {
        //shuffleboardUtility = ShuffleboardUtility.getInstance();
    }

    // -------- METHODS --------\\

    // this returns the horizontal angle between the limelights crosshair and the target crosshair :)
    public double getHorizontalOffset() {

        logger.entering(LimelightSubsystem.class.getName(), "getHorizontalOffset()");

        //smoother.insert(limelightTable.getEntry("tx").getDouble(DEFAULT_HORIZ_ANGLE_OFFSET));

        horizontalOffset = limelightTable.getEntry("tx").getDouble(DEFAULT_HORIZ_ANGLE_OFFSET);

        logger.log(Constants.LOG_LEVEL_FINER, "Horizontal Offset = " + horizontalOffset);
        logger.exiting(LimelightSubsystem.class.getName(), "getHorizontalOffset()");

        return horizontalOffset;
    }

    // the distance between the robot and the goal :)
    public double getDistance() {

        logger.entering(LimelightSubsystem.class.getName(), "getDistance()");

        // initial calculated distance
        double estDistance;

        // error between actual and initial calculated distance
        double error;

        // sum of initial calculated distance and distance error
        double distanceAndError;

        estDistance = TARGET_HEIGHT / Math.tan(Math.toRadians(CAMERA_ANGLE + getVerticleOffset()));
        error = (ERROR_EQ_SLOPE * estDistance) + ERROR_EQ_INTERCEPT;
        distanceAndError = estDistance + error;

        logger.log(Constants.LOG_LEVEL_FINE, "Estimated distance = " + estDistance);
        logger.log(Constants.LOG_LEVEL_FINE, "Error = " + error);
        logger.log(Constants.LOG_LEVEL_FINE, "estDistance + error = " + distanceAndError);
        logger.exiting(LimelightSubsystem.class.getName(), "getDistance()");

        return distanceAndError;
    }

    // whether the limelight sees a target or not :)
    public boolean getValidTargets() {

        // logger.entering(LimelightSubsystem.class.getName(), "getValidTargets()");

        validTarget = limelightTable.getEntry("tv").getDouble(DEFAULT_VALID_TARGET) > 0.0 ? true : false;

        // logger.log(Level.FINE, "Valid Target?: " + validTarget);
        // logger.exiting(LimelightSubsystem.class.getName(), "getValidTargets()");

        return validTarget;
    }

    // this returns the verticle offset between the limelights crosshair and the target crosshair :)
    public double getVerticleOffset() {

        // logger.entering(LimelightSubsystem.class.getName(), "getVerticleOffset()");

        verticleOffset = limelightTable.getEntry("ty").getDouble(DEFAULT_VERT_ANGLE_OFFSET);

        // logger.log(Level.FINER, "Verticle Offset = " + verticleOffset);
        // logger.exiting(LimelightSubsystem.class.getName(), "getVerticleOffset()");

        return verticleOffset;
    }

    public double getTargetArea() {

        targetArea = limelightTable.getEntry("ta").getDouble(DEFAULT_VERT_ANGLE_OFFSET);
        return targetArea;

    }
    
    public double getSkew() {

        skew = limelightTable.getEntry("ts").getDouble(-1000);
        return skew;

    }

    // sets the pipeline, or configuration, of the limelight
    public void setPipeline(LimelightPipelines pipeline) {

        // logger.entering(LimelightSubsystem.class.getName(), "setPipeline()");

        // sets the pipeline to the associated number of the pipeline enum
        limelightTable.getEntry("pipeline").setNumber(pipeline.getPipeline());
        currentPipeline = pipeline;

        // logger.log(Level.FINE, "Pipeline: " + pipeline);
        // logger.exiting(LimelightSubsystem.class.getName(), "setPipeline()");
    }

    // returns the Limelight's pipeline, returns "error" if no pipeline can be found
    public String getPipeline() {

        // holds the name of the current pipeline
        String pipelineName = "error";

        if(currentPipeline.getPipeline() == 0) {
            pipelineName = "no zoom";
        } else if(currentPipeline.getPipeline() == 1) {
            pipelineName = "zoom";
        }

        return pipelineName;
    }

    // used to turn the limelight LEDs on and off
    public void setLightMode(int mode) {
        limelightTable.getEntry("ledMode").setNumber(mode);
    }

    @Override
    public void periodic() {    

        // if(getDistance() > DISTANCE_THRESHOLD) {
        //     setPipeline(LimelightPipelines.ZOOM_2X);
        // } else {
        //     setPipeline(LimelightPipelines.NO_ZOOM);
        // }

        // SmartDashboard.putNumber("horiz offset", getHorizontalOffset());
        // SmartDashboard.putNumber("verical offset", getVerticleOffset());
        // SmartDashboard.putBoolean("valid targets", getValidTargets());
        // SmartDashboard.putNumber("distance", getDistance());
        // SmartDashboard.putString("pipeline", getPipeline());
        SmartDashboard.putNumber("skew", getSkew());

        //shuffleboardUtility.setDistanceFromTarget(getDistance());
    }

} // end of class LimelightSubsystem
