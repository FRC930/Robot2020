/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ledcommands;

import frc.robot.subsystems.LEDSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * An example command that uses an example subsystem.
 */
public class RunWithAutonLEDs extends ParallelCommandGroup {
    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public RunWithAutonLEDs(Command command, LEDSubsystem ledSubsystem) {
        AutonLEDs autonLEDs = new AutonLEDs(ledSubsystem);

        addRequirements(ledSubsystem);
        addCommands(autonLEDs, command);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
