/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterAngleSubsystem;
import frc.robot.subsystems.ShooterAngleSubsystem.SolenoidValues;

public class RunExtendShooterPistonCommand extends CommandBase {

    private ShooterAngleSubsystem m_ShooterAngleSubsystem;
    
    public RunExtendShooterPistonCommand(ShooterAngleSubsystem ShooterAngleSubsystem){
        m_ShooterAngleSubsystem = ShooterAngleSubsystem;
        addRequirements(m_ShooterAngleSubsystem);
    }
    // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_ShooterAngleSubsystem.set(SolenoidValues.EXTEND);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

   // Returns true when the command should end.
   @Override
   public boolean isFinished() {
     return true;
   }
}//End of class RunExtendShooterPistonCommand