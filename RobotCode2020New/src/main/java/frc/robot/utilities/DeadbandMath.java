/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.utilities;

import java.util.logging.*;
/**
 * Shooter Subsystem
 */
public class DeadbandMath {
    
    //All measurements are in meters
    private final double BALL_RADIUS = 0.0889;
    private final double OUTER_RING_RADIUS = 0.439928;
    private final double INNER_RING_RADIUS = 0.1651;
    private final double DISTANCE_TO_INNER = 0.74295;
    private final double MAX_DISTANCE_FROM_GOAL = 12.9;
    private final double MAX_DISTANCE_FROM_MIDLINE_LEFT = 5.805636;
    private final double MAX_DISTANCE_FROM_MIDLINE_RIGHT = 2.404364;
    private final double COMP_OUTER_RADIUS = OUTER_RING_RADIUS - BALL_RADIUS;
    private final double COMP_INNER_RADIUS = INNER_RING_RADIUS - BALL_RADIUS;
    private final double LINE_1_M = DISTANCE_TO_INNER / (-COMP_OUTER_RADIUS - COMP_INNER_RADIUS);
    private final double LINE_1_B = (DISTANCE_TO_INNER * COMP_OUTER_RADIUS) / (-COMP_OUTER_RADIUS - COMP_INNER_RADIUS);
    private final double LINE_2_M = DISTANCE_TO_INNER / (COMP_INNER_RADIUS - COMP_OUTER_RADIUS);
    private final double LINE_2_B = (DISTANCE_TO_INNER * COMP_OUTER_RADIUS) / (COMP_INNER_RADIUS - COMP_OUTER_RADIUS);
    private final double LINE_3_M = DISTANCE_TO_INNER / (COMP_OUTER_RADIUS - COMP_INNER_RADIUS);
    private final double LINE_3_B = (DISTANCE_TO_INNER * -COMP_OUTER_RADIUS) / (COMP_OUTER_RADIUS - COMP_INNER_RADIUS);
    private final double LINE_4_M = DISTANCE_TO_INNER / (COMP_OUTER_RADIUS + COMP_INNER_RADIUS);
    private final double LINE_4_B = (DISTANCE_TO_INNER * -COMP_OUTER_RADIUS) / (COMP_OUTER_RADIUS + COMP_INNER_RADIUS);

    //Constructs logger
    private Logger logger = Logger.getLogger(getClass().toString());
    
    //Class constructor - sets logger lever
    public DeadbandMath() {
        logger.setLevel(Level.FINER);
    }

    //Enum for various outcomes
    public static enum ShotType {
        ERROR(-1), MISS(0), MAYBE(1), BEST(2);

        private int type;

        private ShotType(int type) {
            this.type = type;
        }
    }

    /**
     * Use this to set the position of the robot and return the calculated shotType.
     * <p>
     * The method {@link #calculateDeadbandZone} and {@link #calculateTargetSize} is called in this method also
     * 
     * @param angle     is the current angle from a line perpendicular to the wall/goal,
     *                  in degrees.
     * @param distanceFromGoal  is the distance away from the goal, in meters.
     * 
     * @return {@link #shotType} set in {@link #calculateDeadbandZone} and {@link #calculateTargetSize}.
     */
    public ShotType calculateShotType(double angle, double distanceFromGoal) {
        logger.entering(this.getClass().getName(), "calculateShotType");
        //Sets placeholder variables
        ShotType shotType = null;
        double targetArea = 0;
        //Determines if values cannot be worked with and puts in logger.
        if (Math.abs(angle) >= 90 || distanceFromGoal <= 0) {
            logger.log(Level.WARNING, "The input values given are out of bounds.");
            logger.log(Level.WARNING, "Max Angle: 89 degrees - Given: " + angle + " || Minimum Distance: 0.0001 m - Given: " + distanceFromGoal);
            shotType = ShotType.ERROR;
        } else {
            //Finds X and Y of robot.
            final double robotX = Math.sin(Math.toRadians(angle)) * distanceFromGoal;
            final double robotY = Math.cos(Math.toRadians(angle)) * distanceFromGoal;
            logger.log(Level.INFO, "Robot X: " + robotX + " || Robot Y: " + robotY);
            //Finds if X and Y are out of the field and puts in logger.
            if (robotY > MAX_DISTANCE_FROM_GOAL || robotY <= 0 || 
            robotX >= MAX_DISTANCE_FROM_MIDLINE_RIGHT || 
            robotX <= -MAX_DISTANCE_FROM_MIDLINE_LEFT) {
                logger.log(Level.WARNING, "The values given are out of bounds.");
                logger.log(Level.WARNING, "Robot Y: " + robotY + " - Max: " + MAX_DISTANCE_FROM_GOAL + " - Min: " + 0.0001 + " || Robot X: " 
                + robotX + " - Max: " + MAX_DISTANCE_FROM_MIDLINE_RIGHT + " - Min: " + -MAX_DISTANCE_FROM_MIDLINE_LEFT);
                shotType = ShotType.ERROR;
            } else{
                //Refers to Deadband and TargetSize for calculations.
                shotType = calculateDeadbandZone(robotX, robotY);
                targetArea = calculateTargetSize(robotX, robotY);
                logger.log(Level.INFO, "Calculated Deadband Zone: " + shotType);
                logger.log(Level.INFO, "Calculated Target Size: " + targetArea);
                //Relates the two sets of calculations.
                if (shotType == ShotType.BEST && targetArea >= 0.545) {
                    shotType = ShotType.BEST;
                } else if ((shotType == ShotType.BEST || shotType == ShotType.MAYBE ) && targetArea >= BALL_RADIUS * 2) {
                    shotType = ShotType.MAYBE;
                } else {
                    shotType = ShotType.MISS;
                }
            }
        logger.log(Level.INFO, "Final ShotType: " + shotType);
        logger.exiting(this.getClass().getName(), "calculateShotType");
        return shotType;
    }

    /**
     * Calculate the area the robot is in using the distances calculated in
     * {@link #calculateShotType}.
     * <p>
     * This method will calculate the chance of making a shot in the inner goal.
     * 
     * @param distanceFromCenter is the current distance from a line perpendicular to the goal.
     * @param distanceFromWall is the current distance from the wall.
     * 
     * @return {@link #shotType}
     */
    private ShotType calculateDeadbandZone(double distanceFromCenter, double distanceFromWall) {
        logger.entering(this.getClass().getName(), "calculateDeadbandZone");
        //Placeholder for calculated shotType
        ShotType shotType = null;
        //
        //Figure out if the point is in the "best" zone.
        double yOfLine2 = LINE_2_M * distanceFromCenter + LINE_2_B;
        double yOfLine3 = LINE_3_M * distanceFromCenter + LINE_3_B;
        System.out.println("Line 2 Y: " + yOfLine2 + "- Line 3 Y: " + yOfLine3);
        if (distanceFromWall >= yOfLine2 && distanceFromWall >= yOfLine3) {
            shotType = ShotType.BEST;
        } else { 
            //
            //Figure out if we are in the left hand 'maybe' zone.
            double yOfLine1 = LINE_1_M * distanceFromCenter + LINE_1_B;
            System.out.println("Line 1 Y: " + yOfLine1);
            if (distanceFromWall >= yOfLine1 && distanceFromWall < yOfLine2) {
                shotType = ShotType.MAYBE;
            } else {
                //
                //Figure out if we are in the right hand 'maybe' zone.
                double yOfLine4 = LINE_4_M * distanceFromCenter + LINE_4_B;
                System.out.println("Line 4 Y: " + yOfLine4);
                if (distanceFromWall >= yOfLine4 && distanceFromWall < yOfLine3) {
                    shotType = ShotType.MAYBE;
                } else {
                    //
                    //Since it is not in any other zone, it is a miss.
                    shotType = ShotType.MISS;
                }
            }
        }
        logger.log(Level.INFO, "Calculated ShotType: " + shotType);
        logger.exiting(this.getClass().getName(), "calculateDeadbandZone");
        return shotType;
    }

    /**
     * Calculate the relative area of the target using the distances calculated in
     * {@link #calculateShotType}.
     * <p>
     * This method will calculate the relative size of the Outer Ring at a certain location.
     * 
     * @param distanceFromCenter is the current distance from a line perpendicular to the goal.
     * @param distanceFromWall is the current distance from the wall.
     * 
     * @return {@link #targetArea}
     */
    private double calculateTargetSize(double distanceFromCenter, double distanceFromWall) {
        logger.entering(this.getClass().getName(), "calculateTargetSize");
        double targetArea = 0;
        if (distanceFromCenter >= 0) {
            targetArea = Math.sqrt(Math.pow((OUTER_RING_RADIUS+(((OUTER_RING_RADIUS*distanceFromCenter/distanceFromWall)+((-OUTER_RING_RADIUS*distanceFromWall)/(-OUTER_RING_RADIUS+distanceFromWall)))/
            ((-distanceFromCenter/distanceFromWall)-(distanceFromWall/(-OUTER_RING_RADIUS+distanceFromCenter))))), 2)+
            Math.pow(((distanceFromWall/(distanceFromCenter-OUTER_RING_RADIUS))*(OUTER_RING_RADIUS+(((OUTER_RING_RADIUS*distanceFromCenter/distanceFromWall)+((-OUTER_RING_RADIUS*distanceFromWall)/(-OUTER_RING_RADIUS+distanceFromWall)))/
            ((-distanceFromCenter/distanceFromWall)-(distanceFromWall/(-OUTER_RING_RADIUS+distanceFromCenter)))))+((-distanceFromWall*OUTER_RING_RADIUS)/(distanceFromCenter-OUTER_RING_RADIUS))), 2));
        } else {
            targetArea = Math.sqrt(Math.pow((-OUTER_RING_RADIUS+(((-OUTER_RING_RADIUS*distanceFromCenter/distanceFromWall)+((OUTER_RING_RADIUS*distanceFromWall)/(OUTER_RING_RADIUS+distanceFromWall)))/
            ((-distanceFromCenter/distanceFromWall)-(distanceFromWall/(OUTER_RING_RADIUS+distanceFromCenter))))), 2)+
            Math.pow(((distanceFromWall/(distanceFromCenter+OUTER_RING_RADIUS))*(-OUTER_RING_RADIUS+(((-OUTER_RING_RADIUS*distanceFromCenter/distanceFromWall)+((OUTER_RING_RADIUS*distanceFromWall)/(OUTER_RING_RADIUS+distanceFromWall)))/
            ((-distanceFromCenter/distanceFromWall)-(distanceFromWall/(OUTER_RING_RADIUS+distanceFromCenter)))))+((-distanceFromWall*-OUTER_RING_RADIUS)/(distanceFromCenter+OUTER_RING_RADIUS))), 2));
        }
        logger.log(Level.INFO, "Calculated Target Area: " + targetArea);
        logger.exiting(this.getClass().getName(), "calculateTargetSize");
        return targetArea;
    }
}