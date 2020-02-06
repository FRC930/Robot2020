/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.*;
import frc.robot.commands.hoppercommands.HopperDefaultCommand;
import frc.robot.commands.shootercommands.HopperTurretCommand;
import frc.robot.commands.turretcommads.AutoAimTurretCommand;
import frc.robot.subsystems.*;
import frc.robot.triggers.*;  
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.GenericHID;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
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

  //-------- DECLARATIONS --------\\

  private boolean usingGamecube = true;
  private Joystick driverController;
  private Joystick coDriverController;

  //-------- SUBSYSTEMS --------\\

  private final ColorSensorSubsystem colorSensorSubsystem;
  private final DriveSubsystem driveSubsystem;
  private final IntakeSubsystem intakeSubsystem;
  //private final LEDSubsystem ledSubsystem;
  private final LimelightSubsystem limelightSubsystem;
  private final ShooterSubsystem shooterSubsystem;
  private final TurretSubsystem turretSubsystem;
  
  //-------- COMMANDS --------\\

  private final AimTurretCommand aimTurretCommand;
  private final DriveCommand driveCommand;
  private final AutonomousCommand autoCommand;
  
  //-------- CONSTRUCTOR ---------\\

  public RobotContainer() {

    //--Controllers
    driverController = new Joystick(DRIVER_CONTROLLER_ID);
    coDriverController = new Joystick(CODRIVER_CONTROLLER_ID);

    //--Subsystems
    colorSensorSubsystem = new ColorSensorSubsystem();
    driveSubsystem = new DriveSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    //ledSubsystem = new LEDSubsystem(m_leds, m_ledsBuffer);
    limelightSubsystem = new LimelightSubsystem();
    shooterSubsystem = new ShooterSubsystem();
    turretSubsystem = new TurretSubsystem();
    
    //--Commands
    driveCommand = new DriveCommand(driveSubsystem, driverController);
    autoCommand = new AutonomousCommand(driveSubsystem);
    aimTurretCommand = new AimTurretCommand(turretSubsystem);   

    //--Configure button bindings
    beginRunCommands();         //Sets the default command
    configureButtonBindings();  //Configures buttons for drive team
  }


  //-------- METHODS --------\\

  private void configureButtonBindings() {
    configureDriverBindings();
    configureCodriverBindings();
  }

  private void configureDriverBindings() {    //TODO: Bind controls to commands
    if (usingGamecube) {  //If we're using the gamecube controller
      //--Buttons and triggers
      JoystickButton shootButton = new JoystickButton(driverController, GC_ZR);

      //--Command binds
    } else {  //If we're using the Xbox controller
      //--Buttons and triggers
      AxisTrigger shootButton = new AxisTrigger(driverController, XB_AXIS_RT);

      //--Command binds
    }
    
  } // end of method configureDriverBindings()

  private void configureCodriverBindings() { 
    //--Buttons
    AxisTrigger intakeButton = new AxisTrigger(coDriverController, XB_AXIS_RT);

    //--Command binds
    //intakeButton.whenPressed(intake::run);
  } // end of method configureCodriverBindings()
  
  private void beginRunCommands() {
    //--The instance of the scheduler
    CommandScheduler scheduler = CommandScheduler.getInstance();

    //--Setting default commands
    scheduler.setDefaultCommand(turretSubsystem, aimTurretCommand);
    scheduler.setDefaultCommand(driveSubsystem, driveCommand);
  } // end of method beginRunCommands()

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
