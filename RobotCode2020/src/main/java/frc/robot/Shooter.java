package frc.robot;

/**
 * Shooter
 */
public class Shooter {
    // The height of the center of the inner target
    private final double BALL_RADIUS = 0.0889;
    private final double GOAL_HEIGHT_OUTER_LOW = 2.11455;
    private final double GOAL_HEIGHT_OUTER_TOP = 2.87655;
    private final double GOAL_HEIGHT_INNER_LOW = 2.33045;
    private final double GOAL_HEIGHT_INNER_TOP = 2.66065;
    private final double INNER_GOAL_FROM_WALL = 0.74295;
    private final double GRAVITY = 9.8;
    private final double TARGET_HEIGHT = 2.49555;

    private double velocity;
    private double angle;
    private double distance;

    public Shooter() {
    }

    public void setPosition(double angle, double distance) {
        this.angle = Math.toRadians(angle);
        this.distance = distance;
    }

    public boolean isPossibleShot() {
        return true;
    }

    private void calculateTrajectory(double angle, double distance) {
        double xVelocity = velocity * Math.cos(angle);
        double yVelocity = velocity * Math.sin(angle);
        double tAtYMax = yVelocity / GRAVITY;
        double yAtTMax = (yVelocity * tAtYMax) - (0.5 * GRAVITY * tAtYMax * tAtYMax);
        // TODO: Finish calculations
    }

}