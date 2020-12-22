package frc.robot.utilities;

import java.util.logging.*;

import frc.robot.Constants;

/**
 * DeadbandMath
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
    private final double MIN_HIGH_TARGET_SIZE = 0.545;
    private final double MIN_LOW_TARGET_SIZE = BALL_RADIUS * 2;

    //Enum states saved after setPosition
    private DeadbandZone deadbandZone;
    private ShotChance shotChance;

    //Static flag for singleton
    private static DeadbandMath lastInstance = null;

    //Constructs logger
    private static final Logger logger = Logger.getLogger(DeadbandMath.class.toString());
    
    //Class constructor - sets logger lever
    private DeadbandMath() {
        logger.setLevel(Constants.LOG_LEVEL_FINE);
        deadbandZone = DeadbandZone.RED;
        shotChance = ShotChance.MISS;
    }

    //Enum for deadband zones
    public static enum DeadbandZone {
        ERROR(-1), RED(0), YELLOW(1), GREEN(2);

        private int type;

        private DeadbandZone(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }
    }

    //Enum for calculated target size
    public static enum ShotChance {
        ERROR(-1), MISS(0), LOW(1), HIGH(2);

        //private int type;

        private ShotChance(int type) {
            //this.type = type;
        }
    }

    //Method to get an instance of DeadbandMath
    public static DeadbandMath getInstance() {
        if (lastInstance == null) {
            lastInstance = new DeadbandMath();
        }
        return lastInstance;
    }

    /**
     * Use this to set the position of the robot on a coordinate system and stores calculated enum states.
     * <p>
     *  The method {@link #calculateDeadbandZone} and {@link #calculateShotChance} are called in this method also
     * 
     * @param angle     is the current angle from a line perpendicular to the wall/goal,
     *                  in degrees.
     * @param distanceFromGoal  is the distance away from the goal, in meters.
     */
    public void setPosition(double angle, double distanceFromGoal) {
        logger.entering(DeadbandMath.class.getName(), "setPosition()");
        //Sets placeholder variables
        double robotX = 0;
        double robotY = 0;
        //Determines if values cannot be worked with and is inputed in logger, sets enums and returns.
        if (Math.abs(angle) >= 90 || distanceFromGoal <= 0) {
            logger.log(Constants.LOG_LEVEL_WARNING, "The input values given are out of bounds.");
            logger.log(Constants.LOG_LEVEL_WARNING, "Max Angle: 89 degrees - Given: " + angle + " || Minimum Distance: 0.0001 m - Given: " + distanceFromGoal);
            deadbandZone = DeadbandZone.ERROR;
            shotChance = ShotChance.ERROR;
        } else {
            //Finds X and Y of robot.
            robotX = Math.sin(Math.toRadians(angle)) * distanceFromGoal;
            robotY = Math.cos(Math.toRadians(angle)) * distanceFromGoal;
            logger.log(Constants.LOG_LEVEL_INFO, "Robot X: " + robotX + " || Robot Y: " + robotY);
            //Finds if X and Y are out of the field and is inputed in logger, sets enums and returns.
            if (robotY > MAX_DISTANCE_FROM_GOAL || robotY <= 0 || 
                    robotX >= MAX_DISTANCE_FROM_MIDLINE_RIGHT || 
                    robotX <= -MAX_DISTANCE_FROM_MIDLINE_LEFT) {
                logger.log(Constants.LOG_LEVEL_WARNING, "The values given are out of bounds.");
                logger.log(Constants.LOG_LEVEL_WARNING, "Robot Y: " + robotY + " - Max: " + MAX_DISTANCE_FROM_GOAL + " - Min: " + 0.0001 + " || Robot X: " + 
                    robotX + " - Max: " + MAX_DISTANCE_FROM_MIDLINE_RIGHT + " - Min: " + -MAX_DISTANCE_FROM_MIDLINE_LEFT);
                deadbandZone = DeadbandZone.ERROR;
                shotChance = ShotChance.ERROR;
            } else {
                //Calls methods to calculate enum states
                calculateDeadbandZone(robotX, robotY);
                calculateShotChance(robotX, robotY);
            }
        }   
        logger.log(Constants.LOG_LEVEL_INFO, "deadbandZone: " + deadbandZone + " || shotChance: " + shotChance);
        logger.exiting(DeadbandMath.class.getName(), "setPosition");
    }

    /**
     * Returns DeadbandZone calculated in {@link #calculateDeadBandZone}.
     * 
     * @return deadbandZone
     */
    public DeadbandZone getDeadbandZone() {
        logger.entering(DeadbandMath.class.getName(), "getDeadbandZone");
        logger.log(Constants.LOG_LEVEL_INFO, "deadbandZone: " + deadbandZone);
        logger.exiting(DeadbandMath.class.getName(), "getDeadbandZone");
        return deadbandZone;
    }

    /**
     * Returns ShotChance calculated in {@link #calculateShotChance}.
     * 
     * @return shotChance
     */
    public ShotChance getShotChance() {
        logger.entering(DeadbandMath.class.getName(), "getShotChance");
        logger.log(Constants.LOG_LEVEL_INFO, "shotChance: " + shotChance);
        logger.exiting(DeadbandMath.class.getName(), "getShotChance");
        return shotChance;
    }

    /**
     * Calculate the deadband zone the robot is in using the coordinates calculated in
     * {@link #setPosition}.
     * <p>
     * This method will calculate the chance of making a shot in the inner goal.
     * 
     * @param robotX is the current distance from a line perpendicular to the goal.
     * 
     * @param robotY is the current distance from the wall.
     */
    private void calculateDeadbandZone(double robotX, double robotY) {
        logger.entering(DeadbandMath.class.getName(), "calculateDeadbandZone");
        //
        //Figure out if the point is in the "GREEN" zone.
        double yOfLine2 = LINE_2_M * robotX + LINE_2_B;
        double yOfLine3 = LINE_3_M * robotX + LINE_3_B;
        logger.log(Constants.LOG_LEVEL_INFO, "Line 2 Y: " + yOfLine2 + "- Line 3 Y: " + yOfLine3);
        if (robotY >= yOfLine2 && robotY >= yOfLine3) {
            deadbandZone = DeadbandZone.GREEN;
        } else { 
            //
            //Figure out if we are in the left hand 'YELLOW' zone.
            double yOfLine1 = LINE_1_M * robotX + LINE_1_B;
            logger.log(Constants.LOG_LEVEL_INFO, "Line 1 Y: " + yOfLine1);
            if (robotY >= yOfLine1 && robotY < yOfLine2) {
                deadbandZone = DeadbandZone.YELLOW;
            } else {
                //
                //Figure out if we are in the right hand 'YELLOW' zone.
                double yOfLine4 = LINE_4_M * robotX + LINE_4_B;
                logger.log(Constants.LOG_LEVEL_INFO, "Line 4 Y: " + yOfLine4);
                if (robotY >= yOfLine4 && robotY < yOfLine3) {
                    deadbandZone = DeadbandZone.YELLOW;
                } else {
                    //
                    //Since it is not in any other zone, it is a miss.
                    deadbandZone = DeadbandZone.RED;
                }
            }
        }
        logger.log(Constants.LOG_LEVEL_INFO, "Calculated DeadbandZone: " + deadbandZone);
        logger.exiting(DeadbandMath.class.getName(), "calculateDeadbandZone");
    }

    /**
     * Calculate the relative area of the target using the distances calculated in
     * {@link #setPosition}.
     * <p>
     * This method will calculate the relative size of the Outer Ring at a certain location and convert into an enum state.
     * 
     * @param robotX is the current distance from a line perpendicular to the goal.
     * 
     * @param robotY is the current distance from the wall.
     */
    private void calculateShotChance(double robotX, double robotY) {
        logger.entering(DeadbandMath.class.getName(), "calculateShotChance");
        double targetArea = 0;
        if (robotX <= 0) { 
            targetArea = Math.sqrt(Math.pow(OUTER_RING_RADIUS + ((((OUTER_RING_RADIUS * robotX)/ robotY) + (-OUTER_RING_RADIUS * robotY)/(-OUTER_RING_RADIUS + robotX))/((-robotX/robotY) - (robotY/(-OUTER_RING_RADIUS + robotX)))), 2) +
            Math.pow(((robotY / (robotX - OUTER_RING_RADIUS)) * ((((OUTER_RING_RADIUS * robotX)/robotY) + ((-OUTER_RING_RADIUS * robotY)/(-OUTER_RING_RADIUS + robotX)))/(-(robotX/robotY) - (robotY/(-OUTER_RING_RADIUS + robotX))))) + ((-robotY * OUTER_RING_RADIUS)/(robotX - OUTER_RING_RADIUS)), 2));
        } else {
            targetArea = Math.sqrt(Math.pow(-OUTER_RING_RADIUS + ((((-OUTER_RING_RADIUS * robotX)/ robotY) + (OUTER_RING_RADIUS * robotY)/(OUTER_RING_RADIUS + robotX))/((-robotX/robotY) - (robotY/(OUTER_RING_RADIUS + robotX)))), 2) +
            Math.pow(((robotY / (robotX + OUTER_RING_RADIUS)) * ((((-OUTER_RING_RADIUS * robotX)/robotY) + ((OUTER_RING_RADIUS * robotY)/(OUTER_RING_RADIUS + robotX)))/(-(robotX/robotY) - (robotY/(OUTER_RING_RADIUS + robotX))))) + ((-robotY * -OUTER_RING_RADIUS)/(robotX + OUTER_RING_RADIUS)), 2));
        }
        logger.log(Constants.LOG_LEVEL_INFO, "Calculated Target Area: " + targetArea);
        if (targetArea >= MIN_HIGH_TARGET_SIZE) {
            shotChance = ShotChance.HIGH;
        } else if (targetArea >= MIN_LOW_TARGET_SIZE) {
            shotChance = ShotChance.LOW;
        } else {
            shotChance = ShotChance.MISS;
        }
        logger.log(Constants.LOG_LEVEL_INFO, "Calculated ShotChance: " + shotChance);
        logger.exiting(DeadbandMath.class.getName(), "calculateShotChance");    }
}