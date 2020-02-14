package frc.robot.commands.autocommands.paths;

import frc.robot.subsystems.DriveSubsystem;

import java.util.List;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GyroSubsystem;;

public class Spin extends CommandBase {
    private DriveSubsystem driveSubsystem;
    private GyroSubsystem gyroSubsystem;

    public Spin(DriveSubsystem dSubsystem,GyroSubsystem gSubsystem) {
        driveSubsystem = dSubsystem;
        gyroSubsystem = gSubsystem;
    }
    @Override
    public void execute() {
        if (gyroSubsystem.getYaw() < 360) {
            driveSubsystem.tankDriveVolts(-2.0, 2.0);
        }
        else if (gyroSubsystem.getYaw() > 361.5){
            driveSubsystem.tankDriveVolts(1.0, -1.0);
        }
    }
}