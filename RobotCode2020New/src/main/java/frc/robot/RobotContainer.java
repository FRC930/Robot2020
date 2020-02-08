
//-------- IMPORTS --------\\

package frc.robot;

//--Command imports 
import edu.wpi.first.wpilibj2.command.*;

import frc.robot.commands.autocommands.*;

import frc.robot.commands.colorwheelcommands.rotationalcontrolcommands.*;
import frc.robot.commands.colorwheelcommands.*;
import frc.robot.commands.compressorcommands.*;
import frc.robot.commands.drivecommands.*;
import frc.robot.commands.hoppercommands.*;
import frc.robot.commands.intakecommands.*;
import frc.robot.commands.ledcommands.*;
import frc.robot.commands.shootercommands.*;
import frc.robot.commands.towercommands.*;
import frc.robot.commands.turretcommads.*;

//--Subsystem imports
import frc.robot.subsystems.*;

//--Trigger imports
import frc.robot.triggers.*;  

//--Utility imports
import frc.robot.utilities.*;

//--Other imports
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

//-------- CLASS RobotContainer --------\\

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ColorSensorSubsystem m_colorSensorSubsystem = new ColorSensorSubsystem();
  private final DriveSubsystem m_drive = new DriveSubsystem();

  //-------- CONSTANTS --------\\

  //--Gamecube button map
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

  //--XBox button map
  private final int XB_AXIS_LEFT_X = 0;
  private final int XB_AXIS_LEFT_Y = 1;
  private final int XB_AXIS_RIGHT_X = 4;
  private final int XB_AXIS_RIGHT_Y = 5;
  private final int XB_AXIS_LT = 2;
  private final int XB_AXIS_RT = 3;

  //A button is being used in multiple places, so it is in the constants class
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

  //--Ports of controllers
  private final int DRIVER_CONTROLLER_ID = 0;   //The gamecube controller
  private final int CODRIVER_CONTROLLER_ID = 1; //The xbox controller
  
  //--Deadbands

  //-------- DECLARATIONS --------\\

  private boolean usingGamecube = true;
  private Joystick driverController;
  private Joystick coDriverController;

  //-------- SUBSYSTEMS --------\\

  //--Color wheel subsystem
  private final ColorSensorSubsystem colorSensorSubsystem;
  private final ColorWheelSpinnerSubsystem colorWheelSpinnerSubsystem;

  //--Compressor subsystem
  private final CompresserSubsystem compressorSubsystem;

  //--Drive subsystem
  private final DriveSubsystem driveSubsystem;

  //--Hopper subsystem
  private final HopperSubsystem hopperSubsystem;
 
  //--Intake subsystems
  private final IntakeMotorSubsystem intakeMotors;
  private final IntakePistonSubsystem intakePistons;
  
  //--LED subsystems
  private final LEDSubsystem ledSubsystem;

  //--Limelight subsystem
  private final LimelightSubsystem limelightSubsystem;

  //--Shooter subsystem
  private final ShooterSubsystem shooterSubsystem;

  //--Turret subsystem
  private final TowerSubsystem towerSubsystem;

  //--Turret subsystem
  private final TurretSubsystem turretSubsystem;
  
  //-------- COMMANDS --------\\

  //--Auton commands
  private final AutonomousCommand autoCommand;
  
  //--Color wheel commands
  //TODO: Add color commands here
  private final RotationalControlCommandGroup rotationalControlCommandGroup;
  

  //--Compressor commands
  private final CompressorOnCommand compressorOnCommand;
  private final CompressorOffCommand compressorOffCommand;

  //--Drive commands
  private final DriveCommand driveCommand;

  //--Hopper commands
  private final RunHopperCommand runHopperCommand;
  private final HopperDefaultCommand hopperDefaultCommand;

  //--Intake commands
  private final IntakeCommand intakeCommand;
  private final IntakeStopCommand intakeStopCommand;

  //--LED commands
  //TODO: Add LED commands here

  //--Shooter commands
  //TODO: Add shooting commands here

  //--Tower coommands
  private final RunTowerCommand runTowerCommand;
  private final StopTowerCommand stopTowerCommand;

  //--Turret commands
  private final AimTurretCommand aimTurretCommand;
  private final JoystickButton aButton;
  
  //-------- CONSTRUCTOR ---------\\

  public RobotContainer() {

    //--Drive controllers
    driverController = new Joystick(DRIVER_CONTROLLER_ID);
    coDriverController = new Joystick(CODRIVER_CONTROLLER_ID);
    aButton = new JoystickButton(driverController, XB_A);

    //--Subsystems
    colorSensorSubsystem = new ColorSensorSubsystem();
    colorWheelSpinnerSubsystem = new ColorWheelSpinnerSubsystem();

    compressorSubsystem = new CompresserSubsystem();
    driveSubsystem = new DriveSubsystem();
    hopperSubsystem = new HopperSubsystem();
    intakeMotors = new IntakeMotorSubsystem();
    intakePistons = new IntakePistonSubsystem();
    ledSubsystem = new LEDSubsystem();
    limelightSubsystem = new LimelightSubsystem();
    shooterSubsystem = new ShooterSubsystem();
    towerSubsystem = new TowerSubsystem();
    turretSubsystem = new TurretSubsystem();
    
    //--Commands

    //auto
    autoCommand = new AutonomousCommand(driveSubsystem);

    //colorwheel
    //TODO: Add color wheel commmands down here
    rotationalControlCommandGroup = new RotationalControlCommandGroup(colorSensorSubsystem, colorWheelSpinnerSubsystem, aButton);

    //compressor
    compressorOnCommand = new CompressorOnCommand(compressorSubsystem);
    compressorOffCommand = new CompressorOffCommand(compressorSubsystem);

    //drive
    driveCommand = new DriveCommand(driveSubsystem, driverController,  GC_AXIS_RIGHT_X, GC_AXIS_LEFT_Y);
    
    //hopper
    runHopperCommand = new RunHopperCommand(hopperSubsystem);
    hopperDefaultCommand = new HopperDefaultCommand(hopperSubsystem);

    //intake
    intakeCommand = new IntakeCommand(intakePistons, intakeMotors);
    intakeStopCommand = new IntakeStopCommand(intakePistons, intakeMotors);

    //leds
    //TODO: Add LED commands here

    //shooter
    //TODO: Add shooter commands here

    //tower
    runTowerCommand = new RunTowerCommand(towerSubsystem);
    stopTowerCommand = new StopTowerCommand(towerSubsystem);

    //turret
    aimTurretCommand = new AimTurretCommand(turretSubsystem);   

    //--Bindings
    configureButtonBindings();  //Configures buttons for drive team

    //--Default commands
    beginRunCommands();         //Sets the default command
    
  } //end of constructor RobotContainer()

  //-------- METHODS --------\\

  private void configureButtonBindings() {
    configureDriverBindings(); 
    configureCodriverBindings();
  } //end of method configureButtonBindings()

  private void configureDriverBindings() {    //TODO: Bind controls to commands
    if (usingGamecube) {  //If we're using the gamecube controller

      //--Buttons and triggers

      //B Button
      JoystickButton rotationalButton = new JoystickButton(driverController, GC_A);
      //A Button
      JoystickButton positionalButton = new JoystickButton(driverController, GC_B);
      //L Button
      JoystickButton toggleEndgame = new JoystickButton(driverController, GC_L);
      //ZL Button
      JoystickButton toggleShootButton = new JoystickButton(driverController, GC_ZL);
      //ZR Button
      JoystickButton shootButton = new JoystickButton(driverController, GC_ZR);

      //--Command binds

    } else {  //If we're using the Xbox controller

      //--Buttons and triggers
      AxisTrigger shootButton = new AxisTrigger(driverController, XB_AXIS_RT);

      //--Command binds

    } //end of if statement usingGamecube
    
  } // end of method configureDriverBindings()

  private void configureCodriverBindings() { 
    //--Buttons
    AxisTrigger intakeAxisTrigger = new AxisTrigger(coDriverController, XB_AXIS_RT);

    //--Command binds

    //Toggle intake
    intakeAxisTrigger.toggleWhenActive(intakeCommand).cancelWhenActive(intakeStopCommand);

  } // end of method configureCodriverBindings()
  
  private void beginRunCommands() {

    //--The instance of the scheduler
    CommandScheduler scheduler = CommandScheduler.getInstance();

    //--Setting default commands

    scheduler.setDefaultCommand(turretSubsystem, aimTurretCommand);
    scheduler.setDefaultCommand(driveSubsystem, driveCommand);
    scheduler.setDefaultCommand(hopperSubsystem, hopperDefaultCommand);
  } // end of method beginRunCommands()



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoCommand;
    // Run path following command, then stop at the end.
  }

} //end of class RobotContainer
