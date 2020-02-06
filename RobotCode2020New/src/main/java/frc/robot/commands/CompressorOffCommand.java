package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Compresser;

public class CompressorOffCommand extends CommandBase {
    private Compresser compressor;
    public CompressorOffCommand(Compresser iCompressor) {
        compressor = iCompressor;
        addRequirements(iCompressor);  // Use addRequirements() here to declare subsystem dependencies.
    }

    @Override   // Called when the command is initially scheduled.
    public void initialize() {
    }

    @Override   // Called every time the scheduler runs while the command is scheduled.
    public void execute() { 
        compressor.turnCompressorOff();
    }

  
    @Override   // Called once the command ends or is interrupted.
    public void end(boolean interrupted) {
    }

  
    @Override   // Returns true when the command should end.
    public boolean isFinished() {
        return true;
    }

}