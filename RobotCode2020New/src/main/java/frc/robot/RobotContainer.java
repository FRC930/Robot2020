/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

//-------- SUBSYSTEM IMPORT --------\\
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  //-------- CONSTANTS --------\\

   //--Button Mapping        //Refer to http://team358.org/files/programming/ControlSystem2009-/XBoxControlMapping.jpg
   private final int AXIS_LEFT_X = 0;
   private final int AXIS_LEFT_Y = 1;
   private final int AXIS_RIGHT_X = 4;
   private final int AXIS_RIGHT_Y = 5;
   private final int AXIS_LT = 2;
   private final int AXIS_RT = 3;

   //--Ports
   private final int CODRIVER_CONTROLLER_ID = 1;
   private final int DRIVER_CONTROLLER_ID = 0;

   //--Deadbands
   private final double TRIGGER_PRESSED_THRESHOLD = 0.4;

  //-------- DECLARATIONS --------\\

    private Joystick driver;
    private Joystick coDriver;

    private AddressableLED m_leds = new AddressableLED(0);
    private AddressableLEDBuffer m_ledsBuffer = new AddressableLEDBuffer(50);

  //-------- SUBSYSTEMS --------\\

    //  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
    
    private final ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();
    
    private final LEDSubsystem m_LedSubsystem = new LEDSubsystem(m_leds, m_ledsBuffer);

  //-------- COMMANDS --------\\

    //  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  //-------- CONSTRUCTOR ---------\\

  public RobotContainer() {

    driver = new Joystick(DRIVER_CONTROLLER_ID);
    coDriver = new Joystick(CODRIVER_CONTROLLER_ID);

    // Configure the button bindings
    configureButtonBindings();

  }

  //-------- METHODS --------\\

  //Refer to https://docs.google.com/document/d/1V3UP8MBADUFDnNZTIlefdBUDyUZ-zYfYCRs3ykREHns/edit?usp=sharing
  private void configureButtonBindings() {
    
    // driver buttons and assignments
    final JoystickButton a_buttonDriver = new JoystickButton(driver, 1);
    final JoystickButton b_buttonDriver = new JoystickButton(driver, 2);
    final JoystickButton x_buttonDriver = new JoystickButton(driver, 3);
    final JoystickButton y_buttonDriver = new JoystickButton(driver, 4);
    final JoystickButton lb_buttonDriver = new JoystickButton(driver, 5);
    final JoystickButton rb_buttonDriver = new JoystickButton(driver, 6);
    final JoystickButton back_buttonDriver = new JoystickButton(driver, 7);
    final JoystickButton start_buttonDriver = new JoystickButton(driver, 8);

    a_buttonDriver.whenPressed(new RunShooterCommand(m_ShooterSubsystem, 0.7));
    b_buttonDriver.whenPressed(new StopShooter(m_ShooterSubsystem));

    // codriver buttons and assignments
    final JoystickButton a_buttonCoDriver = new JoystickButton(driver, 1);
    final JoystickButton b_buttonCoDriver = new JoystickButton(driver, 2);
    final JoystickButton x_buttonCoDriver = new JoystickButton(driver, 3);
    final JoystickButton y_buttonCoDriver = new JoystickButton(driver, 4);
    final JoystickButton lb_buttonCoDriver = new JoystickButton(driver, 5);
    final JoystickButton rb_buttonCoDriver = new JoystickButton(driver, 6);
    final JoystickButton back_buttonCoDriver = new JoystickButton(driver, 7);
    final JoystickButton start_buttonCoDriver = new JoystickButton(driver, 8);
    

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;//m_autoCommand;
  }
}
