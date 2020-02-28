/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ledcommands;

import frc.robot.subsystems.LEDSubsystem;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * An example command that uses an example subsystem.
 */
public class RunWithAutonLEDs extends ParallelCommandGroup {
    private final LEDSubsystem m_LEDSubsystem;
    private AddressableLEDBuffer buffer;
    private DriverStation driverStation;
    private Alliance allianceColor;
    private int counter;
    private boolean animCheck;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public RunWithAutonLEDs(LEDSubsystem LEDSubsystem, Command command) {
        m_LEDSubsystem = LEDSubsystem;
        buffer = new AddressableLEDBuffer(60);
        counter = 0;
        animCheck = false;
        driverStation = DriverStation.getInstance();

        addRequirements(LEDSubsystem);
        this.addCommands(command);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        allianceColor = driverStation.getAlliance();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        counter++;

        if (counter >= 32) {
            if (allianceColor == Alliance.Blue) {
                if (animCheck == true) {
                    for (int i = 0; i < buffer.getLength(); i++) {
                        if (i % 2 == 0) {
                            buffer.setRGB(i, 0, 0, 255);
                        } else {
                            buffer.setRGB(i, 0, 0, 0);
                        }
                    }
                    animCheck = false;
                } else {
                    for (int i = 0; i < buffer.getLength(); i++) {
                        buffer.setRGB(i, 0, 0, 0);
                    }
                    for (int i = 1; i < buffer.getLength(); i += 2) {
                        buffer.setRGB(i, 0, 0, 255);
                    }
                    animCheck = true;
                }
            }
            if (allianceColor == Alliance.Red) {
                if (animCheck == true) {
                    for (int i = 0; i < buffer.getLength(); i++) {
                        if (i % 2 == 0) {
                            buffer.setRGB(i, 255, 0, 0);
                        } else {
                            buffer.setRGB(i, 0, 0, 0);
                        }
                    }
                    animCheck = false;
                } else {
                    for (int i = 0; i < buffer.getLength(); i++) {
                        buffer.setRGB(i, 0, 0, 0);
                    }
                    for (int i = 1; i < buffer.getLength(); i += 2) {
                        buffer.setRGB(i, 255, 0, 0);
                    }
                    animCheck = true;
                }
            }

            counter = 0;
        }

        m_LEDSubsystem.updateBuffer(buffer);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
