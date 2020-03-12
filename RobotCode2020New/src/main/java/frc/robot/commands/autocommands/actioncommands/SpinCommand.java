// This command can be used to make the robot spin in place if need be 
package frc.robot.commands.autocommands.actioncommands;

import frc.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GyroSubsystem;;

public class SpinCommand extends CommandBase {
    private DriveSubsystem driveSubsystem;
    private GyroSubsystem gyroSubsystem;
    private double angle;
    
    public SpinCommand(DriveSubsystem dSubsystem,GyroSubsystem gSubsystem, double Angle) {
        driveSubsystem = dSubsystem;
        gyroSubsystem = gSubsystem;
        angle = Angle;
    }

    @Override
    public void execute() {
        // this will see if the robots current angle is less then the given positive angle if so it will turn until it reaches the angle
        if (gyroSubsystem.getYaw() < angle && angle == Math.abs(angle)) {
            driveSubsystem.tankDriveVolts(-2.0, 2.0);
        }

        // if we go past the given positive angle it will go back slowly
        else if (gyroSubsystem.getYaw() > angle + 1.5 && angle == Math.abs(angle)){
            driveSubsystem.tankDriveVolts(1.0, -1.0);
        }

        // this will see if the robots current angle is greater then the given negitive angle if so it will turn until it reaches the angle
        else if (gyroSubsystem.getYaw() > angle && angle != Math.abs(angle)){
            driveSubsystem.tankDriveVolts(2.0, -2.0);
        }

        // if we go past the given negitive angle it will go back slowly
        else if (gyroSubsystem.getYaw() < angle - 1.5 && angle != Math.abs(angle)){
            driveSubsystem.tankDriveVolts(-1.0, 1.0);
        }
    }
}