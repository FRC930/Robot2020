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
import frc.robot.subsystems.LimelightSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//-------- PIDCOMMAND CLASS --------\\

public class AutoAimAutonomousCommand extends PIDCommand {

    // -------- DECLARATIONS --------\\

    private static final Logger logger = Logger.getLogger(AutoAimTurretCommand.class.getName());
    private LimelightSubsystem limelight;
    private int counter;
    private final int LEDS_ON = 3;

    // -------- CONSTRUCTOR --------\\

    public AutoAimAutonomousCommand(LimelightSubsystem limelight, TurretSubsystem turretSubsystem, PIDController controller) {
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
                    SmartDashboard.putNumber("controller", output);

                    

                    if(Math.abs(limelight.getHorizontalOffset()) < 27) {
                            turretSubsystem.setSpeed(output);
                        
                    }
                },
                // Pass in the subsystems we will need
                turretSubsystem, limelight); // End of super constructor

        logger.entering(AutoAimAutonomousCommand.class.getName(), "AutoAimTurretCommand");
        this.limelight = limelight;
        counter = 0;

        // Enable the controller to continuously get input
        this.getController().enableContinuousInput(-27, 27);

        // Set the tolerance of the controller
        this.getController().setTolerance(0.2);

        // Require the subsystems that we need
        addRequirements(limelight, turretSubsystem);

    } // end of constructor AutoAimTurretCommand()

    @Override
    public void initialize() {
        // turn limelight LEDs on
        limelight.setLightMode(LEDS_ON);
    }

    @Override
    public boolean isFinished() {

        double offset = limelight.getHorizontalOffset();
        boolean inRange = false;

        if(Math.abs(offset) < 1.5) {
            counter++;
            if(counter >= 10) {
                inRange = true;
            }
        } 
        else {
            counter = 0;
            inRange = false;
        }

        return inRange;
    }

} // End DefaultTurretCommand class