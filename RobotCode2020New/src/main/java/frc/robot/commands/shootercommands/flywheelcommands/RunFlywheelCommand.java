/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.shootercommands.flywheelcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlywheelSubsystem;

//-------- COMMANDBASE CLASS --------\\

public class RunFlywheelCommand extends CommandBase {

  //-------- DECLARATIONS --------\\

  private FlywheelSubsystem m_FlywheelSubsystem;
  private double m_Speed;

  //-------- CONSTRUCTOR --------\\

  public RunFlywheelCommand(FlywheelSubsystem flywheelSubsystem, double speed) {
    m_FlywheelSubsystem = flywheelSubsystem;
    m_Speed = speed;
    addRequirements(m_FlywheelSubsystem);
  }

  //--------- COMMANDBASE METHODS ----------\\

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_FlywheelSubsystem.setSpeed(m_Speed);  //Run the flywheel at a certain speed
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
<<<<<<< HEAD:RobotCode2020New/src/main/java/frc/robot/commands/shootercommands/RunFlywheelCommand.java
    //System.out.println("isAtSpeed(): " + isAtSpeed());
=======
>>>>>>> 5861273be32beac358aaf9987676cb3d9ebd4973:RobotCode2020New/src/main/java/frc/robot/commands/shootercommands/flywheelcommands/RunFlywheelCommand.java
    return isAtSpeed();
  }

  //TODO: Change 5880 and 50 to constants please
  private boolean isAtSpeed() {
    return this.m_FlywheelSubsystem.getSpeed() < (this.m_Speed * 5880) + 50 || this.m_FlywheelSubsystem.getSpeed() > (this.m_Speed * 5880) - 50;
  }
<<<<<<< HEAD:RobotCode2020New/src/main/java/frc/robot/commands/shootercommands/RunFlywheelCommand.java
}
=======

} // end of command class RunFlywheelCommand

>>>>>>> 5861273be32beac358aaf9987676cb3d9ebd4973:RobotCode2020New/src/main/java/frc/robot/commands/shootercommands/flywheelcommands/RunFlywheelCommand.java
