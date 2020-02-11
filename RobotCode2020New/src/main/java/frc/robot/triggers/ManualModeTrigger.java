/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.triggers;
import edu.wpi.first.wpilibj2.command.button.Trigger;

//-------- TRIGGER CLASS --------\\

public class ManualModeTrigger extends Trigger {

  //--------- CONSTANTS --------\\

  // -------- DECLARATIONS --------\\

  public static boolean IN_MANUAL_MODE = false;

  //-------- METHODS --------\\

  public ManualModeTrigger() {
  }

  /**
   * Gets the value of the joystick button.
   *
   * @return The value of the IN_MANUAL_MODE button
   */
  @Override
  public boolean get() {
    return IN_MANUAL_MODE;
  }

} // end of class ManualModeTrigger

