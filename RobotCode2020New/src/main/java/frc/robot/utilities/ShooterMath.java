package frc.robot.utilities;

/**
 * ShooterSubsystem
 */
public class ShooterMath {

    // All measurements are in meters
    private final double BALL_RADIUS = 0.0889;
    private final double GOAL_HEIGHT_OUTER_LOW = 2.11455;
    private final double GOAL_HEIGHT_OUTER_TOP = 2.87655;
    private final double GOAL_HEIGHT_INNER_LOW = 2.33045;
    private final double GOAL_HEIGHT_INNER_MIDDLE = 2.49555;
    private final double GOAL_HEIGHT_INNER_TOP = 2.66065;
    private final double INNER_GOAL_FROM_WALL = 0.74295;
    private final double START_HEIGHT = 0; // Change for robot
    private final double GRAVITY = 9.8;

    // Flag for singleton
    private static ShooterMath lastInstance = null;

    // Variables stored for later use.
    // Distance is only assigned to, but is kept just in case we want to
    // add more functionality later.
    private double angle;
    private double distance;
    private double velocity;
    private ShotOutcome shotOutcome;

    private ShooterMath() {
        this(0, 0);
    }

    private ShooterMath(double angle, double distance) {
        this.velocity = 0.0;
        this.angle = 0.0;
        this.distance = 0.0;
        this.shotOutcome = ShotOutcome.NONE;

        setPosition(this.angle, this.distance);
    }

    // Methods to get instance from singleton
    public static ShooterMath getInstance() {
        if (lastInstance == null) {
            lastInstance = new ShooterMath();
        }
        return lastInstance;
    }

    public static ShooterMath getInstance(double angle, double distance) {
        if (lastInstance == null) {
            lastInstance = new ShooterMath(angle, distance);
        }
        return lastInstance;
    }

    /**
     * Use this to set the position of the robot
     * <p>
     * The methods {@link #calculateVelocity} and {@link #calculateTrajectory} are
     * called in this method also
     * 
     * @param angle    is the current angle of the shooter, angles inputed as
     *                 degrees, converted to radians
     * @param distance is the distance away from the goal, in meters
     */
    public void setPosition(double angle, double distance) {
        this.angle = Math.toRadians(angle);
        this.distance = distance;
        this.calculateVelocity(this.angle, this.distance);
        this.calculateTrajectory(this.angle, this.distance);
    }

    /**
     * Returns the velocity required to make the shot. Will return -1 if error
     * occurs.
     * 
     * @return {@link #velocity} set in {@link #calculateVelocity}
     */
    public double getVelocity() {
        return this.velocity;
    }

    /**
     * Returns whether the inner shot is possible
     * 
     * @return {@link #shotOutcome} set in {@link #calculateTrajectory}
     */
    public ShotOutcome getPossibleShot() {
        return this.shotOutcome;
    }

    /**
     * Calculate the velocity that the ball will need to shot at in order to make
     * the inner goal. Will return -1 if error occurs.
     * 
     * @param angle    is the current angle of the shooter
     * @param distance is the distance away from the wall
     */
    private void calculateVelocity(double angle, double distance) {
        if (angle > 0 && angle < Math.toRadians(90) && distance > 0 && distance <= 13) {
            this.velocity = Math.sqrt((-this.GRAVITY * Math.pow(this.distance + this.INNER_GOAL_FROM_WALL, 2))
                    / (2 * this.GOAL_HEIGHT_INNER_MIDDLE * Math.pow(Math.cos(this.angle), 2)
                            - 2 * (this.distance + this.INNER_GOAL_FROM_WALL) * Math.cos(this.angle) * Math.sin(this.angle)));
        } else {
            this.velocity = -1.0;
        }
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
        if (yAtWallHit > GOAL_HEIGHT_OUTER_LOW + BALL_RADIUS && yAtInnerWallHit < GOAL_HEIGHT_OUTER_TOP - BALL_RADIUS
                && velocity != -1.0) {
            shotOutcome = ShotOutcome.OUTER;
            if (yAtInnerWallHit > GOAL_HEIGHT_INNER_LOW + BALL_RADIUS
                    && yAtInnerWallHit < GOAL_HEIGHT_INNER_TOP - BALL_RADIUS) {
                shotOutcome = ShotOutcome.INNER;
            }
        } else {
            shotOutcome = ShotOutcome.NONE;
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
         * y0 + vy * t - 0.5 * g * t ^ 2
         */
        return START_HEIGHT + yVelocity * time - 0.5 * GRAVITY * time * time;
    }

    public static enum ShotOutcome {
        NONE(0), OUTER(1), INNER(2);

        //private int type;

        private ShotOutcome(int type) {
            //this.type = type;
        }
    }

    public static String getShotType(ShotOutcome type){
        String shotType;
        if (type == ShotOutcome.NONE){
            shotType = "Impossible";
        }
        else if (type == ShotOutcome.OUTER){
            shotType = "Outer Goal possible";
        }
        else{
            shotType = "Inner Goal possible";
        }
        return shotType;
    }
}