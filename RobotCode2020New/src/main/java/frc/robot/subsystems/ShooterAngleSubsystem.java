/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ShooterAngleSubsystem extends SubsystemBase {

  //-------- CONSTANTS --------\\
    private final int SOLENOID_CHANNEL = 0;
   //--Ports

  //-------- DECLARATIONS --------\\
  private Solenoid ShooterPiston;

  private final Logger logger = Logger.getLogger(ShooterAngleSubsystem.class.getName());

  
  public enum SolenoidValues {
    
    EXTENDED(true), RETRACTED(false);

    private final boolean solenoidState;

    private SolenoidValues(boolean solenoidState) {
      this.solenoidState = solenoidState;
    }

    public boolean getSolenoidState() {
      return this.solenoidState;
    }
  }

  //-------- CONSTRUCTOR --------\\

  public ShooterAngleSubsystem(){
    ShooterPiston = new Solenoid(SOLENOID_CHANNEL);
  }
    //-------- METHODS --------\\ 
    //Sets the value of the piston   
    public void set(boolean state){
    ShooterPiston.set(state);
    this.logger.log(Level.FINE,"setPistonValue");
  }
  //returns the value of the piston, TRUE = On or up, FALSE = Off or down
  public boolean get(){
    this.logger.log(Level.FINE,"getPistonValue: " + (ShooterPiston.get() ? "True" : "False"));
    return ShooterPiston.get();
  }

  @Override
  public void periodic() {
  }
}//end of class ShooterAngleSubsytem