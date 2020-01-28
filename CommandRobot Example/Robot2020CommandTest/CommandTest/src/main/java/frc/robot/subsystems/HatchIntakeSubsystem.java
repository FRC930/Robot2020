/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class HatchIntakeSubsystem extends SubsystemBase {

  //Constants.HATCH_SOLENOID_PORT = 0
  private final DoubleSolenoid hatchPiston;

  public HatchIntakeSubsystem() {
    hatchPiston = new DoubleSolenoid(0, 7);
  }

  public void openHatchPistion() {
    hatchPiston.set(Value.kForward);
  }

  public void closeHatchPistion() {
    hatchPiston.set(Value.kReverse);
  }

}
