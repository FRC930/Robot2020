package frc.robot.commands.autocommands.paths;

import frc.robot.subsystems.DriveSubsystem;

import java.util.List;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Spin extends CommandBase {
    private DriveSubsystem driveSubsystem;

    public Spin(DriveSubsystem subsystem) {
        driveSubsystem = subsystem;
    }
    @Override
    public void execute() {
        if (driveSubsystem.getYaw() < 360) {
            driveSubsystem.tankDriveVolts(-2.0, 2.0);
        }
        else if (driveSubsystem.getYaw() > 361.5){
            driveSubsystem.tankDriveVolts(1.0, -1.0);
        }
    }
}