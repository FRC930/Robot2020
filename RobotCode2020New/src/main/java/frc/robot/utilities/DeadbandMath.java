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
    
    //All measurements are in meters.
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
    private Logger logger = Logger.getLogger("George");
    
    public DeadbandMath() {
        logger.setLevel(Level.FINER);
    }

    public static enum ShotType {
        ERROR(-1), MISS(0), MAYBE(1), BEST(2);

        private int type;

        private ShotType(int type) {
            this.type = type;
        }
    }

    /**
     * Use this to set the position of the robot and return the zone calculated.
     * <p>
     * The method {@link #calculateShotType} is called in this method also
     * 
     * @param angle     is the current angle from a line perpendicular to the wall/goal,
     *                  in degrees.
     * @param distanceFromGoal  is the distance away from the goal, in meters.
     * 
     * @return {@link #shotType} set in {@link #calculateShotType}
     */
    public ShotType setPosition(double angle, double distanceFromGoal) {
        logger.log(Level.INFO, "Log Level: " + logger.getLevel().toString()) ;
        logger.entering(this.getClass().getName(), "setPosition");
        ShotType shotType = null;
        if (Math.abs(angle) >= 90 || distanceFromGoal <= 0) {
            shotType = ShotType.ERROR;
        } else {
            final double robotX = Math.sin(Math.toRadians(angle)) * distanceFromGoal;
            final double robotY = Math.cos(Math.toRadians(angle)) * distanceFromGoal;
            System.out.println("Robot X:" + robotX + ": Robot Y:" + robotY);
            shotType = calculateShotType(robotX, robotY);
        }
        logger.log(Level.INFO, "setPosition.shotType = " + shotType);
        logger.exiting(this.getClass().getName(), "setPosition");
        return shotType;
    }

    /**
     * Calculate the area the robot is in using the distances calculated in
     * {@link #setPosition}.
     * <p>
     * This method will calculate the chance of making a shot in the inner goal.
     * 
     * @param distanceFromWall is the current distance from the wall.
     * @param distanceFromCenter is the current distance from a line perpendicular to the goal.
     * 
     * @return {@link #shotType}
     */
    private ShotType calculateShotType(double distanceFromCenter, double distanceFromWall) {
        logger.entering(this.getClass().getName(), "calculateShotType");
        ShotType shotType = null;

        //
        //  is this point on the field of play?
        if (distanceFromWall > MAX_DISTANCE_FROM_GOAL || 
            distanceFromWall <= 0 || 
            distanceFromCenter >= MAX_DISTANCE_FROM_MIDLINE_RIGHT || 
            distanceFromCenter <= -MAX_DISTANCE_FROM_MIDLINE_LEFT) {
            shotType = ShotType.ERROR;
        } else {
            //
            //  figure out if the point is in the "best" zone
            double yOfLine2 = LINE_2_M * distanceFromCenter + LINE_2_B;
            double yOfLine3 = LINE_3_M * distanceFromCenter + LINE_3_B;
            System.out.println("Line 2 Y:" + yOfLine2 + ": Line 3 Y:" + yOfLine3);
            if (distanceFromWall >= yOfLine2 && distanceFromWall >= yOfLine3) {
                shotType = ShotType.BEST;
            } else { 
                //
                //  figure out if we are in the left hand 'maybe' zone
                double yOfLine1 = LINE_1_M * distanceFromCenter + LINE_1_B;
                System.out.println("Line 1 Y:" + yOfLine1);
                if (distanceFromWall >= yOfLine1 && distanceFromWall < yOfLine2) {
                    shotType = ShotType.MAYBE;
                } else {
                    //
                    // figure out if we are in the right hand 'maybe' zone
                    double yOfLine4 = LINE_4_M * distanceFromCenter + LINE_4_B;
                    System.out.println("Line 4 Y:" + yOfLine4);
                    if(distanceFromWall >= yOfLine4 && distanceFromWall < yOfLine3) {
                        shotType = ShotType.MAYBE;
                    } else {
                        //
                        // since it is not in any other zone, it is a miss
                        shotType = ShotType.MISS;
                    }
                }
            }
        }
        logger.log(Level.INFO, "calculateShotType.shotType = " + shotType);
        logger.exiting(this.getClass().getName(), "calculateShotType");
        return shotType;
    }
}