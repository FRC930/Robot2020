package frc.robot.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static frc.robot.utilities.ShooterMath.ShotType.*;

import org.junit.Test;

/**
 * ShooterMathTest
 */
public class ShooterMathTest {

    @Test
    public void test20_10() {
        ShooterMath mathClass = new ShooterMath();
        // Values
        mathClass.setPosition(20, 10);
        assertEquals(21.27769, mathClass.getVelocity(), 0.0001);
        assertSame(mathClass.getPossibleShot(), INNER);
    }

    @Test
    public void test40_8() {
        ShooterMath mathClass = new ShooterMath();
        // Values
        mathClass.setPosition(40, 8);
        assertEquals(11.48286, mathClass.getVelocity(), 0.0001);
        assertSame(mathClass.getPossibleShot(), INNER);
    }

    @Test
    public void test40_3() {
        ShooterMath mathClass = new ShooterMath();
        // Values
        mathClass.setPosition(40, 3);
        assertEquals(13.46558, mathClass.getVelocity(), 0.0001);
        assertSame(mathClass.getPossibleShot(), NONE);
    }
}