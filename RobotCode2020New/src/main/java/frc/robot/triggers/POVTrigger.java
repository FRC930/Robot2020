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

public class POVTrigger extends Trigger {

  // -------- DECLARATIONS --------\\

  private Joystick joystick;
  private int povAxis;
  private int povNumber;

  //-------- CONSTRUCTOR --------\\

  /**
   * Creates a joystick button for triggering commands.
   *
   * @param joystick     The Joystick object that has an axis
   * 
   * @param povNumber The direction number (see {@link joystick#getRawAxis(int) }
   */
  public POVTrigger(Joystick joystick, int povAxis, int povNumber) {
    requireNonNullParam(joystick, "joystick", "JoystickButton");

    this.joystick = joystick;
    this.povAxis = povAxis;
    this.povNumber = povNumber;
  }

  //-------- METHODS --------\\

  /**
   * Gets the value of the joystick button.
   *
   * @return The value of the joystick button
   */
  @Override
  public boolean get() {
    return (this.joystick.getPOV(this.povAxis) == povNumber);
  }

} // end of class AxisTrigger