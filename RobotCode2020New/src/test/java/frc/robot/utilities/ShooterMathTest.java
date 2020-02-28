package frc.robot.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static frc.robot.utilities.ShooterMath.ShotOutcome;

import org.junit.Test;

/**
 * ShooterMathTest
 */
public class ShooterMathTest {

    @Test
    public void test20_10() {
        ShooterMath mathClass = ShooterMath.getInstance();
        // Values
        mathClass.setPosition(20, 10);
        assertEquals(21.27769, mathClass.getVelocity(), 0.0001);
        assertSame(mathClass.getPossibleShot(), ShotOutcome.INNER);
    }

    @Test
    public void test40_8() {
        ShooterMath mathClass = ShooterMath.getInstance();
        // Values
        mathClass.setPosition(40, 8);
        assertEquals(11.48286, mathClass.getVelocity(), 0.0001);
        assertSame(mathClass.getPossibleShot(), ShotOutcome.INNER);
    }

    @Test
    public void test40_3() {
        ShooterMath mathClass = ShooterMath.getInstance();
        // Values
        mathClass.setPosition(40, 3);
        assertEquals(13.46558, mathClass.getVelocity(), 0.0001);
        assertSame(mathClass.getPossibleShot(), ShotOutcome.NONE);
    }

    //Tests for number extremes

    @Test
    public void test90_8() {
        ShooterMath mathClass = ShooterMath.getInstance();
        // Values
        mathClass.setPosition(90, 8);
        assertEquals(-1.0, mathClass.getVelocity(), 0.0001);
        assertSame(mathClass.getPossibleShot(), ShotOutcome.NONE);
    }

    @Test
    public void test91_8() {
        ShooterMath mathClass = ShooterMath.getInstance();
        // Values
        mathClass.setPosition(91, 8);
        assertEquals(-1.0, mathClass.getVelocity(), 0.0001);
        assertSame(mathClass.getPossibleShot(), ShotOutcome.NONE);
    }
    
    @Test
    public void test0_8() {
        ShooterMath mathClass = ShooterMath.getInstance();
        // Values
        mathClass.setPosition(0, 8);
        assertEquals(-1.0, mathClass.getVelocity(), 0.0001);
        assertSame(mathClass.getPossibleShot(), ShotOutcome.NONE);
    }

    @Test
    public void testneg1_8() {
        ShooterMath mathClass = ShooterMath.getInstance();
        // Values
        mathClass.setPosition(-1, 8);
        assertEquals(-1.0, mathClass.getVelocity(), 0.0001);
        assertSame(mathClass.getPossibleShot(), ShotOutcome.NONE);
    }
    
    @Test
    public void test25_100() {
        ShooterMath mathClass = ShooterMath.getInstance();
        // Values
        mathClass.setPosition(25, 100);
        assertEquals(-1.0, mathClass.getVelocity(), 0.0001);
        assertSame(mathClass.getPossibleShot(), ShotOutcome.NONE);
    }

    @Test
    public void test25_0() {
        ShooterMath mathClass = ShooterMath.getInstance();
        // Values
        mathClass.setPosition(25, 0);
        assertEquals(-1.0, mathClass.getVelocity(), 0.0001);
        assertSame(mathClass.getPossibleShot(), ShotOutcome.NONE);
    }
    
    @Test
    public void test25_neg1() {
        ShooterMath mathClass = ShooterMath.getInstance();
        // Values
        mathClass.setPosition(25, -1);
        assertEquals(-1.0, mathClass.getVelocity(), 0.0001);
        assertSame(mathClass.getPossibleShot(), ShotOutcome.NONE);
    }

    @Test
    public void testX() {
        ShooterMath mathClass = ShooterMath.getInstance();

        mathClass.setPosition(39, 5);
        System.out.println(mathClass.getVelocity());
    }
}