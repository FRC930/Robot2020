package frc.robot.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/*                                                  NOT DEALING WITH THIS
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.commands.colorwheelcommands.GetColorCommand;
import frc.robot.subsystems.ColorSensorSubsystem;

import com.revrobotics.ColorSensorV3;

import org.junit.Test;


public class GetColorTest {

    ColorSensorSubsystem subsystem = mock(ColorSensorSubsystem.class);
    GetColorCommand thing = new GetColorCommand(subsystem);

    @Test
    public void getNearestColorTest() {
        //all color tests need to run inistialized
        thing.initialize();

        //Color wheel order and makes some colors to test
        Color blueColor = new Color(0.143, 0.427, 0.429); //field color = red   //blue = 0.999 added
        Color greenColor = new Color(0.197, 0.561, 0.240); //field color = yellow   //green = 0.998 added
        Color redColor = new Color(0.561, 0.232, 0.114); //field color = blue   //red = 0.907 added
        Color yellowColor = new Color(0.361, 0.524, 0.113); //field color = green   //yellow = 0.998 added

        //when class getSensorColor() gets called in ColorSensorSubsystem.class, makes it return blue for the test
        when(subsystem.getSensorColor()).thenReturn(blueColor);
        String outputString = thing.positionalTrackerCounter(); //uses getSensorColor()'s returned color to find Field color
        assertEquals("Red", outputString); //checking blue's field color to Red

        when(subsystem.getSensorColor()).thenReturn(redColor);
        outputString = thing.positionalTrackerCounter();
        assertEquals("Blue", outputString);

        when(subsystem.getSensorColor()).thenReturn(greenColor);
        outputString = thing.positionalTrackerCounter();
        assertEquals("Yellow", outputString);

        when(subsystem.getSensorColor()).thenReturn(yellowColor);
        outputString = thing.positionalTrackerCounter();
        assertEquals("Green", outputString);

        Color blackColor = new Color(0, 0, 0);

        when(subsystem.getSensorColor()).thenReturn(blackColor);
        outputString = thing.positionalTrackerCounter();
        assertEquals("Unknown", outputString);

        Color whiteColor = new Color(1, 1, 1);

        when(subsystem.getSensorColor()).thenReturn(whiteColor);
        outputString = thing.positionalTrackerCounter();
        assertEquals("Unknown", outputString);
    }

    @Test
    public void rotationalTrackerCounterTest() {
        thing.initialize();

		for(int i = 0;i < 25;i++) {
        }
    }
}
*/