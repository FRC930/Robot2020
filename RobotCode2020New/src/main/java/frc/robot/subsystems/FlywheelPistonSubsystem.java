/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;

import java.util.logging.Logger;

import frc.robot.Constants;
import frc.robot.utilities.ShuffleboardUtility;

//--------- SUBSYSTEM CLASS ---------\\

public class FlywheelPistonSubsystem extends SubsystemBase {

  //-------- CONSTANTS --------\\

  private static final Logger logger = Logger.getLogger(FlywheelPistonSubsystem.class.getName());

  //-------- DECLARATIONS --------\\

  private Solenoid FlywheelPiston;
  private ShuffleboardUtility shuffleboardUtility;

  public enum SolenoidValues {

    EXTEND(true), RETRACT(false);

    private final boolean solenoidState;

    private SolenoidValues(boolean solenoidState) {
      this.solenoidState = solenoidState;
    }

    public boolean getSolenoidState() {
      return this.solenoidState;
    }
  }

  // -------- CONSTRUCTOR --------\\

  public FlywheelPistonSubsystem() {
    FlywheelPiston = new Solenoid(Constants.SHOOTER_SOLENOID_ID);
    shuffleboardUtility = ShuffleboardUtility.getInstance();
  }

  //-------- METHODS --------\\ 

  //Sets the value of the piston   
  public void set(SolenoidValues state) {
    FlywheelPiston.set(state.getSolenoidState());
    this.logger.log(Constants.LOG_LEVEL_FINE, "set(" + state + ")");

  }

  //returns the value of the piston, TRUE = On or up, FALSE = Off or down
  public boolean get(){
    this.logger.log(Constants.LOG_LEVEL_FINE, "getPistonValue: " + (FlywheelPiston.get() ? "True" : "False"));
    return FlywheelPiston.get();
  }

  @Override
  public void periodic() {
    shuffleboardUtility.setShooterAngle(get());
  }

} // end of class FlywheelPistonSubsystem