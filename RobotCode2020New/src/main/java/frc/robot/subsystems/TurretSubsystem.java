package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

/**
 * <h2>TurretSubsystem
 * 
 * <p>TurretSubsystem is a PID subsystem that controls the rotation of the turret
 */
public class TurretSubsystem extends PIDSubsystem {
    /**
     * The motor controller that will control the turret rotation
     */
    private CANSparkMax controller;

    /**
     * <p>Use this constructor to initialize the PID controller to a default controller
     * <p>This constructor also calls {@link #setHardware() setHardware}
     */
    public TurretSubsystem() {
        // TODO: Figure out values for PID controller
        super(new PIDController(0, 0, 0));
        getController().setTolerance(1 /* Figure out the tolerance */);
        setSetpoint(0 /* Figure out the set-point */);
    }

    /**
     * Use this controller to initialize the PID controller to a specified controller
     * 
     * @param controller is a PIDController that will control the turret
     */
    public TurretSubsystem(PIDController controller) {
        super(controller);
    }

    /**
     * Use this method to set the motor controller to a default
     */
    public void setHardware() {
        // TODO: Get device ID
        setHardware(new CANSparkMax(0 /* Figure out device ID */, MotorType.kBrushless));
    }

    /**
     * Use this method to set the motor controller to a passed motor controller
     * 
     * @param turretController is the motor controller
     */
    public void setHardware(CANSparkMax turretController) {
        controller = turretController;
    }

    /**
     * This method sets the motor controller speed to the output of the PID controller 
     * 
     * @param output the output that the PID controller sends
     * @param setPoint is the value that the PID controller is trying to accomplishd
     */
    @Override
    protected void useOutput(double output, double setPoint) {
        // TODO: Check this. Could need to be negative output
        controller.set(output);
    }

    /**
     * This method returns the measurement that the PID controller will use to try to achieve the setpoint
     * 
     * @return the measurement given from the LimeLight
     */
    @Override
    protected double getMeasurement() {
        // TODO: Talk to Andrew's subteam to get the angle value from the LimeLight
        return 0 /* LimeLight get angle */;
    }
}