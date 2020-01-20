/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ExampleSubsystem;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.ColorShim;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class GetColorCommand extends CommandBase {

  //-------- CONSTANTS --------\\
  private final Color kBlueTarget = new ColorShim(0.143, 0.427, 0.429);
  private final Color kGreenTarget = new ColorShim(0.197, 0.561, 0.240);
  private final Color kRedTarget = new ColorShim(0.561, 0.232, 0.114);
  private final Color kYellowTarget = new ColorShim(0.361, 0.524, 0.113);
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ColorSensor m_subsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public GetColorCommand(ColorSensor subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // This method will be called once per scheduler run

    /*
      // Returns the color values from the sensors
      color = m_subsystem.getColor();
      String colorString = colorResult(color);

      // Outputs the color returned from the sensor
      if (lastColor == null || !colorString.equals(lastColor)){
        System.out.println("matching color:" + colorString);
        lastColor = colorString;
      }
      //System.out.println("matching color:" + colorResult(color));
      */
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

  private String getNearestColor(Color c) {
    // Matches the color sensor RGB values to the closest true color inside of the ColorMatch class
    // Creates a ColorMatch object that finds and manages distance/difference between colors

    ColorMatch colorMatch = new ColorMatch();
    ColorMatchResult match = colorMatch.matchClosestColor(c);

    // Checks the values and matches it to one of the colors below
    if (match.color == kBlueTarget) {
      return "Blue";
    } else if (match.color == kRedTarget) {
      return "Red";
    } else if (match.color == kGreenTarget) {
      return "Green";
    } else if (match.color == kYellowTarget) {
      return "Yellow";
    } else {
      return "Unknown";
    }
  }
}
