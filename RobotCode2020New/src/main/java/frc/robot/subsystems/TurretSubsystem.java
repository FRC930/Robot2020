// package frc.robot.subsystems;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import edu.wpi.first.wpilibj.controller.PIDController;
// import edu.wpi.first.wpilibj2.command.PIDSubsystem;

// /**
//  * <h2>TurretSubsystem
//  * 
//  * <p>
//  * TurretSubsystem is a PID subsystem that controls the rotation of the turret
//  */
// public class TurretSubsystem extends PIDSubsystem {
//     /**
//      * The motor controller that will control the turret rotation
//      */
//     private ;
//     private double angle;
// CANSparkMax controller
//     /**
//      * <p>
//      * Use this constructor to initialize the PID controller to a default controller
//      * <p>
//      * This constructor also calls {@link #setHardware() setHardware}
//      */
//     public TurretSubsystem() {
//         // TODO: Figure out values for PID controller
//         super(new PIDController(0, 0, 0));
//         getController().setTolerance(1 /* Figure out the tolerance */);
//         setSetpoint(0 /* Figure out the set-point */);
//         this.angle = 0;
//     }

//     /**
//      * Use this controller to initialize the PID controller to a specified
//      * controller
//      * 
//      * @param controller is a PIDController that will control the turret
//      */
//     public TurretSubsystem(PIDController controller) {
//         super(controller);
//         this.angle = 0;
//     }

//     /**
//      * Use this method to set the motor controller to a default
//      */
//     public void setHardware() {
//         // TODO: Get device ID
//         setHardware(new CANSparkMax(0 /* Figure out device ID */, MotorType.kBrushless));
//     }

//     /**
//      * Use this method to set the motor controller to a passed motor controller
//      * 
//      * @param turretController is the motor controller
//      */
//     public void setHardware(CANSparkMax turretController) {
//         controller = turretController;
//     }

//     /**
//      * The angle should be in degrees
//      * 
//      * @param angle is the angle that the turret is off the setpoint 
//      */
//     public void setMeasurement(double angle) {
//         this.angle = angle;
//     }

//     /**
//      * This method sets the motor controller speed to the output of the PID
//      * controller
//      * 
//      * @param output   the output that the PID controller sends
//      * @param setPoint is the value that the PID controller is trying to accomplish
//      */
//     @Override
//     protected void useOutput(double output, double setPoint) {
//         if (output > 1) {
//             controller.set(1);
//         } else if (output < -1) {
//             controller.set(-1);
//         } else {
//             controller.set(output);
//         }
//     }

//     /**
//      * This method returns the measurement that the PID controller will use to try
//      * to achieve the setpoint
//      * 
//      * @return the measurement given from the LimeLight
//      */
//     @Override
//     protected double getMeasurement() {
//         return this.angle;
//     }
// }