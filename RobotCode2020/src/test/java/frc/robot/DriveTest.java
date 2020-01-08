/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static org.mockito.ArgumentMatchers.doubleThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.withSettings;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

/**
 * <h3>DriveTest</h3>
 * 
 * DriveTest tests interfaces in the Drive class <br>
 * Just changed the method for testing for correct values Now we use the verify
 * method to check for correct values
 */
public class DriveTest {
    
    private Drive drive;
    private CANSparkMax right1;
    private CANSparkMax right2;
    private CANSparkMax right3;
    private CANSparkMax left1;
    private CANSparkMax left2;
    private CANSparkMax left3;

    /**
     * This method sets the hardware to new mocks
     */
    public void setHardware() {
        // Mocking Spark Max motor controllers
        this.right1 = mock(CANSparkMax.class,
                withSettings().useConstructor(4, MotorType.kBrushless));
        this.right2 = mock(CANSparkMax.class,
                withSettings().useConstructor(5, MotorType.kBrushless));
        this.right3 = mock(CANSparkMax.class,
                withSettings().useConstructor(6, MotorType.kBrushless));
        this.left1 = mock(CANSparkMax.class,
                withSettings().useConstructor(1, MotorType.kBrushless));
        this.left2 = mock(CANSparkMax.class,
                withSettings().useConstructor(2, MotorType.kBrushless));
        this.left3 = mock(CANSparkMax.class,
                withSettings().useConstructor(3, MotorType.kBrushless));
    }

    /**
     * This method sets the hardware for the drive and gets the instance from the
     * Drive class again
     */
    @Before
    public void setup() {
        this.drive = Drive.getInstance();
        this.setHardware();
        this.drive.setMotorControllers(left1, left2, left3, right1, right2, right3);
    }

    //-------- TESTS --------\\

    /**
     * This test checks that the robot holds to the joystick deadband
     */
    @Test
    public void rightJoystick() {
        // No joystick movement
        this.drive.run(0, 0);

        // Very small joystick movement just below the deadband, still shouldn't be
        // enough to trigger movement
        this.drive.run(0, 0.000123);
        this.drive.run(0, -0.000123);

        // Verify that there are three (3) zero values
        verify(this.left1, times(3)).set(doubleThat(new CustomDouble(0)));
        verify(this.right1, times(3)).set(doubleThat(new CustomDouble(0)));

        // Run at the deadband
        // The robot should set the motor controllers to a non-zero value
        this.drive.run(0, 0.000124);
        this.drive.run(0, -0.000124);

        // Verify that there are no (0) nonzero values
        verify(this.left1, times(0)).set(doubleThat(new NonZeroDouble()));
        verify(this.right1, times(0)).set(doubleThat(new NonZeroDouble()));

        // Run at a value above the deadband
        // Returns nonzero
        this.drive.run(0, 0.001);
        this.drive.run(0, -0.001);

        // Verify that there are five (5) zero values 
        verify(this.left1, times(5)).set(doubleThat(new CustomDouble(0)));
        verify(this.right1, times(5)).set(doubleThat(new CustomDouble(0)));

        // Verify that there are two (2) nonzero values
        verify(this.left1, times(2)).set(doubleThat(new NonZeroDouble()));
        verify(this.right1, times(2)).set(doubleThat(new NonZeroDouble()));
    }

    /**
     * This test checks that the robot holds to the joystick deadband
     */
    @Test
    public void leftJoystick() {
        // No joystick movement
        this.drive.run(0, 0);

        // Very small joystick movement just below the deadband, still shouldn't be
        // enough to trigger movement
        this.drive.run(0.000123, 0);
        this.drive.run(-0.000123, 0);

        // Verify that there are three (3) zero values
        verify(this.left1, times(3)).set(doubleThat(new CustomDouble(0)));
        verify(this.right1, times(3)).set(doubleThat(new CustomDouble(0)));

        // Run at the deadband
        // The robot should set the motor controllers to a non-zero value
        this.drive.run(0.000124, 0);
        this.drive.run(-0.000124, 0);

        // Verify that there are no (0) nonzero values
        verify(this.left1, times(0)).set(doubleThat(new NonZeroDouble()));
        verify(this.right1, times(0)).set(doubleThat(new NonZeroDouble()));

        // Verify that there are five (5) zero values
        verify(this.left1, times(5)).set(doubleThat(new CustomDouble(0)));
        verify(this.right1, times(5)).set(doubleThat(new CustomDouble(0)));

        // Run at a value above the deadband
        this.drive.run(0.001, 0);
        this.drive.run(-0.001, 0);

        // Verify that there are two (2) nonzero values     
        verify(this.left1, times(2)).set(doubleThat(new NonZeroDouble()));
        verify(this.right1, times(2)).set(doubleThat(new NonZeroDouble()));
    }

    /**
     * Test both of the joysticks to make sure that the robot sets the values
     * correctly
     */
    @Test
    public void testBothJoysticks() {
        // Very small joystick movement just below the deadband, still shouldn't be
        // enough to trigger movement
        this.drive.run(0.000123, 0.000123);
        this.drive.run(-0.000123, -0.000123);

        // Small value much below the deadband
        this.drive.run(0.00001, 0.00001);
        this.drive.run(-0.00001, -0.00001);

        // Verify that there are four (4) zero values
        verify(this.left1, times(4)).set(doubleThat(new CustomDouble(0)));
        verify(this.right1, times(4)).set(doubleThat(new CustomDouble(0)));

        // Run at the deadband
        // The robot should set the motor controllers to a non-zero value
        this.drive.run(0.000124, 0.000124);
        this.drive.run(-0.000124, 0.000124);

        // Verify that there are four (4) zero values
        verify(this.left1, times(0)).set(doubleThat(new NonZeroDouble()));
        verify(this.right1, times(0)).set(doubleThat(new NonZeroDouble()));

        // Run at a value above the deadband
        this.drive.run(0.001, 0.001);
        this.drive.run(-0.001, -0.001);

        // Verify that there are two (2) nonzero values
        verify(this.left1, times(2)).set(doubleThat(new NonZeroDouble()));
        verify(this.right1, times(2)).set(doubleThat(new NonZeroDouble()));

        // Verify that there are six (6) zero values
        verify(this.left1, times(6)).set(doubleThat(new CustomDouble(0)));
        verify(this.right1, times(6)).set(doubleThat(new CustomDouble(0)));
    } //    end of test testBothJoysticks()

    /**
     * Test to make sure that the robot moves when it is supposed to.
     */
    @Test
    public void testMovement() {
        // Test with the left joystick at zero
        // Right joystick at a negative value
        this.drive.run(0, -0.5);
        verify(this.left1).set(doubleThat(new CustomDouble(-0.125)));
        verify(this.right1).set(doubleThat(new CustomDouble(0.125)));

        // Right joystick at zero
        this.drive.run(0, 0);
        verify(this.left1).set(doubleThat(new CustomDouble(0)));
        verify(this.right1).set(doubleThat(new CustomDouble(0)));

        // Right joystick at a positive value
        this.drive.run(0, 0.5);
        verify(this.left1).set(doubleThat(new CustomDouble(0.125)));
        verify(this.right1).set(doubleThat(new CustomDouble(-0.125)));

        // Test with the left joystick positive
        // Right joystick at a negative value
        this.drive.run(0.5, -0.5);
        verify(this.left1).set(doubleThat(new CustomDouble(-0.21625)));
        verify(this.right1).set(doubleThat(new CustomDouble(0.03375)));

        // Right joystick at zero
        this.drive.run(0.5, 0);
        verify(this.left1).set(doubleThat(new CustomDouble(-0.09125)));
        verify(this.right1).set(doubleThat(new CustomDouble(-0.09125)));

        // Right joystick at a positive value
        this.drive.run(0.5, 0.5);
        verify(this.left1).set(doubleThat(new CustomDouble(0.03375)));
        verify(this.right1).set(doubleThat(new CustomDouble(-0.21625)));

        // Test with the left joystick negative
        // Right joystick at a negative value
        this.drive.run(-0.5, -0.5);
        verify(this.left1).set(doubleThat(new CustomDouble(-0.03375)));
        verify(this.right1).set(doubleThat(new CustomDouble(0.21625)));

        // Right joystick at zero
        this.drive.run(-0.5, 0);
        verify(this.left1).set(doubleThat(new CustomDouble(0.09125)));
        verify(this.right1).set(doubleThat(new CustomDouble(0.09125)));

        // Right joystick at a positive value
        this.drive.run(-0.5, 0.5);
        verify(this.left1).set(doubleThat(new CustomDouble(0.21625)));
        verify(this.right1).set(doubleThat(new CustomDouble(-0.03375)));
    } //    end of test testMovement()
} //    end of DriveTest() class

/**
 * <h3>NonZeroDouble</h3>
 * 
 * NonZeroDouble is an argument matcher that returns true if the number is not
 * zero
 */
class NonZeroDouble implements ArgumentMatcher<Double> {
    @Override
    public boolean matches(Double argument) {
        if (Math.abs(argument) >= Math.ulp(0)) {
            return true;
        } else {
            return false;
        }
    }
}

/**
 * <h3>CustomDouble</h3>
 * 
 * CustomDouble is an argument matcher that allows the user to input a double
 * that they want to compare to in the constructor.
 */
class CustomDouble implements ArgumentMatcher<Double> {
    private double number;

    public CustomDouble(double d) {
        this.number = d;
    }

    @Override
    public boolean matches(Double argument) {
        if (Math.abs(argument - this.number) < Math.ulp(1.0)) {
            return true;
        } else {
            return false;
        }
    }
}