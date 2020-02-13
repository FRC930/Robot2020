/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ledcommands;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.subsystems.LEDSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class LEDSwitchCommand extends SequentialCommandGroup {
    private LEDSubsystem m_ledSubsystem;
    private AddressableLED m_leds;
    private AddressableLEDBuffer m_ledsBuffer;

    static private int flashType;

    public LEDSwitchCommand(LEDSubsystem ledSubsystem)
    {
        m_ledSubsystem = ledSubsystem;
        addRequirements(m_ledSubsystem);

        System.out.println("&&&&&&&&Calling LEDSequential");

        addCommands(new RedLEDCommand(m_ledSubsystem), new WaitCommand(1), new GreenLEDCommand(m_ledSubsystem), new WaitCommand(1), new BlueLEDCommand(m_ledSubsystem));
    }
}
