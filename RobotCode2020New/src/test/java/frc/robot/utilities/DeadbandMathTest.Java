package frc.robot.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static frc.robot.utilities.DeadbandMath.ShotType.*;

import org.junit.Test;

public class DeadbandMathTest {

    @Test
    public void test90_10() {
        DeadbandMath mathClass = new DeadbandMath();
        // Values
        mathClass.setPosition(90, 10);
    }
}