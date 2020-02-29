package frc.robot.commands.compressorcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CompresserSubsystem;

public class CompressorOnCommand extends CommandBase {

    private CompresserSubsystem compressorSubsystem;

    public CompressorOnCommand(CompresserSubsystem cSubsystem) {
        compressorSubsystem = cSubsystem;
        addRequirements(cSubsystem);  // Use addRequirements() here to declare subsystem dependencies.
    } // End of Constructor

    @Override   // Called every time the scheduler runs while the command is scheduled.
    public void execute() { 
        compressorSubsystem.turnCompressorOn();
    } // End of execute() method
  
    @Override   // Returns true when the command should end.
    public boolean isFinished() {
        return true;
    } // End of isFinished() method

} // End of Class