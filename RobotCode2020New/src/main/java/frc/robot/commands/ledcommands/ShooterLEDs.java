/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ledcommands;

import frc.robot.subsystems.LEDSubsystem;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ShooterLEDs extends CommandBase {
  private final LEDSubsystem m_LEDSubsystem;
  private AddressableLEDBuffer buffer;
  private long counter;
  private int index1;
  private int index2;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterLEDs(LEDSubsystem LEDSubsystem) {
    m_LEDSubsystem = LEDSubsystem;
    buffer = new AddressableLEDBuffer(60);
    counter = 0;
    index1 = 0;
    index2 = 19;

    addRequirements(LEDSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // for(int i = 0; i <= index2; i++)
    // {
    // buffer.setRGB(i, 0, 255, 0);
    // }
    // for(int i = index2 + 1; i < buffer.getLength(); i++)
    // {
    // buffer.setRGB(i, 0, 0, 255);
    // }

    for (int i = 0; i < buffer.getLength(); i++) {
      buffer.setRGB(i, 0, 0, 255);
    }
    m_LEDSubsystem.updateBuffer(buffer);
  }

  private void moveLEDs() {
    index1++;
    index2++;

    if (index2 >= buffer.getLength()) {
      index2 = 0;
    }
    if (index1 >= buffer.getLength()) {
      index1 = 0;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    counter++;

    // Keeping track of animation speed.
    if (counter % 3 == 0) {
      buffer.setRGB(index1, 0, 0, 255);
      moveLEDs();
      buffer.setRGB(index2, 0, 255, 0);
      m_LEDSubsystem.updateBuffer(buffer);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
