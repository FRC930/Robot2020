/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.turretcommads;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj.controller.PIDController;

import frc.robot.Constants;
import frc.robot.subsystems.TurretSubsystem;

//-------- COMMAND CLASS --------\\

public class SetTurretPositionPIDCommand extends PIDCommand {

    //You must include logger as a constant variable, and you must have logging in your files
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final double SPEED_LIMIT = 0.4;
    private TurretSubsystem turretSubsystem;    
    
    public SetTurretPositionPIDCommand(TurretSubsystem turretSubsystem, PIDController controller, double targetPosition){

        super(controller,
            // lambda for passing through the position of the encoder
            ()->{
                return turretSubsystem.getRawEncoderPosition();
            },

            // target position to reach
            targetPosition,

            // lambda that sets the speed of the turret
            (double speed)->{

                // clamp the speed of the turret
                if (speed > Constants.TURRET_MAX_SET_POSITION_SPEED) {
                    speed = Constants.TURRET_MAX_SET_POSITION_SPEED;
                } else if (speed < -Constants.TURRET_MAX_SET_POSITION_SPEED) {
                    speed = -Constants.TURRET_MAX_SET_POSITION_SPEED;
                }

                System.out.println("SETTING SPEED: " + speed);
                turretSubsystem.setSpeed(speed);
            },

            turretSubsystem);

        this.turretSubsystem = turretSubsystem;

        // Enable the controller to continuously get input
        this.getController().enableContinuousInput(0, 1);

        // Set the tolerance of the controller
        this.getController().setTolerance(Constants.TURRET_DEADBAND);

        addRequirements(turretSubsystem);
    }

    //-------- METHODS --------\\
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {   
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
} // end of class TurretBackPIDCommand 
