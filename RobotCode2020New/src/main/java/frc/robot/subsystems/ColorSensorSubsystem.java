//-------- IMPORTS --------\\

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ColorSensorV3;

import java.util.logging.Logger;
import frc.robot.Constants;

//-------- SUBSYSTEM CLASS --------\\
/**
 * <h3>ColorSensorSubsystem</h3>
 * 
 * This class is abstracts the hardware for the color sensor.
 */
public class ColorSensorSubsystem extends SubsystemBase {

    // -------- CONSTANTS --------\\

    /**
     * This logger will be used to tell information to the user
     */
    private static final Logger logger = Logger.getLogger(ColorSensorSubsystem.class.getName());

    /**
     * This is the I2C port that the color sensor will use
     */
    private final I2C.Port i2cPort = I2C.Port.kOnboard;

    // -------- DECLARATIONS --------\\

    /**
     * The color sensor that we will use to detect colors
     */
    private ColorSensorV3 sensor;

    // -------- CONSTRUCTOR --------\\

    /**
     * This is the default constructor which initializes {@link #sensor} to the
     * correct color sensor.
     */
    public ColorSensorSubsystem() {
        sensor = new ColorSensorV3(i2cPort);
    }

    // -------- METHODS --------\\

    /**
     * <h3>getSensorColor</h3>
     * This method returns the color that the sensor is reading
     * @return the color that the sensor reads
     */
    public Color getSensorColor() {
        logger.entering(ColorSensorSubsystem.class.getName(), "getSensorColor()");
        logger.log(Constants.LOG_LEVEL_FINE, "Returning the color:" + sensor.getColor().toString());
        logger.exiting(ColorSensorSubsystem.class.getName(), "getSensorColor()");
        return sensor.getColor();
    }

} // End of class