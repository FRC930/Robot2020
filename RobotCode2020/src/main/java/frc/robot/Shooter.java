package frc.robot;

/**
 * Shooter
 */
public class Shooter {
    // All measurements are in meters
    private static final double BALL_RADIUS = 0.0889;
    private static final double GOAL_HEIGHT_OUTER_LOW = 2.11455;
    private static final double GOAL_HEIGHT_OUTER_TOP = 2.87655;
    private static final double GOAL_HEIGHT_INNER_LOW = 2.33045;
    private static final double GOAL_HEIGHT_INNER_TOP = 2.66065;
    private static final double INNER_GOAL_FROM_WALL = 0.74295;
    private static final double START_HEIGHT = 0;
    private static final double GRAVITY = 9.8;
    private static final double TARGET_HEIGHT = 2.49555;

    private double velocity;
    private double angle;
    private double distance;
    private boolean willMakeInner;

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
        double tAtWallHit = distance / xVelocity;
        double yAtWallHit = START_HEIGHT + yVelocity * tAtWallHit - 0.5 * GRAVITY * tAtWallHit * tAtWallHit;
        double tAtInnerWallHit = (INNER_GOAL_FROM_WALL + distance) / xVelocity;
        double yAtInnerWallHit = START_HEIGHT + yVelocity * tAtInnerWallHit
                - 0.5 * GRAVITY * tAtInnerWallHit * tAtInnerWallHit;
    }
    
    private double calculateY(double yVelocity, double time) {
        /**
         *          g * t^2
         * y * t - --------- + h
         *             2
         */
        return START_HEIGHT + yVelocity * time - 0.5 * GRAVITY * time * time;
    }
}