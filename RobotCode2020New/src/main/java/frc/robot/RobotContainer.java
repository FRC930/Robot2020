/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.*;
//import frc.robot.commands.DriveCommand;
//import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.intakecommands.*;
import frc.robot.commands.CompressorOnCommand;
import frc.robot.commands.CompressorOffCommand;
//import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeMotorSubsystem;
import frc.robot.subsystems.IntakePistonSubsystem;
import frc.robot.subsystems.Compresser;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
import frc.robot.commands.autocommands.*;
import frc.robot.commands.colorwheelcommands.*;
import frc.robot.commands.drivecommands.*;
import frc.robot.commands.intakecommands.*;
import frc.robot.commands.ledcommands.*;
import frc.robot.commands.shootercommands.*;
import frc.robot.commands.turretcommads.*;

import frc.robot.subsystems.*;
import frc.robot.triggers.*;  

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class RobotContainer {

  //-------- CONSTANTS --------\\

  //----Gamecube button map----\\
  private final int GC_Y = 1; 
  private final int GC_B = 2; 
  private final int GC_A = 3; 
  private final int GC_X = 4; 
  private final int GC_L = 5; 
  private final int GC_R = 6; 
  private final int GC_ZL = 7; 
  private final int GC_ZR = 8; 
  private final int GC_MINUS = 9; 
  private final int GC_PLUS = 10; 
  private final int GC_GRAYSTICK_BUTTON = 11; 
  private final int GC_CSTICK_BUTTON = 12; 
  private final int GC_HOME = 13; 
  private final int GC_CAPTURE = 14; 

  private final int GC_AXIS_LEFT_X = 0;
  private final int GC_AXIS_LEFT_Y = 1;
  private final int GC_AXIS_RIGHT_X = 2;
  private final int GC_AXIS_RIGHT_Y = 3; 

  //----XBox button map ----\\
  private final int XB_AXIS_LEFT_X = 0;
  private final int XB_AXIS_LEFT_Y = 1;
  private final int XB_AXIS_RIGHT_X = 4;
  private final int XB_AXIS_RIGHT_Y = 5;
  private final int XB_AXIS_LT = 2;
  private final int XB_AXIS_RT = 3;

  public static final int XB_A = 1;
  public static final int XB_B = 2;
  public static final int XB_X = 3;
  public static final int XB_Y = 4;
  public static final int XB_LB = 5;
  public static final int XB_RB = 6;
  public static final int XB_BACK = 7;
  public static final int XB_START = 8;
  public static final int XB_LEFTSTICK_BUTTON = 9;
  public static final int XB_RIGHTSTICK_BUTTON = 10;

  //----Ports of controllers
  private final int DRIVER_CONTROLLER_ID = 0;   //The gamecube controller
  private final int CODRIVER_CONTROLLER_ID = 1; //The xbox controller
  
  // --Deadbands

  // -------- DECLARATIONS --------\\

  private Joystick driverController;
  private Joystick coDriverController;

  // -------- SUBSYSTEMS --------\\

  //private final DriveSubsystem driveSubsystem;
  private final IntakePistonSubsystem intakePistons;
  private final IntakeMotorSubsystem intakeMotors;
  private final Compresser compressor;
  private final ShooterSubsystem shooterSubsystem;
  //private final LEDSubsystem ledSubsystem;
  private final TurretSubsystem turretSubsystem;
  private final ColorSensorSubsystem colorSensorSubsystem;
  private final DriveSubsystem driveSubsystem;

  // -------- COMMANDS --------\\

  //private final DriveCommand driveCommand;
  //private final AutonomousCommand autoCommand;
  private final IntakeCommand intakeCommand;
  private final CompressorOnCommand compressorOnCommand;
  private final CompressorOffCommand compressorOffCommand;
  private final IntakeStopCommand intakeStopCommand;
  private final AimTurretCommand aimTurretCommand;
  private final DriveCommand driveCommand;
  //private final AutonomousCommand autoCommand;
  private Joystick driverJoystick;
  private Joystick coDriverJoystick;
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  private final AutonomousCommand autoCommand;
  
  //-------- CONSTRUCTOR ---------\\

  public RobotContainer() {

    //Controllers
    driverJoystick = null; //new Joystick(Constants.DRIVER_CONTROLLER_ID);
    coDriverJoystick = new Joystick(Constants.CODRIVER_CONTROLLER_ID);
  

    //Subsystems
    //driveSubsystem = new DriveSubsystem();
    intakePistons = new IntakePistonSubsystem();
    intakeMotors = new IntakeMotorSubsystem();
    compressor = new Compresser();

    //Commands
    //driveCommand = new DriveCommand(driveSubsystem, driverJoystick);
    //autoCommand = new AutonomousCommand(driveSubsystem);
    intakeCommand = new IntakeCommand(intakePistons, intakeMotors);
    intakeStopCommand = new IntakeStopCommand(intakePistons, intakeMotors);
    compressorOnCommand = new CompressorOnCommand(compressor);
    compressorOffCommand = new CompressorOffCommand(compressor);
    driverController = new Joystick(DRIVER_CONTROLLER_ID);
    coDriverController = new Joystick(CODRIVER_CONTROLLER_ID);

    //Subsystems
    driveSubsystem = new DriveSubsystem();
    colorSensorSubsystem = new ColorSensorSubsystem();
    turretSubsystem = new TurretSubsystem();
    shooterSubsystem = new ShooterSubsystem();
    //ledSubsystem = new LEDSubsystem(m_leds, m_ledsBuffer);

    //Commands
    driveCommand = new DriveCommand(driveSubsystem, driverController);
    autoCommand = new AutonomousCommand(driveSubsystem);
    aimTurretCommand = new AimTurretCommand(turretSubsystem);   

    // Configure the button bindings

    //beginRunCommands();
    configureButtonBindings();
  }


  // -------- METHODS --------\\

  private void configureButtonBindings() {
    beginRunCommands();
      //compressorOnCommand.schedule(true);
      System.out.println("ButtonConfigure");
    JoystickButton intakeButton = new JoystickButton(coDriverJoystick, 1);
    JoystickButton intakeStopButton = new JoystickButton(coDriverJoystick, 2);
    intakeButton.whenPressed(intakeCommand);
    intakeStopButton.whenPressed(intakeStopCommand);
    //intakeButton.whenInactive(intakeStopCommand);
  }

  private void configureCodriverBindings() { 

    //--Buttons
    AxisTrigger intakeAxisTrigger = new AxisTrigger(coDriverController, XB_AXIS_RT);

    //--Command binds
    //intakeAxisTrigger.whenActive(intakeCommand).whenInactive(intakeStopCommand);
    intakeAxisTrigger.toggleWhenActive(intakeCommand).cancelWhenActive(intakeStopCommand);
  }
  
  private void beginRunCommands() {
    CommandScheduler.getInstance().setDefaultCommand(turretSubsystem, aimTurretCommand);
    CommandScheduler.getInstance().setDefaultCommand(driveSubsystem, driveCommand);
    System.out.println("beginRunCommands");
    
    configureCodriverBindings();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    System.out.println("Command");
    return autoCommand;

    // Run path following command, then stop at the end.
  }

} //end of class RobotContainer
