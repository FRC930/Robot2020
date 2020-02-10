package frc.robot.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.wpi.first.wpilibj.util.Color;
import frc.robot.commands.colorwheelcommands.rotationalcontrolcommands.RotationalControlTrackerCommand;
import frc.robot.subsystems.ColorSensorSubsystem;

import com.revrobotics.ColorSensorV3;

import org.junit.Test;


public class RotationalControlTrackerCommandTest {

    ColorSensorSubsystem subsystem = mock(ColorSensorSubsystem.class);
    RotationalControlTrackerCommand rotationalControlTrackerCommand = new RotationalControlTrackerCommand(subsystem);

    @Test
    public void getNearestColorTest() {
        //all color tests need to run initalized
        rotationalControlTrackerCommand.initialize();

        //Color wheel order and makes some colors to test
        Color blueColor = new Color(0.143, 0.427, 0.429); //field color = red   //blue = 0.999 added
        Color greenColor = new Color(0.197, 0.561, 0.240); //field color = yellow   //green = 0.998 added
        Color redColor = new Color(0.561, 0.232, 0.114); //field color = blue   //red = 0.907 added
        Color yellowColor = new Color(0.361, 0.524, 0.113); //field color = green   //yellow = 0.998 added

        //when class getSensorColor() gets called in ColorSensorSubsystem.class, makes it return blue for the test
        when(subsystem.getSensorColor()).thenReturn(blueColor);
        String outputString = rotationalControlTrackerCommand.getNearestColor(blueColor); //uses getSensorColor()'s returned color to find Field color
        assertEquals("Blue", outputString); //checking blue's field color to Red

        when(subsystem.getSensorColor()).thenReturn(redColor);
        outputString = rotationalControlTrackerCommand.getNearestColor(redColor);
        assertEquals("Red", outputString);

        when(subsystem.getSensorColor()).thenReturn(greenColor);
        outputString = rotationalControlTrackerCommand.getNearestColor(greenColor);
        assertEquals("Green", outputString);

        when(subsystem.getSensorColor()).thenReturn(yellowColor);
        outputString = rotationalControlTrackerCommand.getNearestColor(yellowColor);
        assertEquals("Yellow", outputString);

        Color blackColor = new Color(0, 0, 0);

        when(subsystem.getSensorColor()).thenReturn(blackColor);
        outputString = rotationalControlTrackerCommand.getNearestColor(blackColor);
        assertEquals("Unknown", outputString);

        Color whiteColor = new Color(1.0, 1.0, 1.0);

        when(subsystem.getSensorColor()).thenReturn(whiteColor);
        outputString = rotationalControlTrackerCommand.getNearestColor(whiteColor);
        assertEquals("Unknown", outputString);
    }
    
    /*@Test
    public void isFinishedMethodTest() {
        rotationalControlTrackerCommand.isFinished();
        boolean complete = rotationalControlTrackerCommand.isFinished();
        assertTrue(complete);
    }*/
}