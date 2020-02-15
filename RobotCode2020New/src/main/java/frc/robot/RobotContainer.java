//-------- IMPORTS --------\\

package frc.robot;

//--Command imports 
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.autocommands.paths.*;

import frc.robot.commands.colorwheelcommands.rotationalcontrolcommands.*;
import frc.robot.commands.colorwheelcommands.*;
import frc.robot.commands.compressorcommands.*;
import frc.robot.commands.drivecommands.*;
import frc.robot.commands.hoppercommands.*;
import frc.robot.commands.intakecommands.*;
import frc.robot.commands.intakecommands.intakemotorcommands.*;
import frc.robot.commands.intakecommands.intakepistoncommands.*;

import frc.robot.commands.kickercommands.*;
//import frc.robot.commands.ledcommands.*;
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
import edu.wpi.first.wpilibj.Joystick;
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

  // A button is being used in multiple places, so it is in the constants class
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

  // --Ports of controllers
  private final int DRIVER_CONTROLLER_ID = 0; // The gamecube controller
  private final int CODRIVER_CONTROLLER_ID = 1; // The xbox controller

  // --Deadbands

  // -------- DECLARATIONS --------\\

  private boolean usingGamecube = true; // Default, this should be true
  private boolean inManualMode = false; // Default, this should be false
  private Joystick driverController;
  private Joystick coDriverController;

  // -------- SUBSYSTEMS --------\\

  // --Color wheel subsystem
  private final ColorSensorSubsystem colorSensorSubsystem;
  private final ColorWheelSpinnerSubsystem colorWheelSpinnerSubsystem;

  // --Compressor subsystem
  private final CompresserSubsystem compressorSubsystem;

  // --Drive subsystem
  private final DriveSubsystem driveSubsystem;

  // --Flywheel piston subsystem
  private final FlywheelPistonSubsystem flywheelPistonSubsystem;

  // --Flywheel subsystem
  private final FlywheelSubsystem flywheelSubsystem;

  // --Gyro subsystem
  private final GyroSubsystem gyroSubsystem;

  // --Hopper subsystem
  private final HopperSubsystem hopperSubsystem;

  // --Intake subsystems
  private final IntakeMotorSubsystem intakeMotorSubsystem;
  private final IntakePistonSubsystem intakePistonSubsystem;

  // --Kicker subsystem
  private final KickerSubsystem kickerSubsystem;

  // --LED subsystems
  // private final LEDSubsystem ledSubsystem;

  // --Limelight subsystem
  private final LimelightSubsystem limelightSubsystem;

  // --Tower subsystem
  private final TowerSubsystem towerSubsystem;

  // --Turret subsystem
  private final TurretSubsystem turretSubsystem;

  // -------- COMMANDS --------\\

  // --Auton commands
  private final SaltAndPepperSkillet saltAndPepperSkillet;
  private final AutonomousCommand autonomousCommand;

  // --Color wheel commands
  // TODO: Add color commands here
  private final RotationalControlCommandGroup rotationalControlCommandGroup;
  private final ColorWheelSpinnerCommand colorWheelSpinnerCommand;

  // --Compressor commands
  private final CompressorOnCommand compressorOnCommand;
  private final CompressorOffCommand compressorOffCommand;

  // --Drive commands
  private final DriveCommand driveCommand;

  // --Hopper commands
  private final RunHopperCommand runHopperCommand;
  private final StopHopperCommand stopHopperCommand;
  private final HopperDefaultCommand hopperDefaultCommand;

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
  private final RunDefaultFlywheel runDefaultFlywheel;
  // For manual mode
  private final RunFlywheelCommand runFlywheelCommand;
  //private final StopFlywheelCommand stopFlywheelCommand;

  // --Flywheel piston commands
  private final RetractFlywheelPistonCommand retractFlywheelPistonCommand;
  private final ExtendFlywheelPistonCommand extendFlywheelPistonCommand;

  // --Tower commands
  private final RunTowerCommand runTowerCommand;
  private final StopTowerCommand stopTowerCommand;

  // --Turret commands
  private final AimTurretCommand aimTurretCommand;
  private final AutoAimTurretCommand autoAimTurretCommand;
  private final JoystickTurret joystickTurret;

  // -------- CONSTRUCTOR ---------\\

  public RobotContainer() {

    // --Drive controllers
    driverController = new Joystick(DRIVER_CONTROLLER_ID);
    coDriverController = new Joystick(CODRIVER_CONTROLLER_ID);

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
    // ledSubsystem = new LEDSubsystem();
    limelightSubsystem = new LimelightSubsystem();
    flywheelSubsystem = new FlywheelSubsystem();
    flywheelPistonSubsystem = new FlywheelPistonSubsystem();
    towerSubsystem = new TowerSubsystem();
    turretSubsystem = new TurretSubsystem();

    // --Commands

    // intake
    deployIntakeCommand = new DeployIntakeCommand(intakePistonSubsystem, intakeMotorSubsystem);
    returnIntakeCommand = new ReturnIntakeCommand(intakePistonSubsystem, intakeMotorSubsystem);

    // auto
    saltAndPepperSkillet = new SaltAndPepperSkillet(driveSubsystem, gyroSubsystem, deployIntakeCommand,
        returnIntakeCommand);
    autonomousCommand = new AutonomousCommand(driveSubsystem, gyroSubsystem);

    rotationalControlCommandGroup = new RotationalControlCommandGroup(colorSensorSubsystem, colorWheelSpinnerSubsystem);

    // colorwheel
    // TODO: Add color wheel commmands down here
    colorWheelSpinnerCommand = new ColorWheelSpinnerCommand(colorWheelSpinnerSubsystem);

    // compressor
    compressorOnCommand = new CompressorOnCommand(compressorSubsystem);
    compressorOffCommand = new CompressorOffCommand(compressorSubsystem);

    // drive (NOTE: This is where we bind the driver controls to the drivetrain)
    driveCommand = new DriveCommand(driveSubsystem, driverController, GC_AXIS_LEFT_X, GC_AXIS_RIGHT_Y);

    // hopper
    runHopperCommand = new RunHopperCommand(hopperSubsystem);
    hopperDefaultCommand = new HopperDefaultCommand(hopperSubsystem);
    stopHopperCommand = new StopHopperCommand(hopperSubsystem);

    // kicker
    runKickerCommand = new RunKickerCommand(kickerSubsystem);
    stopKickerCommand = new StopKickerCommand(kickerSubsystem);

    // leds
    // TODO: Add LED commands here

    // Flywheel
    runDefaultFlywheel = new RunDefaultFlywheel(flywheelSubsystem);
    runFlywheelCommand = new RunFlywheelCommand(flywheelSubsystem, 0.7);
    // stopFlywheelCommand = new StopFlywheelCommand(flywheelSubsystem);

    // Flywheel piston
    retractFlywheelPistonCommand = new RetractFlywheelPistonCommand(flywheelPistonSubsystem);
    extendFlywheelPistonCommand = new ExtendFlywheelPistonCommand(flywheelPistonSubsystem);

    // tower
    runTowerCommand = new RunTowerCommand(towerSubsystem);
    stopTowerCommand = new StopTowerCommand(towerSubsystem);

    // turret
    aimTurretCommand = new AimTurretCommand(turretSubsystem);
    autoAimTurretCommand = new AutoAimTurretCommand(limelightSubsystem, turretSubsystem);
    joystickTurret = new JoystickTurret(turretSubsystem, coDriverController);

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

    // InstantCommand takes a runnable, which we're sending an anonymous method
    // through right now.
    manualModeButton.whileActiveOnce(new InstantCommand(() -> {
      inManualMode = !inManualMode;
      SmartDashboard.putBoolean("Safety", inManualMode);
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

      // --Command binds

      // Rotational control command bind
      rotationalButton.whileActiveOnce(rotationalControlCommandGroup);
      // Positional control command bind
      // positionalButton.whileActiveOnce(positionalControlCommandGroup); //TODO:
      // Uncomment me!
      // Drive command binds
      driveCommand.setTurningAndThrottleAxis(GC_AXIS_RIGHT_X, GC_AXIS_LEFT_Y);
      // Shooter command binds
      // shootButton.whileActiveOnce(new ShootPowerCellCommand(flywheelSubsystem,
      // towerSubsystem, hopperSubsystem, kickerSubsystem, limelightSubsystem,
      // flywheelPistonSubsystem));
      // shootButton.whenReleased(new StopTowerKickerCommand(towerSubsystem,
      // kickerSubsystem));
      // shootButton.whenPressed(new RunFlywheelCommand(flywheelSubsystem, 0.8));

      // ---- BUTTONS AND TRIGGERS (MANUAL) ----\\

      // --Buttons and Triggers

      // A Button
      Trigger manualColorSpinnerButton = new JoystickButton(driverController, GC_A);// .and(inManualModeTrigger);
      // B Button
      Trigger manualHopperButton = new JoystickButton(driverController, GC_B);// .and(inManualModeTrigger);
      // X Button
      Trigger manualKickerButton = new JoystickButton(driverController, GC_X);// .and(inManualModeTrigger);
      // Y Button
      Trigger manualTowerEndgame = new JoystickButton(driverController, GC_Y);// ;.and(inManualModeTrigger);
      // ZR Button
      Trigger manualFlywheelButton = new JoystickButton(driverController, GC_ZR);// .and(inManualModeTrigger);
      // ZL Button
      Trigger manualFlywheelPistonButton = new JoystickButton(driverController, GC_ZL);

      // --Command binds

      // manual color wheel spinner
      manualColorSpinnerButton.whenActive(colorWheelSpinnerCommand);
      // manual hopper spinning
      manualHopperButton.whenActive(runHopperCommand).whenInactive(stopHopperCommand);
      // manual kicker spinning
      manualKickerButton.whenActive(runKickerCommand).whenInactive(stopKickerCommand);
      // manual tower spinning
      manualTowerEndgame.whenActive(runTowerCommand).whenInactive(stopTowerCommand);
      // manual flywheel spinning
      //manualFlywheelButton.whenActive(runFlywheelCommand).whenInactive(stopFlywheelCommand);
      //manualFlywheelButton.whenActive(new PrintCommand("It is being held!"))
          //.whenInactive(new PrintCommand("It was let go!"));
      // manual flywheel piston stuff
      manualFlywheelPistonButton.whenActive(extendFlywheelPistonCommand).whenInactive(retractFlywheelPistonCommand);

      // manual
    } else { // If we're using the Xbox controller

      // --Buttons and triggers
      AxisTrigger shootButton = new AxisTrigger(driverController, XB_AXIS_RT);
      JoystickButton shootA = new JoystickButton(driverController, 1);
      JoystickButton shootX = new JoystickButton(driverController, 3);
      JoystickButton shootY = new JoystickButton(driverController, 4);
      JoystickButton flywheelStop = new JoystickButton(driverController, 2);
      // --Command binds

      // shootButton.whenActive(new RunFlywheelCommand(flywheelSubsystem, 0.8));
      shootY.whenPressed(new RunFlywheelCommand(flywheelSubsystem, 0.9));
      shootX.whenPressed(new RunFlywheelCommand(flywheelSubsystem, 0.7));
      shootA.whenPressed(new RunFlywheelCommand(flywheelSubsystem, 0.7));
      flywheelStop.whenPressed(new StopFlywheelCommand(flywheelSubsystem));
    } // end of if statement usingGamecube

  } // end of method configureDriverBindings()

  private void configureCodriverBindings() {

    // --Buttons
    // AxisTrigger intakeAxisTrigger = new AxisTrigger(coDriverController,
    // XB_AXIS_RT);
    JoystickButton a = new JoystickButton(coDriverController, 1);
    // --Command binds

    // Toggle intake
    a.toggleWhenActive(deployIntakeCommand).whenInactive(returnIntakeCommand);

  } // end of method configureCodriverBindings()

  private void beginRunCommands() {

    // --The instance of the scheduler
    CommandScheduler scheduler = CommandScheduler.getInstance();

    /*
     * if (inManualMode) { scheduler.unregisterSubsystem(turretSubsystem); //Allows
     * for automatic turrent stuff to be enabled //--Setting default commands
     * 
     * 
     * } else { scheduler.unregisterSubsystem(hopperSubsystem, turretSubsystem,
     * flywheelSubsystem, kickerSubsystem, towerSubsystem);
     * scheduler.setDefaultCommand(turretSubsystem, joystickTurret); }
     */
    //scheduler.setDefaultCommand(turretSubsystem, joystickTurret);
    // scheduler.setDefaultCommand(turretSubsystem, aimTurretCommand);
    //scheduler.setDefaultCommand(driveSubsystem, driveCommand);
    // scheduler.setDefaultCommand(hopperSubsystem, hopperDefaultCommand);
    scheduler.setDefaultCommand(flywheelSubsystem, runDefaultFlywheel);

  } // end of method beginRunCommands()

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autonomousCommand;
    // Run path following command, then stop at the end.
  }

} // end of class RobotContainer