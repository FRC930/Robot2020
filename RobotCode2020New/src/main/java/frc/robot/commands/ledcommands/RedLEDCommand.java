// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.commands.ledcommands;

// import edu.wpi.first.wpilibj.AddressableLED;
// import edu.wpi.first.wpilibj.AddressableLEDBuffer;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.util.Color;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import edu.wpi.first.wpilibj2.command.WaitCommand;
// import edu.wpi.first.wpilibj.Timer;
// import frc.robot.subsystems.LEDSubsystem;

// /**
//  * An example command that uses an example subsystem.
//  */
// public class RedLEDCommand extends CommandBase {
//     private LEDSubsystem m_ledSubsystem;
//     private AddressableLED m_leds;
//     private AddressableLEDBuffer m_ledsBuffer;

//     static private int flashType;

//     private int counter;

//     public RedLEDCommand(LEDSubsystem ledSubsystem)
//     {
//         m_ledSubsystem = ledSubsystem;
//         addRequirements(m_ledSubsystem);
//     }

//     // Called when the command is initially scheduled.
//     @Override
//     public void initialize() 
//     {
//         m_ledSubsystem.setLEDs(255, 0, 0);
//         counter = 0;
//     }

//     // Called every time the scheduler runs while the command is scheduled.
//     @Override
//     public void execute() 
//     {
//         counter++;
//     }

//     // Called once the command ends or is interrupted.
//     @Override
//     public void end(boolean interrupted) 
//     {
        
//     }

//     // Returns true when the command should end.
//     @Override
//     public boolean isFinished() {
//         return counter > 200;
//     }
// }
