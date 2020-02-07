/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase {
    private AddressableLEDBuffer ledsBuffer;
    private AddressableLED leds;

    public LEDSubsystem(int port, int length) 
    {
        leds = new AddressableLED(port);
        ledsBuffer = new AddressableLEDBuffer(length);

        leds.setLength(ledsBuffer.getLength());
        setLEDs(0, 0, 0);
        leds.start();
    }

    public void updateBuffer(AddressableLEDBuffer buffer)
    {
        this.ledsBuffer = buffer;
        leds.setData(ledsBuffer);
    }

    public void resetLEDs()
    {
        for(int i = 0; i < ledsBuffer.getLength(); i++)
            this.ledsBuffer.setRGB(i, 0, 0, 0);

        leds.setData(ledsBuffer);
    }

    public void setLEDs(int r, int g, int b)
    {
        for(int i = 0; i < ledsBuffer.getLength(); i++)
        {
            ledsBuffer.setRGB(i, r, g, b);
        }
        leds.setData(ledsBuffer);
    }
}
