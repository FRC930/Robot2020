package frc.robot.commands.autocommands.actioncommands;

import frc.robot.subsystems.DriveSubsystem;

import java.util.List;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GyroSubsystem;;

public class SpinCommand extends CommandBase {
    private DriveSubsystem driveSubsystem;
    private GyroSubsystem gyroSubsystem;

    public SpinCommand(DriveSubsystem dSubsystem,GyroSubsystem gSubsystem) {
        driveSubsystem = dSubsystem;
        gyroSubsystem = gSubsystem;
    }
    @Override
    public void execute() {
        
    }
    private void spin(double angle){        
        if (gyroSubsystem.getYaw() < angle) {
            driveSubsystem.tankDriveVolts(-2.0, 2.0);
        }
        else if (gyroSubsystem.getYaw() > angle + 1.5){
            driveSubsystem.tankDriveVolts(1.0, -1.0);
        }
    }
}