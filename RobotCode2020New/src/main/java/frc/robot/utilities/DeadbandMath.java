package frc.robot.utilities;

/**
 * Shooter Subsystem
 */
public class DeadbandMath {
    
    //All measurements are in meters.
    private final double BALL_RADIUS = 0.0889;
    private final double OUTER_RING_RADIUS = 0.439928;
    private final double INNER_RING_RADIUS = 0.1651;
    private final double DISTANCE_TO_INNER = 0.74295;
    private final double COMP_OUTER_RADIUS = OUTER_RING_RADIUS - BALL_RADIUS;
    private final double COMP_INNER_RADIUS = INNER_RING_RADIUS - BALL_RADIUS;
    private final double LINE_1_M = DISTANCE_TO_INNER / (-COMP_OUTER_RADIUS - COMP_INNER_RADIUS);
    private final double LINE_1_B = (DISTANCE_TO_INNER * COMP_OUTER_RADIUS) / (-COMP_OUTER_RADIUS - COMP_INNER_RADIUS);
    private final double LINE_2_M = DISTANCE_TO_INNER / (COMP_INNER_RADIUS - COMP_OUTER_RADIUS);
    private final double LINE_2_B = (DISTANCE_TO_INNER * COMP_OUTER_RADIUS) / (COMP_INNER_RADIUS - COMP_OUTER_RADIUS);
    private final double LINE_3_M = DISTANCE_TO_INNER / (COMP_OUTER_RADIUS + COMP_INNER_RADIUS);
    private final double LINE_3_B = (DISTANCE_TO_INNER * -COMP_OUTER_RADIUS) * (COMP_OUTER_RADIUS + COMP_INNER_RADIUS);
    private final double LINE_4_M = DISTANCE_TO_INNER / (COMP_OUTER_RADIUS - COMP_INNER_RADIUS);
    private final double LINE_4_B = (DISTANCE_TO_INNER * -COMP_OUTER_RADIUS) / (COMP_OUTER_RADIUS - COMP_INNER_RADIUS);

    public DeadbandMath() {
    }

    public static enum ShotType {
        MISS(0), MAYBE(1), SURE(2);

        private int type;

        private ShotType(int type) {
            this.type = type;
        }
    }

    /**
     * Use this to set the position of the robot
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
        return calculateShotType(Math.sin(Math.toRadians(angle)) * distanceFromGoal, Math.cos(Math.toRadians(angle)) * distanceFromGoal);
    }

    /**
     * Calculate the area the robot is in using the distances calculated in
     * {@link #setPosition}.
     * <p>
     * This method will calculate the chance of making a shot in the inner goal.
     * 
     * @param distanceFromWall is the current distance from the wall.
     * @param distanceFromCenter is the current distance from a line directly in front of the goal.
     * 
     * @return {@link #shotType}
     */
    private ShotType calculateShotType(double distanceFromWall, double distanceFromCenter) {
        double yOfLine2 = LINE_2_M * distanceFromCenter + LINE_2_B;
        double yOfLine3 = LINE_3_M * distanceFromCenter + LINE_3_B;
        
        if (distanceFromWall >= yOfLine2 && distanceFromWall >= yOfLine3) {
            return ShotType.SURE;
        } else { 
            double yOfLine1 = LINE_1_M * distanceFromCenter + LINE_1_B;
            if (distanceFromWall >= yOfLine1 && distanceFromWall < yOfLine2) {
                return ShotType.MAYBE;
            } else {
                double yOfLine4 = LINE_4_M * distanceFromCenter + LINE_4_B;
                if(distanceFromWall >= yOfLine4 && distanceFromWall < yOfLine3) {
                    return ShotType.MAYBE;
                } else {
                    return ShotType.MISS;
                }
            }
        }
    }
}