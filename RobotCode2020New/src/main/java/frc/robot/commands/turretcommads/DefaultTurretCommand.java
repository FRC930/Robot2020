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

    // -------- DECLARATIONS --------\\

    private final Logger logger = Logger.getLogger(DefaultTurretCommand.class.getName());

    // -------- CONSTRUCTOR --------\\

    public DefaultTurretCommand(LimelightSubsystem limeLight, TurretSubsystem turret, PIDController controller) {
        // Initial P = 0.065
        // Oscillations over time: 3 cycles per 1 second
        // Period = 0.33   :    Took the oscillations over time and divided one by that number
        // Calcualted P = 0.6 * Initial P = 0.039
        // Calculated I = (1.2 * Initial P) / Period = 0.24
        // Calculated D = (3 * Initial P * Period) / 40
        super(controller,
                // This lambda tells the controller where to get the input values from
                () -> {
                    SmartDashboard.putNumber("horiz off", limeLight.getHorizontalOffset());
                    // SmartDashboard.putNumber("PID error", value);
                    if (limeLight.getValidTargets()) {
                        return limeLight.getHorizontalOffset();
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
                    SmartDashboard.putNumber("controller", output);

                    if (Math.abs(limeLight.getHorizontalOffset()) < 27) {
                        turret.setSpeed(output);
                    }
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