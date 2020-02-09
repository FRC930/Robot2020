package frc.robot.utilities;

import static org.junit.Assert.assertEquals;
import static frc.robot.utilities.DeadbandMath.ShotType.*;

import org.junit.Test;

/**
 * DeadbandMathTest
 */
public class DeadbandMathTest {

    @Test
    public void BestArea() {
        DeadbandMath mathClass = new DeadbandMath();
        // Values
        assertEquals(BEST, mathClass.calculateShotType(0, 8.25));
        assertEquals(BEST, mathClass.calculateShotType(10, 8.25));
        assertEquals(BEST, mathClass.calculateShotType(-12, 11));
        assertEquals(BEST, mathClass.calculateShotType(24, 4.8));
        assertEquals(BEST, mathClass.calculateShotType(-22, 8.6));
    }

    @Test    
    public void MaybeArea() {
        DeadbandMath mathClass = new DeadbandMath();
        // Values
        assertEquals(MAYBE, mathClass.calculateShotType(-30, 5.7));
        assertEquals(MAYBE, mathClass.calculateShotType(27, 4.5));
        assertEquals(MAYBE, mathClass.calculateShotType(32, 2));
        assertEquals(MAYBE, mathClass.calculateShotType(-23, 8));
        assertEquals(MAYBE, mathClass.calculateShotType(33, 4));
        assertEquals(MAYBE, mathClass.calculateShotType(-34, 3.1));
    }

    @Test
    public void MissArea() {
        DeadbandMath mathClass = new DeadbandMath();
        // Values
        assertEquals(MISS, mathClass.calculateShotType(-62, 3.2));
        assertEquals(MISS, mathClass.calculateShotType(80, 2.2));
        assertEquals(MISS, mathClass.calculateShotType(40, 2.2));
        assertEquals(MISS, mathClass.calculateShotType(-33, 6.7));
    }

    @Test
    public void ErrorArea() {
        DeadbandMath mathClass = new DeadbandMath();
        // Values
        assertEquals(ERROR, mathClass.calculateShotType(90, 3));
        assertEquals(ERROR, mathClass.calculateShotType(-17, 20));
        assertEquals(ERROR, mathClass.calculateShotType(20, -10));
        assertEquals(ERROR, mathClass.calculateShotType(-46, 12));
    }

}