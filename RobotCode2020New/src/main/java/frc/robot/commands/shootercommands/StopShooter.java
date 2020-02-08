/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shootercommands;

import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class StopShooter extends CommandBase {
    private ShooterSubsystem m_ShooterSubsystem;
    private LEDSubsystem m_ledSubsystem;

    public StopShooter(ShooterSubsystem shooterSubsystem, LEDSubsystem ledSubsystem) 
    {
        m_ShooterSubsystem = shooterSubsystem;
        addRequirements(m_ShooterSubsystem);
        m_ledSubsystem = ledSubsystem;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() 
    {
        //LEDSwitchCommand lsw = new LEDSwitchCommand(m_ledSubsystem, 1);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() 
    {
        m_ShooterSubsystem.stop();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) 
    {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
