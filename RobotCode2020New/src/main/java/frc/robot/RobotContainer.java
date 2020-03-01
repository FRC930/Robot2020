//-------- IMPORTS --------\\

package frc.robot;

// --Library Commands
import edu.wpi.first.wpilibj2.command.*;

// --Our Commands
import frc.robot.commands.autocommands.paths.*;

import frc.robot.commands.colorwheelcommands.*;
import frc.robot.commands.colorwheelcommands.rotationalcontrolcommands.*;
import frc.robot.commands.colorwheelcommands.positionalcontrolcommands.*;

import frc.robot.commands.compressorcommands.*;

import frc.robot.commands.drivecommands.*;

import frc.robot.commands.hoppercommands.*;

import frc.robot.commands.intakecommands.*;
import frc.robot.commands.intakecommands.intakemotorcommands.*;
import frc.robot.commands.intakecommands.intakepistoncommands.*;

import frc.robot.commands.kickercommands.*;
import frc.robot.commands.ledcommands.RunWithAutonLEDs;
//import frc.robot.commands.ledcommands.*;
import frc.robot.commands.limelightcommands.*;
import frc.robot.commands.shootercommands.ShootPowerCellCommandGroup;
import frc.robot.commands.shootercommands.flywheelcommands.*;
import frc.robot.commands.shootercommands.pistoncommands.*;
import frc.robot.commands.shootercommands.StopTowerKickerCommandGroup;

import frc.robot.commands.towercommands.*;

import frc.robot.commands.turretcommads.*;

import frc.robot.commands.endgamecommands.RetractArmCommand;
import frc.robot.commands.endgamecommands.ToggleShiftCommand;
import frc.robot.commands.endgamecommands.StopArmCommand;
import frc.robot.commands.endgamecommands.ExtendArmCommand;

// --Subsystem imports
import frc.robot.subsystems.*;

// --Trigger imports
import frc.robot.triggers.*;

// --Utility imports
import frc.robot.utilities.*;

import java.util.logging.Logger;
import java.util.logging.Level;

// --Other imports
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

//-------- CLASS RobotContainer --------\\

public class RobotContainer {

  // -------- CONSTANTS --------\\

  // --Gamecube button map
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
  
  // --XBox button map
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

  public static final int XB_POV_UP = 0;
  public static final int XB_POV_DOWN = 180;
  public static final int XB_POV_LEFT = 270;
  public static final int XB_POV_RIGHT = 90;

  // --Ports of controllers
  private final int DRIVER_CONTROLLER_ID = 0; // The gamecube controller
  private final int CODRIVER_CONTROLLER_ID = 1; // The xbox controller

  // -------- DECLARATIONS --------\\
  private static final Logger frcRobotLogger = Logger.getLogger(RobotContainer.class.getPackageName());

  //-------- DECLARATIONS --------\\

  private static boolean usingGamecube = true; // Default, this should be true
  private static boolean inManualMode = false; // Default, this should be false
  private Joystick driverController;
  private Joystick coDriverController;

  // -- Inline Class for Manual Mode Trigger
  private class ManualModeTrigger extends Trigger {
    public boolean get() {
      shuffleboardUtility.setManualMode(inManualMode);
      return inManualMode;
    }
  }

  // -------- SUBSYSTEMS --------\\

  //--Endgame subsystem
  private final ClimberArmSubsystem climberArmSubsystem;

  // --Color wheel stuff subsystems
  private final ColorSensorSubsystem colorSensorSubsystem;
  private final ColorWheelSpinnerSubsystem colorWheelSpinnerSubsystem;

  // --Compressor subsystem
  private final CompresserSubsystem compressorSubsystem;

  // --Drive subsystem
  private final DriveSubsystem driveSubsystem;

  // --Shooter stuff subsystems
  private final FlywheelSubsystem flywheelSubsystem;

  // --Gyro subsystem
  private final GyroSubsystem gyroSubsystem;

  // --Hopper subsystem
  private final HopperSubsystem hopperSubsystem;

  // --Intake stuff subsystems
  private final IntakeMotorSubsystem intakeMotorSubsystem;
  private final IntakePistonSubsystem intakePistonSubsystem;

  // --Kicker subsystem
  private final KickerSubsystem kickerSubsystem;

  // --LED subsystems
  // private final LEDSubsystem ledSubsystem;

  // --Limelight subsystem
  private final LimelightSubsystem limelightSubsystem;

  // --Flywheel Angle Subsystem
  private final FlywheelPistonSubsystem flywheelPistonSubsystem;

  // --Tower subsystem
  private final TowerSubsystem towerSubsystem;

  // --Turret subsystem
  private final TurretSubsystem turretSubsystem;

  //--LED subsystem
  LEDSubsystem ledSubsystem = new LEDSubsystem();

  //-------- COMMANDS --------\\

  // --Auton command
  //TODO: Change this to accept any auton path from the shuffleboard
  //private final BigCountrySkilletCommand bigCountrySkilletCommand;
  //private final CaliAvocadoSkilletCommand caliAvocadoSkilletCommand;
  // private final CheesyDenverSkilletCommand cheesyDenverSkilletCommand;
  // private final EverythingSkilletCommand everythingSkilletCommand;
  // private final FarmersBreakfastSkilletCommand farmersBreakfastSkilletCommand;
  // private final GypsySkilletCommand gypsySkilletCommand;
  // private final GyroSkilletCommand gyroSkilletCommand;
  // private final LoadedSkilletCommand loadedSkilletCommand;
  // private final PeachtreeSkilletCommand peachtreeSkilletCommand;
  private final PhillyCheesesteakAndEggSkilletCommand phillyCheesesteakAndEggSkilletCommand;
  //private final SaltAndPepperSkilletCommand saltAndPepperSkilletCommand;
  // private final SouthBySouthWestSkilletCommand southBySouthWestSkilletCommand;
  // private final SpinachAndMushroomSkilletCommand spinachAndMushroomSkilletCommand;
  // private final VeggieSkilletCommand veggieSkilletCommand;

  // --Color wheel commands
  private final RotationalControlCommandGroup rotationalControlCommandGroup;
  private final ColorWheelSpinnerCommand colorWheelSpinnerCommand;

  // --Compressor commands
  private final CompressorOnCommand compressorOnCommand;
  private final CompressorOffCommand compressorOffCommand;

  // --Drive commands
  private final DriveCommand driveCommand;

  // --Endgame commands
  private final ExtendArmCommand extendArmCommand;
  private final RetractArmCommand retractArmCommand;
  private final StopArmCommand stopArmCommand;
  private final ToggleShiftCommand toggleShiftCommand;
  private final LimelightLEDsOnCommand limelightLEDsOnCommand;
  private final LimelightLEDsOffCommand limelightLEDsOffCommand;
  
  // --Hopper commands
  // private final StopHopperCommand stopHopperCommand;
  private final DefaultHopperCommand defaultHopperCommand;
  private final KillHopperStateCommand killHopperStateCommand;

  // --Intake commands
  private final DeployIntakeCommand deployIntakeCommand;
  private final ReturnIntakeCommand returnIntakeCommand;

  // --Kicker commands
  private final RunKickerCommand runKickerCommand;
  private final StopKickerCommand stopKickerCommand;

  // --LED commands
  // TODO: Add LED commands here

  // --Shooter commands
  // --Flywheel commands
  private final DefaultFlywheelCommand defaultFlywheelCommand;
  private final RunFlywheelCommand runFlywheelCommand; // For manual mode
  private final StopFlywheelCommand stopFlywheelCommand; // For manual mode
  private final RampFlywheelCommand rampFlywheelCommand;

  // --Flywheel piston commands
  private final RetractFlywheelPistonCommand retractFlywheelPistonCommand;
  private final ExtendFlywheelPistonCommand extendFlywheelPistonCommand;

  // --Tower commands
  private final RunTowerCommand runTowerCommand;
  private final StopTowerCommand stopTowerCommand;

  // --Turret commands
  private final AutoAimTurretCommand autoAimTurretCommand;
  private final JoystickTurretCommand joystickTurretCommand;  // For manual
  private final Logger logger = Logger.getLogger(RobotContainer.class.getName());;
  // --Utilities
  private final ShuffleboardUtility shuffleboardUtility;

  
  // -------- CONSTRUCTOR ---------\\

  public RobotContainer() {

    //Setting Log level for entire robot code
    //TODO: Edit this in Shuffleboard...?
    frcRobotLogger.setLevel(Level.OFF);

    // --Drive controllers
    driverController = new Joystick(DRIVER_CONTROLLER_ID);
    coDriverController = new Joystick(CODRIVER_CONTROLLER_ID);
    //Solenoid s = new Solenoid(2);
    //s.set(true);
    // --Subsystems
    colorSensorSubsystem = new ColorSensorSubsystem();
    colorWheelSpinnerSubsystem = new ColorWheelSpinnerSubsystem();

    compressorSubsystem = new CompresserSubsystem();

    driveSubsystem = new DriveSubsystem();

    gyroSubsystem = new GyroSubsystem();

    hopperSubsystem = new HopperSubsystem();

    intakeMotorSubsystem = new IntakeMotorSubsystem();
    intakePistonSubsystem = new IntakePistonSubsystem();

    kickerSubsystem = new KickerSubsystem();

    climberArmSubsystem = new ClimberArmSubsystem();

    // ledSubsystem = new LEDSubsystem();

    limelightSubsystem = new LimelightSubsystem();

    flywheelSubsystem = new FlywheelSubsystem();
    flywheelPistonSubsystem = new FlywheelPistonSubsystem();

    towerSubsystem = new TowerSubsystem();

    turretSubsystem = new TurretSubsystem();

    // --Commands
    
    // endgame
    extendArmCommand = new ExtendArmCommand(climberArmSubsystem);
    retractArmCommand = new RetractArmCommand(climberArmSubsystem);
    stopArmCommand = new StopArmCommand(climberArmSubsystem);
    toggleShiftCommand = new ToggleShiftCommand(driveSubsystem);
    limelightLEDsOnCommand = new LimelightLEDsOnCommand(limelightSubsystem);
    limelightLEDsOffCommand = new LimelightLEDsOffCommand(limelightSubsystem);

    // intake
    deployIntakeCommand = new DeployIntakeCommand(intakePistonSubsystem, intakeMotorSubsystem);
    returnIntakeCommand = new ReturnIntakeCommand(intakePistonSubsystem, intakeMotorSubsystem);

    // colorwheel
    // TODO: Add color wheel commmands down here
    rotationalControlCommandGroup = new RotationalControlCommandGroup(colorSensorSubsystem, colorWheelSpinnerSubsystem);
    colorWheelSpinnerCommand = new ColorWheelSpinnerCommand(colorWheelSpinnerSubsystem);

    // compressor
    compressorOnCommand = new CompressorOnCommand(compressorSubsystem);
    compressorOffCommand = new CompressorOffCommand(compressorSubsystem);

    // drive (NOTE: This is where we bind the driver controls to the drivetrain)
    driveCommand = new DriveCommand(driveSubsystem, driverController, GC_AXIS_LEFT_X, GC_AXIS_RIGHT_Y);

    // hopper
    killHopperStateCommand = new KillHopperStateCommand();
    defaultHopperCommand = new DefaultHopperCommand(hopperSubsystem, killHopperStateCommand);

    // kicker
    runKickerCommand = new RunKickerCommand(kickerSubsystem);
    stopKickerCommand = new StopKickerCommand(kickerSubsystem);

    // leds
    // TODO: Add LED commands here

    // Flywheel
    defaultFlywheelCommand = new DefaultFlywheelCommand(flywheelSubsystem);
    runFlywheelCommand = new RunFlywheelCommand(flywheelSubsystem);
    stopFlywheelCommand = new StopFlywheelCommand(flywheelSubsystem);
    rampFlywheelCommand = new RampFlywheelCommand(flywheelSubsystem);

    // Flywheel piston
    retractFlywheelPistonCommand = new RetractFlywheelPistonCommand(flywheelPistonSubsystem);
    extendFlywheelPistonCommand = new ExtendFlywheelPistonCommand(flywheelPistonSubsystem);

    // tower
    runTowerCommand = new RunTowerCommand(towerSubsystem);
    stopTowerCommand = new StopTowerCommand(towerSubsystem);

    // turret
    autoAimTurretCommand = new AutoAimTurretCommand(limelightSubsystem, turretSubsystem, new PIDController(Constants.TURRET_P, Constants.TURRET_I, Constants.TURRET_D), coDriverController, XB_AXIS_LEFT_X);
    joystickTurretCommand = new JoystickTurretCommand(turretSubsystem, coDriverController, XB_AXIS_LEFT_X);

    // auto 
    //TODO: Change this to get the Shuffleboard selected command
    //caliAvocadoSkilletCommand = new CaliAvocadoSkilletCommand(driveSubsystem,deployIntakeCommand,returnIntakeCommand);
    phillyCheesesteakAndEggSkilletCommand = new PhillyCheesesteakAndEggSkilletCommand(driveSubsystem);

    shuffleboardUtility = ShuffleboardUtility.getInstance();
    
    // --Bindings
    configureButtonBindings(); // Configures buttons for drive team

    // --Default commands
    beginRunCommands(); // Sets the default command
  } // end of constructor RobotContainer()

  // -------- METHODS --------\\

  private void configureButtonBindings() {

    SmartDashboard.putBoolean("Safety", inManualMode);

    // This trigger is dedicated to the coDriver. It turns the robot's controls into
    // Manual mode, which is mainly only used for debugging purposes only
    JoystickButton manualModeButton = new JoystickButton(coDriverController, XB_BACK);

    // InstantCommand takes a runnable, which we're sending an anonymous runnable
    // method through
    // This runnable method flips the manual mode and updates shuffleboard
    manualModeButton.whileActiveOnce(new InstantCommand(() -> {
      inManualMode = !inManualMode;
      SmartDashboard.putBoolean("Safety", inManualMode);
      beginRunCommands();
    }));

    configureDriverBindings();
    configureCodriverBindings();
  } // end of method configureButtonBindings()

  private void configureDriverBindings() { // TODO: Bind controls to commands

    ManualModeTrigger inManualModeTrigger = new ManualModeTrigger();

    if (usingGamecube) { // If we're using the gamecube controller

      // ---- BUTTONS AND TRIGGERS (DRIVE) ----\\
      // NOTE: Drive controls are defined in the DriveCommand constructor

      // --Buttons and Triggers

      // A Button
      JoystickButton rotationalButton = new JoystickButton(driverController, GC_A);
      // B Button
      JoystickButton positionalButton = new JoystickButton(driverController, GC_B);
      // L Button
      JoystickButton toggleEndgame = new JoystickButton(driverController, GC_L);
      // ZL Button
      JoystickButton toggleAngle = new JoystickButton(driverController, GC_ZL);
      // ZR Button
      JoystickButton shootButton = new JoystickButton(driverController, GC_ZR);
      // X Button
      JoystickButton liftArm = new JoystickButton (driverController, GC_Y);
      // Y Button 
      JoystickButton lowerArm = new JoystickButton (driverController, GC_X);
      // --Command binds

      // Rotational control command bind
      rotationalButton.whileActiveOnce(rotationalControlCommandGroup);

      // Positional control command bind TODO: Uncomment this when ready for testing
      // positionalButton.whileActiveOnce(positionalControlCommandGroup);

      // Drive command binds
      driveCommand.setTurningAndThrottleAxis(GC_AXIS_RIGHT_X, GC_AXIS_LEFT_Y);

      //Shooter command binds
      shootButton.whileActiveOnce(new ShootPowerCellCommandGroup(flywheelSubsystem, towerSubsystem, hopperSubsystem, kickerSubsystem, limelightSubsystem, flywheelPistonSubsystem, shootButton));
      shootButton.whenReleased(new StopTowerKickerCommandGroup(towerSubsystem, kickerSubsystem));
      //shootButton.whenPressed(new RunFlywheelCommand(flywheelSubsystem, 0.8));
      
      // Endgame command binds

      liftArm.whenPressed(extendArmCommand);
      liftArm.whenReleased(stopArmCommand);

      lowerArm.whenPressed(retractArmCommand);
      lowerArm.whenReleased(stopArmCommand);

      toggleEndgame.toggleWhenActive(toggleShiftCommand);

      // ---- BUTTONS AND TRIGGERS (MANUAL) ----\\

      // --Buttons and Triggers

      // A Button
      Trigger manualColorSpinnerButton = new JoystickButton(driverController, GC_A).and(inManualModeTrigger);
      // B Button
      Trigger manualHopperButton = new JoystickButton(driverController, GC_B);//.and(inManualModeTrigger);
      // X Button
      Trigger manualKickerButton = new JoystickButton(driverController, GC_X).and(inManualModeTrigger);
      // Y Button
      Trigger manualTowerEndgame = new JoystickButton(driverController, GC_Y).and(inManualModeTrigger);
      
      JoystickButton reverseHopperButton = new JoystickButton(coDriverController, XB_B);

      JoystickButton killHopperButton = new JoystickButton(coDriverController, XB_START);

      // ZR Button
      Trigger manualFlywheelButton = new JoystickButton(driverController, GC_ZR).and(inManualModeTrigger);
      // ZL Button
      AxisTrigger manualFlywheelPistonButton = new AxisTrigger(coDriverController, XB_AXIS_LT);// .and(inManualModeTrigger);

      // --Command binds

      // manual color wheel spinner
      manualColorSpinnerButton.whenActive(colorWheelSpinnerCommand);
      // manual hopper spinning
      manualHopperButton.whileActiveOnce(new RunHopperCommand(hopperSubsystem)).whenInactive(new StopHopperCommand(hopperSubsystem,killHopperButton));

      // manual kicker spinning
      manualKickerButton.whenActive(runKickerCommand).whenInactive(stopKickerCommand);
      // manual tower spinning
      manualTowerEndgame.whenActive(runTowerCommand).whenInactive(stopTowerCommand);
      // manual flywheel spinning
      manualFlywheelButton.whenActive(runFlywheelCommand).whenInactive(stopFlywheelCommand);
      // manual flywheel piston stuff
      manualFlywheelPistonButton.whenActive(extendFlywheelPistonCommand).whenInactive(retractFlywheelPistonCommand);

      reverseHopperButton.whileActiveOnce(new StopHopperCommand(hopperSubsystem, reverseHopperButton));
      // manual
      killHopperButton.whileActiveOnce(killHopperStateCommand);
    } else { // If we're using the Xbox controller

    } // end of if statement usingGamecube

  } // end of method configureDriverBindings()

  private void configureCodriverBindings() {

    // --Buttons

    AxisTrigger intakeAxisTrigger = new AxisTrigger(coDriverController, XB_AXIS_RT);

    JoystickButton limelightLEDsOn = new JoystickButton(coDriverController, XB_LB);

    POVTrigger turretFront = new POVTrigger(coDriverController, 0, XB_POV_UP);
    POVTrigger turretBack = new POVTrigger(coDriverController, 0, XB_POV_DOWN);
    POVTrigger turretLeft = new POVTrigger(coDriverController, 0, XB_POV_LEFT);

    // --Command binds
    // limelightLEDsOn.whenPressed(limelightLEDsOnCommand);
    // limelightLEDsOn.whenReleased(limelightLEDsOffCommand);
    // limelightLEDsOn.whenPressed(autoAimTurretCommand);
    // limelightLEDsOn.whenReleased(limelightLEDsOffCommand);

    limelightLEDsOn.whileActiveOnce(autoAimTurretCommand);

    turretFront.whileActiveContinuous(new TurretFrontCommand(turretSubsystem));
    turretBack.whileActiveContinuous(new TurretBackCommand(turretSubsystem));
    turretLeft.whileActiveContinuous(new TurretLeftCommand(turretSubsystem));

    // Toggle intake
    intakeAxisTrigger.toggleWhenActive(deployIntakeCommand).whenInactive(returnIntakeCommand);

  } // end of method configureCodriverBindings()

  private void beginRunCommands() {

    // --The instance of the scheduler
    CommandScheduler scheduler = CommandScheduler.getInstance();

    scheduler.unregisterSubsystem(hopperSubsystem, turretSubsystem, flywheelSubsystem, kickerSubsystem, towerSubsystem);

    if (inManualMode) {
      scheduler.setDefaultCommand(turretSubsystem, joystickTurretCommand); 
    } else { 
      scheduler.setDefaultCommand(turretSubsystem, joystickTurretCommand);
      scheduler.setDefaultCommand(driveSubsystem, driveCommand);
      scheduler.setDefaultCommand(hopperSubsystem, defaultHopperCommand);
      scheduler.setDefaultCommand(flywheelSubsystem, runFlywheelCommand);//defaultFlywheelCommand);
      scheduler.setDefaultCommand(limelightSubsystem, limelightLEDsOffCommand);
    }

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    //return new RunWithAutonLEDs(saltAndPepperSkilletCommand, ledSubsystem);
    //CommandScheduler scheduler = CommandScheduler.getInstance();
    //scheduler.setDefaultCommand(turretSubsystem, );
    //scheduler.setDefaultCommand(hopperSubsystem, defaultHopperCommand);
    //scheduler.setDefaultCommand(flywheelSubsystem, defaultFlywheelCommand);
    return new RunWithAutonLEDs(new InstantCommand(), ledSubsystem);//new SaltAndPepperSkilletCommand(driveSubsystem, deployIntakeCommand, returnIntakeCommand,new ShootPowerCellCommandGroup(flywheelSubsystem, towerSubsystem, hopperSubsystem, kickerSubsystem, limelightSubsystem, flywheelPistonSubsystem));
    //return null;
    // Run path following command, then stop at the end.
  }

  // -------- METHODS FOR SHUFFLEBOARD --------\\

  public static boolean getUsingGamecube() {
    return usingGamecube;
  }

  public static boolean getInManual() {
    return inManualMode;
  }

  public static void setUsingGamecube(boolean using) {
    usingGamecube = using;
  }

  public void StartShuffleBoard() {
    // shuffleboardUtility.putDriveTab();
    // shuffleboardUtility.putTestingTab();
  }

} // end of class RobotContainer