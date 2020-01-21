package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

/**
 * TurretSubsystem
 */
public class TurretSubsystem extends PIDSubsystem {
    CANSparkMax controller;

    public TurretSubsystem() {
        // TODO: Figure out values for PID controller
        super(new PIDController(0, 0, 0));
        getController().setTolerance(1 /* Figure out the tolerance */);
        setSetpoint(0 /* Figure out the set-point */);
    }

    public TurretSubsystem(PIDController controller) {
        super(controller);
    }

    public void setHardware() {
        // TODO: Get device IDs
        setHardware(new CANSparkMax(0 /* Figure out device ID */, MotorType.kBrushless));
    }

    public void setHardware(CANSparkMax turretController) {
        controller = turretController;
    }

    @Override
    protected void useOutput(double output, double setPoint) {
        // Check this. Could need to be negative output
        controller.set(output);
    }

    @Override
    protected double getMeasurement() {
        // TODO: Talk to Andrew's subteam to get the angle value from the LimeLight
        return 0 /* LimeLight get angle */;
    }
}