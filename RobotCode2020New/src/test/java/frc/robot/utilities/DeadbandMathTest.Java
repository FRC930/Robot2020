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
        assertEquals(BEST, mathClass.setPosition(10, 8.25));
        assertEquals(BEST, mathClass.setPosition(-12, 11));
        assertEquals(BEST, mathClass.setPosition(24, 4.8));
        assertEquals(BEST, mathClass.setPosition(-22, 8.6));
    }

    @Test
    public void MaybeArea() {
        DeadbandMath mathClass = new DeadbandMath();
        // Values
        assertEquals(MAYBE, mathClass.setPosition(-30, 5.7));
        assertEquals(MAYBE, mathClass.setPosition(27, 4.5));
        assertEquals(MAYBE, mathClass.setPosition(32, 2));
        assertEquals(MAYBE, mathClass.setPosition(-23, 8));
        assertEquals(MAYBE, mathClass.setPosition(33, 4));
        assertEquals(MAYBE, mathClass.setPosition(-34, 3.1));
    }

    @Test
    public void MissArea() {
        DeadbandMath mathClass = new DeadbandMath();
        // Values
        assertEquals(MISS, mathClass.setPosition(-62, 3.2));
        assertEquals(MISS, mathClass.setPosition(80, 2.2));
        assertEquals(MISS, mathClass.setPosition(40, 2.2));
        assertEquals(MISS, mathClass.setPosition(-33, 6.7));
    }

    @Test
    public void ErrorArea() {
        DeadbandMath mathClass = new DeadbandMath();
        // Values
        assertEquals(ERROR, mathClass.setPosition(90, 3));
        assertEquals(ERROR, mathClass.setPosition(-17, 20));
        assertEquals(ERROR, mathClass.setPosition(20, -10));
        assertEquals(ERROR, mathClass.setPosition(-46, 12));
    }

}