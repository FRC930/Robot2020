/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.triggers;

import static edu.wpi.first.wpilibj.util.ErrorMessages.requireNonNullParam;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.Joystick;

//-------- TRIGGER CLASS --------\\

public class AxisTrigger extends Trigger {

  //--------- CONSTANTS --------\\

  private final double TRIGGER_PRESSED_THRESHOLD = 0.4;

  // -------- DECLARATIONS --------\\

  private Joystick joystick;
  private int axisNumber;

  //-------- CONSTRUCTOR --------\\

  /**
   * Creates a joystick button for triggering commands.
   *
   * @param joystick     The Joystick object that has an axis
   * 
   * @param axisNumber The button number (see {@link joystick#getRawAxis(int) }
   */
  public AxisTrigger(Joystick joystick, int axisNumber) {
    requireNonNullParam(joystick, "joystick", "JoystickButton");

    this.joystick = joystick;
    this.axisNumber = axisNumber;
  }

  //-------- METHODS --------\\

  /**
   * Gets the value of the joystick button.
   *
   * @return The value of the joystick button
   */
  @Override
  public boolean get() {
    return (this.joystick.getRawAxis(this.axisNumber) >= TRIGGER_PRESSED_THRESHOLD);
  }

} // end of class AxisTrigger