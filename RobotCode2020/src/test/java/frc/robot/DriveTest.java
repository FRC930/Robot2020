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

import java.lang.management.ManagementFactory;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

/**
 * <h3>DriveTest</h3>
 * 
 * DriveTest tests interfaces in the Drive class <br>
 * DriveTest tests mocked Spark Maxes with drivetrain
 * code. It makes sure that the different motors for
 * each drivetrain wheel will run properly.
 * 
 */
public class DriveTest {

    //-------- DECLARATIONS --------\\
    private Drive drive;
    //private CANSparkMax right1;
    //private CANSparkMax right2;
    //private CANSparkMax right3;
    //private CANSparkMax left1;
    //private CANSparkMax left2;
    //private CANSparkMax left3;

    private WPI_TalonFX tright1;
    private WPI_TalonFX tright2;
    private WPI_TalonFX tleft1;
    private WPI_TalonFX tleft2;

    /**
     * This method sets the hardware to new mocks
     */
    public void setHardware() {
        // Mocking Spark Max motor controllers
        //right1 = mock(CANSparkMax.class);
        //right2 = mock(CANSparkMax.class);
        //right3 = mock(CANSparkMax.class);
        //left1 = mock(CANSparkMax.class);
        //left2 = mock(CANSparkMax.class);
        //left3 = mock(CANSparkMax.class);

        tright1 = mock(WPI_TalonFX.class);
        tright2 = mock(WPI_TalonFX.class);
        tleft1 = mock(WPI_TalonFX.class);
        tleft2 = mock(WPI_TalonFX.class);
    }

    /**
     * This method sets the hardware for the drive and gets the instance from the
     * Drive class again
     */
    @Before
    public void setup() {
        drive = Drive.getInstance();
        setHardware();
        //drive.setMotorControllers(left1, left2, left3, right1, right2, right3);
        drive.setMotorControllers(tleft1, tleft2, tright1, tright2);
    }

    //-------- TESTS --------\\

    /**
     * This test checks that the robot holds to the right joystick deadband
     */
    @Test
    public void rightJoystick() {
        // No joystick movement
        drive.run(0, 0);

        // Very small joystick movement just below the deadband, still shouldn't be
        // enough to trigger movement
        drive.run(0, 0.000123);
        drive.run(0, -0.000123);

        // Verify that there are three (3) zero values
        verify(tleft1, times(3)).set(doubleThat(new CustomDouble(0)));
        verify(tright1, times(3)).set(doubleThat(new CustomDouble(0)));

        // Run at the deadband
        // The robot should set the motor controllers to a non-zero value
        drive.run(0, 0.000124);
        drive.run(0, -0.000124);

        // Verify that there are no (0) nonzero values
        verify(tleft1, times(0)).set(doubleThat(new NonZeroDouble()));
        verify(tright1, times(0)).set(doubleThat(new NonZeroDouble()));

        // Run at a value above the deadband
        // Returns nonzero
        drive.run(0, 0.001);
        drive.run(0, -0.001);

        // Verify that there are five (5) zero values 
        verify(tleft1, times(5)).set(doubleThat(new CustomDouble(0)));
        verify(tright1, times(5)).set(doubleThat(new CustomDouble(0)));

        // Verify that there are two (2) nonzero values
        verify(tleft1, times(2)).set(doubleThat(new NonZeroDouble()));
        verify(tright1, times(2)).set(doubleThat(new NonZeroDouble()));
    }

    /**
     * This test checks that the robot holds to the left joystick deadband
     */
    @Test
    public void leftJoystick() {
        // No joystick movement
        drive.run(0, 0);

        // Very small joystick movement just below the deadband, still shouldn't be
        // enough to trigger movement
        drive.run(0.000123, 0);
        drive.run(-0.000123, 0);

        // Verify that there are three (3) zero values
        verify(tleft1, times(3)).set(doubleThat(new CustomDouble(0)));
        verify(tright1, times(3)).set(doubleThat(new CustomDouble(0)));

        // Run at the deadband
        // The robot should set the motor controllers to a non-zero value
        drive.run(0.000124, 0);
        drive.run(-0.000124, 0);

        // Verify that there are no (0) nonzero values
        verify(tleft1, times(0)).set(doubleThat(new NonZeroDouble()));
        verify(tright1, times(0)).set(doubleThat(new NonZeroDouble()));

        // Verify that there are five (5) zero values
        verify(tleft1, times(5)).set(doubleThat(new CustomDouble(0)));
        verify(tright1, times(5)).set(doubleThat(new CustomDouble(0)));

        // Run at a value above the deadband
        drive.run(0.001, 0);
        drive.run(-0.001, 0);

        // Verify that there are two (2) nonzero values     
        verify(tleft1, times(2)).set(doubleThat(new NonZeroDouble()));
        verify(tright1, times(2)).set(doubleThat(new NonZeroDouble()));
    }

    /**
     * This test checks that the robot holds to both joystick deadband 
     */
    @Test
    public void testBothJoysticks() {
        // Very small joystick movement just below the deadband, still shouldn't be
        // enough to trigger movement
        drive.run(0.000123, 0.000123);
        drive.run(-0.000123, -0.000123);

        // Small value much below the deadband
        drive.run(0.00001, 0.00001);
        drive.run(-0.00001, -0.00001);

        // Verify that there are four (4) zero values
        verify(tleft1, times(4)).set(doubleThat(new CustomDouble(0)));
        verify(tright1, times(4)).set(doubleThat(new CustomDouble(0)));

        // Run at the deadband
        // The robot should set the motor controllers to a non-zero value
        drive.run(0.000124, 0.000124);
        drive.run(-0.000124, 0.000124);

        // Verify that there are four (4) zero values
        verify(tleft1, times(0)).set(doubleThat(new NonZeroDouble()));
        verify(tright1, times(0)).set(doubleThat(new NonZeroDouble()));

        // Run at a value above the deadband
        drive.run(0.001, 0.001);
        drive.run(-0.001, -0.001);

        // Verify that there are two (2) nonzero values
        verify(tleft1, times(2)).set(doubleThat(new NonZeroDouble()));
        verify(tright1, times(2)).set(doubleThat(new NonZeroDouble()));

        // Verify that there are six (6) zero values
        verify(tleft1, times(6)).set(doubleThat(new CustomDouble(0)));
        verify(tright1, times(6)).set(doubleThat(new CustomDouble(0)));
    } //    end of test testBothJoysticks()

    /**
     * Test to make sure that the robot moves when both joysticks
     * take in values. *Each of the input values below are cubed*
     */
    @Test
    public void testMovement() {
        // Test with the left joystick at zero
        // Right joystick in the left position
        drive.run(0, -0.5);
        verify(tleft1).set(doubleThat(new CustomDouble(-0.125)));
        verify(tright1).set(doubleThat(new CustomDouble(0.125)));

        // Right joystick at zero
        drive.run(0, 0);
        verify(tleft1).set(doubleThat(new CustomDouble(0)));
        verify(tright1).set(doubleThat(new CustomDouble(0)));

        // Right joystick in the right position
        drive.run(0, 0.5);
        verify(tleft1).set(doubleThat(new CustomDouble(0.125)));
        verify(tright1).set(doubleThat(new CustomDouble(-0.125)));

        // Test with the left joystick up
        // Right joystick at a negative value
        drive.run(0.5, -0.5);
        verify(tleft1).set(doubleThat(new CustomDouble(-0.21625)));
        verify(tright1).set(doubleThat(new CustomDouble(0.03375)));

        // Right joystick at zero
        drive.run(0.5, 0);
        verify(tleft1).set(doubleThat(new CustomDouble(-0.09125)));
        verify(tright1).set(doubleThat(new CustomDouble(-0.09125)));

        // Right joystick in the right position
        drive.run(0.5, 0.5);
        verify(tleft1).set(doubleThat(new CustomDouble(0.03375)));
        verify(tright1).set(doubleThat(new CustomDouble(-0.21625)));

        // Test with the left joystick down
        // Right joystick in the left position
        drive.run(-0.5, -0.5);
        verify(tleft1).set(doubleThat(new CustomDouble(-0.03375)));
        verify(tright1).set(doubleThat(new CustomDouble(0.21625)));

        // Right joystick at zero
        drive.run(-0.5, 0);
        verify(tleft1).set(doubleThat(new CustomDouble(0.09125)));
        verify(tright1).set(doubleThat(new CustomDouble(0.09125)));

        // Right joystick in the right position
        drive.run(-0.5, 0.5);
        verify(tleft1).set(doubleThat(new CustomDouble(0.21625)));
        verify(tright1).set(doubleThat(new CustomDouble(-0.03375)));
    } //    end of test testMovement()
} //    end of DriveTest() class

/**
 * <h3>NonZeroDouble</h3>
 * 
 * NonZeroDouble is an method that returns true if the drive.run
 * input is not zero. It will return a nonzero value if the value
 * in the drive.run method is outside the deadband (-0.001, 0.001)
 * 
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
 * CustomDouble is an method that returns true if the drive.run
 * input is zero. It will return zero if the value in the drive.run
 * method is within the deadband (-0.001, 0.001)
 * 
 */
class CustomDouble implements ArgumentMatcher<Double> {
    private double number;

    public CustomDouble(double d) {
        number = d;
    }

    @Override
    public boolean matches(Double argument) {
        if (Math.abs(argument - number) < Math.ulp(1.0)) {
            return true;
        } else {
            return false;
        }
    }
}