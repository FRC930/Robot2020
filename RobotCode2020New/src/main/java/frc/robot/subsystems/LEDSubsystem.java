/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class LEDSubsystem extends SubsystemBase {
    private AddressableLEDBuffer ledsBuffer;
    private AddressableLED leds;

    public LEDSubsystem(AddressableLED leds, AddressableLEDBuffer ledsBuffer) 
    {
        leds.setLength(ledsBuffer.getLength());
        leds.setData(ledsBuffer);
        leds.start();

        this.leds = leds;
        this.ledsBuffer = ledsBuffer;
        // timer = new Timer();
    }

    public void startTimer()
    {
        // timer.start();
    }

    public void setLEDs(int r, int g, int b)
    {
        for(int i = 0; i < ledsBuffer.getLength(); i++)
        {
            ledsBuffer.setRGB(i, r, g, b);
        }
        leds.setData(ledsBuffer);
    }

    @Override
    public void periodic() {      

    }
}
