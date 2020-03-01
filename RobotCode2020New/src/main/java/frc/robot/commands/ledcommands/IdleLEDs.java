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
public class IdleLEDs extends CommandBase {
  private final LEDSubsystem m_LEDSubsystem;
  private AddressableLEDBuffer buffer;
  private int counter;
  private boolean animCheck;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public IdleLEDs(LEDSubsystem LEDSubsystem) {
    m_LEDSubsystem = LEDSubsystem;
    buffer = new AddressableLEDBuffer(60);
    counter = 0;
    animCheck = false;

    addRequirements(LEDSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
	counter++;

    if(counter >= 32)
    {
		m_LEDSubsystem.setLEDs(0, 0, 255);
        if(animCheck == true)
        {
			for(int i = 0; i < buffer.getLength(); i++)
			{
				if(i % 2 == 0)
				{
					buffer.setRGB(i, 255, 255, 0);
				}
			}
			animCheck = false;
		}
		else
		{
			for(int i = 1; i < buffer.getLength(); i += 2)
			{
				//if(i % 4 == 0)
				//{
					buffer.setRGB(i, 255, 255, 0);
				//}
			}
			animCheck = true;
		}
	  
	  counter = 0;
	}

	m_LEDSubsystem.updateBuffer(buffer);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return false;
  }
}
