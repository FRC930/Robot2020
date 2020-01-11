package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * ShooterSubsystem
 */
public class ShooterSubsystem extends SubsystemBase {

    // All measurements are in meters
    private final double BALL_RADIUS = 0.0889;
    private final double GOAL_HEIGHT_OUTER_LOW = 2.11455;
    private final double GOAL_HEIGHT_OUTER_TOP = 2.87655;
    private final double GOAL_HEIGHT_INNER_LOW = 2.33045;
    private final double GOAL_HEIGHT_INNER_MIDDLE = 2.49555;
    private final double GOAL_HEIGHT_INNER_TOP = 2.66065;
    private final double INNER_GOAL_FROM_WALL = 0.74295;
    private final double START_HEIGHT = 0; //Change for robot
    private final double GRAVITY = 9.8;

    // Distance is only assigned to, but is kept just in case we want to
    // add more functionality later
    private double angle;
    private double distance;
    private double velocity;
    private ShotType shotType;

    public ShooterSubsystem() {
        velocity = 0.0;
        angle = 0.0;
        distance = 0.0;
        shotType = ShotType.NONE;
    }

    /**
     * Use this to set the position of the robot
     * <p>
     * The methods {@link #calculateVelocity} and {@link #calculateTrajectory} are
     * called in this method also
     * 
     * @param angle    is the current angle of the shooter, angles inputed as degrees, converted to radians
     * @param distance is the distance away from the wall, in meters
     */    
    public void setPosition(double angle, double distance) {
        this.angle = Math.toRadians(angle);
        this.distance = distance;
        this.calculateVelocity(this.angle, distance);
        this.calculateTrajectory(this.angle, distance);
    }

    /**
     * Returns the velocity required to make the shot
     * 
     * @return {@link #velocity} set in {@link #calculateVelocity}
     */
    public double getVelocity() {
        return this.velocity;
    }
    
    /**
     * Returns whether the inner shot is possible
     * 
     * @return {@link #willMakeInner} set in {@link #calculateTrajectory}
     */
    public ShotType getPossibleShot() {
        return this.shotType;
    }

    /**
     * Calculate the velocity that the ball will need to shot at in order to make
     * the inner goal
     * 
     * @param angle    is the current angle of the shooter
     * @param distance is the distance away from the wall
     */
    private void calculateVelocity(double angle, double distance) {
        this.velocity = (Math.sqrt(9.8 / (2 * Math.tan(angle) * distance - GOAL_HEIGHT_INNER_MIDDLE)) * distance)
                / Math.cos(angle);
    }

    /**
     * Calculate the trajectory of the ball based on the velocity calculated in
     * {@link #calculateVelocity}, the angle of the shot, and the distance away
     * <p>
     * This method will then see if the ball makes the outer and inner goals
     * 
     * @param angle    is the current angle of the shooter
     * @param distance is the distance away from the wall
     */
    private void calculateTrajectory(double angle, double distance) {
        double xVelocity = velocity * Math.cos(angle);
        double yVelocity = velocity * Math.sin(angle);
        double tAtWallHit = distance / xVelocity;
        double yAtWallHit = calculateY(yVelocity, tAtWallHit);
        double tAtInnerWallHit = (INNER_GOAL_FROM_WALL + distance) / xVelocity;
        double yAtInnerWallHit = calculateY(yVelocity, tAtInnerWallHit);
        if (yAtWallHit > GOAL_HEIGHT_OUTER_LOW + BALL_RADIUS && yAtInnerWallHit < GOAL_HEIGHT_OUTER_TOP - BALL_RADIUS) {
            shotType = ShotType.OUTER;
            if (yAtInnerWallHit > GOAL_HEIGHT_INNER_LOW + BALL_RADIUS && yAtInnerWallHit < GOAL_HEIGHT_INNER_TOP - BALL_RADIUS) {
                shotType = ShotType.INNER;
            }
        } else {
            shotType = ShotType.NONE;
        }
    }

    /**
     * CalculateY is a helper method to calculate the current y position based on
     * the y velocity and the time
     * 
     * @param yVelocity is the initial y velocity
     * @param time      is the time on the parabola
     */
    private double calculateY(double yVelocity, double time) {
        /**
         * g * t^2 y * t - --------- + h 2
         */
        return START_HEIGHT + yVelocity * time - 0.5 * GRAVITY * time * time;
    }

    public static enum ShotType {
        NONE(0), INNER(1), OUTER(2);

        private ShotType(int type) {

        }
    }
}