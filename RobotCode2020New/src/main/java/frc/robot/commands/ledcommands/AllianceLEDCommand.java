/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ledcommands;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class AllianceLEDCommand extends CommandBase {
    private LEDSubsystem m_ledSubsystem;
    private AddressableLEDBuffer buffer;
    private int counter;
    private int ledPosition;

    public AllianceLEDCommand(LEDSubsystem ledSubsystem) {
        m_ledSubsystem = ledSubsystem;
        addRequirements(m_ledSubsystem);

        counter = 0;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        buffer = new AddressableLEDBuffer(60);
        ledPosition = 0;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        SmartDashboard.putNumber("Counter", counter);
        if (counter >= 25) {
            m_ledSubsystem.resetLEDs();
            if (DriverStation.getInstance().getAlliance() == Alliance.Red) {
                if (ledPosition == 0) {
                    for (int i = 0; i < buffer.getLength(); i += 2) {
                        buffer.setRGB(i, 255, 0, 0);
                        ledPosition = 1;
                    }
                } else if (ledPosition == 1) {
                    for (int i = 1; i < buffer.getLength(); i += 2) {
                        buffer.setRGB(i, 225, 140, 0);
                        ledPosition = 2;
                    }
                }
            } else if (DriverStation.getInstance().getAlliance() == Alliance.Blue) {
                if (ledPosition == 0) {
                    for (int i = 0; i < buffer.getLength(); i += 2) {
                        buffer.setRGB(i, 0, 0, 255);
                    }
                    ledPosition = 1;
                } else if (ledPosition == 1) {
                    for (int i = 1; i < buffer.getLength(); i += 2) {
                        buffer.setRGB(i, 0, 140, 225);
                    }
                    ledPosition = 0;
                }
            }
            
            m_ledSubsystem.updateBuffer(buffer);
            counter = 0;
        }

        counter++;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        System.out.println("isAuton: " + DriverStation.getInstance().isAutonomous());
        return !DriverStation.getInstance().isAutonomous();
    }
}
