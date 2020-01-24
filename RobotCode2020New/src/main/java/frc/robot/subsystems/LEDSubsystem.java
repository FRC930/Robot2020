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

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class LEDSubsystem extends SubsystemBase {
    public LEDSubsystem(AddressableLED leds, AddressableLEDBuffer ledsBuffer) 
    {
        leds.setLength(ledsBuffer.getLength());
        leds.setData(ledsBuffer);
        leds.start();
    }

    @Override
    public void periodic() {      

    }
}
