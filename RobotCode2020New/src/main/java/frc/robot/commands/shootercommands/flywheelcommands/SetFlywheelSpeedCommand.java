/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.shootercommands.flywheelcommands;
import frc.robot.commands.shootercommands.flywheelcommands.DefaultFlywheelCommand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.utilities.ShuffleboardUtility;
import frc.robot.Constants;
import frc.robot.subsystems.FlywheelSubsystem;

//-------- COMMAND CLASS --------\\

public class SetFlywheelSpeedCommand extends CommandBase {

    // -------- DECLARATIONS --------\\

    private FlywheelSubsystem m_FlywheelSubsystem;
    private ShuffleboardUtility shuffleboardUtility;
    private CommandScheduler scheduler;

    // -------- CONSTRUCTOR --------\\

    public SetFlywheelSpeedCommand(FlywheelSubsystem flywheelSubsystem) {
        m_FlywheelSubsystem = flywheelSubsystem;
        shuffleboardUtility = ShuffleboardUtility.getInstance();
        addRequirements(m_FlywheelSubsystem);

        scheduler = CommandScheduler.getInstance();
    }

    //-------- COMMANDBASE METHODS --------\\

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        //Kil default command
        scheduler.unregisterSubsystem(m_FlywheelSubsystem);
        new DefaultFlywheelCommand(m_FlywheelSubsystem, shuffleboardUtility.getShooterSpeed());
        scheduler.setDefaultCommand(m_FlywheelSubsystem, new DefaultFlywheelCommand(m_FlywheelSubsystem, Constants.FLYWHEEL_AUTON_SPEED));
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return true;
    }

} // end of class KillFlywheelCommand
