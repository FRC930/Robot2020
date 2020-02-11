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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//-------- TRIGGER CLASS --------\\

public class ManualModeTrigger extends Trigger {

  //--------- CONSTANTS --------\\

  // -------- DECLARATIONS --------\\

  private final Joystick joystick;
  private final int buttonNumber;
  private static boolean inManualMode = false;

  //-------- METHODS --------\\

  /**
   * Creates a joystick axis for triggering commands.
   *
   * @param joystick     The Joystick object that has an axis
   * 
   * @param buttonNumber The button number (see {@link joystick#getRawButton(int) }
   */
  public ManualModeTrigger(Joystick joystick, int buttonNumber) {
    requireNonNullParam(joystick, "joystick", "JoystickButton");  //Requires a joystick object, or else it fails

    this.joystick = joystick;
    this.buttonNumber = buttonNumber;
  }

  /**
   * Gets the value of the joystick button.
   *
   * @return The value of the joystick button
   */
  @Override
  public boolean get() {

    boolean buttonDown = this.joystick.getRawButton(buttonNumber);

    SmartDashboard.putBoolean("Safety", inManualMode);
    if (buttonDown && !inManualMode) {
      inManualMode = true;
    } else if (buttonDown && inManualMode) {
      inManualMode = false;
    }

    System.out.println("inManualMode: " + inManualMode);
    return inManualMode;
  }

} // end of class ManualModeTrigger

