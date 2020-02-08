/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.subsystems;

import java.util.logging.Level;
import java.util.logging.Logger;

import frc.robot.utilities.SmootherStack;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//-------- SUBSYSTEM CLASS --------\\

public class LimelightSubsystem extends SubsystemBase {

    // -------- CONSTANTS --------\\

    // default limelight value :)
    public final double IF_YOU_SEE_THIS_CODE_NO_WORK = 0.12345;

    // max angle of the limelight's POV (-27 to 27)
    public final double MAXIMUM_ANGLE = 27;

    // the height between the limelight and the target
    public final double TARGET_HEIGHT = 1.6764;

    // the angle the camera is mounted at on the turret
    public final double CAMERA_ANGLE = 15;

    // both used for the equasion of the error we found
    public final double ERROR_EQ_SLOPE = 0.23638537459;
    // NEED the zero for Jabroni's OCD
    public final double ERROR_EQ_INTERCEPT = -0.37613082;

    // default limelight return values
    public final double DEFAULT_HORIZ_ANGLE_OFFSET = -100;
    public final double DEFAULT_VERT_ANGLE_OFFSET = -200;

    // -------- DECLARATIONS --------\\

    // network table that holds the limelight's settings
    private NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

    // tv Whether the limelight has any valid targets (0 or 1)
    private boolean validTarget;

    // tx Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
    private double horizontalOffset;

    // ty Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
    private double verticleOffset;

    // ta Target Area (0% of image to 100% of image)
    private double percentOfImage;

    // ts Skew or rotation (-90 degrees to 0 degrees)
    private double skew;

    // logger
    private Logger logger;

    //private FIFOStack smoother = new FIFOStack(10);

    // enum for the different limelight pipelines
    public enum LimelightPipelines {

        CLOSE_PIPELINE(0), MID_PIPELINE(1), FAR_PIPELINE(2);

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
        logger = Logger.getLogger(LimelightSubsystem.class.getName());
        logger.setLevel(Level.INFO);
    }

    // -------- METHODS --------\\

    // this returns the horizontal angle between the limelights crosshair and the target crosshair :)
    public double getHorizontalOffset() {

        logger.entering(getClass().getName(), "getHorizontalOffset()");

        //smoother.insert(limelightTable.getEntry("tx").getDouble(DEFAULT_HORIZ_ANGLE_OFFSET));

        horizontalOffset = limelightTable.getEntry("tx").getDouble(DEFAULT_HORIZ_ANGLE_OFFSET);

        logger.log(Level.FINER, "Horizontal Offset = " + horizontalOffset);
        logger.exiting(getClass().getName(), "getHorizontalOffset()");

        return horizontalOffset;
    }

    // the distance between the robot and the goal :)
    public double getDistance() {

        logger.entering(getClass().getName(), "getDistance()");

        // initial calculated distance
        double estDistance;

        // error between actual and initial calculated distance
        double error;

        // sum of initial calculated distance and distance error
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

    // whether the limelight sees a target or not :)
    public boolean getValidTargets() {

        logger.entering(getClass().getName(), "getValidTargets()");

        validTarget = limelightTable.getEntry("tv").getDouble(989) > 0.0 ? true : false;

        logger.log(Level.FINE, "Valid Target?: " + validTarget);
        logger.exiting(getClass().getName(), "getValidTargets()");

        return validTarget;
    }

    // this returns the verticle offset between the limelights crosshair and the target crosshair :)
    public double getVerticleOffset() {

        logger.entering(getClass().getName(), "getVerticleOffset()");

        verticleOffset = limelightTable.getEntry("ty").getDouble(DEFAULT_VERT_ANGLE_OFFSET);

        logger.log(Level.FINER, "Verticle Offset = " + verticleOffset);
        logger.exiting(getClass().getName(), "getVerticleOffset()");

        return verticleOffset;
    }

    // sets the pipeline, or configuration, of the limelight
    public void setPipeline(LimelightPipelines pipeline) {

        logger.entering(getClass().getName(), "setPipeline()");

        // sets the pipeline to the associated number of the pipeline enum
        limelightTable.getEntry("pipeline").setNumber(pipeline.getPipeline());

        logger.log(Level.FINE, "Pipeline: " + pipeline);
        logger.exiting(getClass().getName(), "setPipeline()");
    }

    @Override
    public void periodic() {    

        limelightTable.getEntry("tx").getDouble(989);

    }

} // end of class LimelightSubsystem
