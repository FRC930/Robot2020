/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.turretcommads;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.LimelightSubsystem.LimelightPipelines;
import frc.robot.subsystems.LimelightSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//-------- PIDCOMMAND CLASS --------\\

public class DefaultTurretCommand extends PIDCommand {

    //-------- DECLARATIONS --------\\

    private final Logger logger = Logger.getLogger(DefaultTurretCommand.class.getName());
    private static final int MAX_ROTATIONAL_UNITS = 3570;

    //-------- CONSTRUCTOR --------\\

    public DefaultTurretCommand(LimelightSubsystem limeLight, TurretSubsystem turret) {
        super(new PIDController(0.025, 0.0, 0.0008),
                // This lambda tells the controller where to get the input values from
                () -> {
                    SmartDashboard.putNumber("horiz off", limeLight.getHorizontalOffset());
                    if (limeLight.getValidTargets()) {
                        return limeLight.getHorizontalOffset();
                    }
                    return 0.0;
                },
                // The setpoint that the controller will try to acheive
                0.0,
                // This lambda tells the controller how to use the output
                (double output) -> {
                    // double FeedForward = 0.2;
                    // output = output + FeedForward;

                    // Make sure that we are not assigning some weird value to the motor
                    if (output > 1) {
                        output = 1;
                    } else if (output < -1) {
                        output = -1;
                    }
                    SmartDashboard.putNumber("controller", output);

                    // Make sure that the turret does not turn past ~300° in either direction
                    // Internal units. 3570 == ~300°
                    if (output < 0) {
                        if (turret.getEncoderPosition() > MAX_ROTATIONAL_UNITS) {
                            output = 0;
                        }
                    } else if (output > 0) {
                        if (turret.getEncoderPosition() < -MAX_ROTATIONAL_UNITS) {
                            output = 0;
                        }
                    }

                    turret.setSpeed(output);
                },
                // Pass in the subsystems we will need
                turret, limeLight); // End of super constructor

        logger.entering(this.getClass().getName(), "AutoAimTurretCommand");

        // Enable the controller to continuously get input
        this.getController().enableContinuousInput(-27, 27);

        // Set the tolerance of the controller
        this.getController().setTolerance(0.4);

        // Require the subsystems that we need
        addRequirements(limeLight, turret);

    } // end of constructor AutoAimTurretCommand()

    @Override
    public boolean isFinished() {
        return false;
    }
    
} // End DefaultTurretCommand class