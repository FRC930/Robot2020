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
// public class LEDIdleFlash extends SequentialCommandGroup {
//     private LEDSubsystem m_ledSubsystem;
//     private AddressableLED m_leds;
//     private AddressableLEDBuffer m_ledsBuffer;

//     static private int flashType;

//     public LEDIdleFlash(LEDSubsystem ledSubsystem, AddressableLED leds, AddressableLEDBuffer ledsBuffer, int flashType)
//     {
//         m_ledSubsystem = ledSubsystem;
//         addRequirements(m_ledSubsystem);

//         m_leds = leds;
//         m_ledsBuffer = ledsBuffer;
//         this.flashType = flashType;
//     }

//     // Called when the command is initially scheduled.
//     @Override
//     public void initialize() 
//     {

//     }

//     // Called every time the scheduler runs while the command is scheduled.
//     @Override
//     public void execute() 
//     {
//         switch(flashType){
//             case 0:
//                 for(int i = 0; i < m_ledsBuffer.getLength(); i++)
//                 {
//                     m_ledsBuffer.setRGB(i, 255, 150, 0);
//                     //addCommands(new WaitCommand(1));
//                     m_leds.setData(m_ledsBuffer);
//                 }
//                 SmartDashboard.putNumber("ledSet", 0);
//                 flashType = 1;
//             break;
//             case 1:
//                 for(int i = 0; i < m_ledsBuffer.getLength(); i++)
//                 {
//                     m_ledsBuffer.setRGB(i, 0, 0, 0);
//                     //addCommands(new WaitCommand(1));
//                     m_leds.setData(m_ledsBuffer);
//                 }
//                 SmartDashboard.putNumber("ledSet", 1);
//                 flashType = 0;
//             break;
//             default:
//                 for(int i = 0; i < m_ledsBuffer.getLength(); i++)
//                 {
//                     m_ledsBuffer.setRGB(i, 0, 0, 0);
//                 }
//                 m_leds.setData(m_ledsBuffer);
//                 SmartDashboard.putNumber("ledSet", -1);
//             break;
//         }
//     }

//     // Called once the command ends or is interrupted.
//     @Override
//     public void end(boolean interrupted) 
//     {

//     }

//     // Returns true when the command should end.
//     @Override
//     public boolean isFinished() {
//         return true;
//     }
// }
