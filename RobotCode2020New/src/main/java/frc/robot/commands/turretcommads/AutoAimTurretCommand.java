/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.turretcommads;

import java.util.logging.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

import frc.robot.subsystems.TurretSubsystem;
import frc.robot.Constants;
import frc.robot.subsystems.LimelightSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//-------- PIDCOMMAND CLASS --------\\

public class AutoAimTurretCommand extends PIDCommand {

    // -------- DECLARATIONS --------\\

    private static final Logger logger = Logger.getLogger(AutoAimTurretCommand.class.getName());
    private LimelightSubsystem limelightSubsystem;
    private final int LEDS_ON = 3;

    // -------- CONSTRUCTOR --------\\

    public AutoAimTurretCommand(LimelightSubsystem limelight, TurretSubsystem turret, PIDController controller, Joystick coDriver, int coDriverAxis) {
        // Initial P = 0.065
        // Oscillations over time: 3 cycles per 1 second
        // Period = 0.33   :    Took the oscillations over time and divided one by that number
        // Calcualted P = 0.6 * Initial P = 0.039
        // Calculated I = (1.2 * Initial P) / Period = 0.24
        // Calculated D = (3 * Initial P * Period) / 40
        super(controller,
                // This lambda tells the controller where to get the input values from
                () -> {
                    SmartDashboard.putNumber("horiz off", limelight.getHorizontalOffset());
                    // SmartDashboard.putNumber("PID error", value);
                    if (limelight.getValidTargets()) {
                        return limelight.getHorizontalOffset();
                    } else {
                        controller.reset();
                        return 0.0;
                    }
                },
                // The setpoint that the controller will try to acheive
                0.0,
                // This lambda tells the controller how to use the output
                (double output) -> {

                    // Make sure that we are not assigning some weird value to the motor
                    if (output > 1) {
                        output = 1;
                    } else if (output < -1) {
                        output = -1;
                    }
                    
                    //System.out.println("horizontal offset: " + limelight.getHorizontalOffset());
                    SmartDashboard.putNumber("controller", output);

                    // manual control will override the auto tracking
                    if(Math.abs(coDriver.getRawAxis(coDriverAxis)) > Constants.JOYSTICK_TURRET_DEADBAND) {
                        logger.log(Level.INFO, "turret joytick value > Constants.JOYSTICK_TURRET_DEADBAND");
                        turret.setSpeed(-Math.pow(coDriver.getRawAxis(coDriverAxis), 3) * 0.5);
                    } else {
                        logger.log(Level.INFO, "turret joytick value < Constants.JOYSTICK_TURRET_DEADBAND");
                        if(Math.abs(limelight.getHorizontalOffset()) < 27) {
                            logger.log(Level.INFO, "setting 'turret' speed ="+ output);
                            turret.setSpeed(output);
                        }
                    }
                },
                // Pass in the subsystems we will need
                turret, limelight); // End of super constructor

        logger.entering(AutoAimTurretCommand.class.getName(), "AutoAimTurretCommand");
        this.limelightSubsystem = limelight;

        // Enable the controller to continuously get input
        this.getController().enableContinuousInput(-27, 27);

        // Set the tolerance of the controller
        this.getController().setTolerance(0.4);

        // Require the subsystems that we need
        addRequirements(limelight, turret);
    
        logger.exiting(AutoAimTurretCommand.class.getName(), "AutoAimTurretCommand");
    } // end of constructor AutoAimTurretCommand()

    @Override
    public void initialize() {
        // turn limelight LEDs on
        limelightSubsystem.setLightMode(LEDS_ON);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

} // End DefaultTurretCommand class