package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * <h3>ShooterSubsystem</h3>
 * <p>
 * The shooter subsystem controls the hardware for the shooter
 * </p><p>
 * There are two constructors: one with hardware arguments used for mocking, and
 * one that does not receive hardware and sets the motor controllers to a default value
 * </p>
 */
public class ShooterSubsystem extends SubsystemBase {
    // motor controllers for the 775 motors on the shooter
    private final CANSparkMax motorLead;
    private final CANSparkMax motor2;

    private CANPIDController pidcontroller;
    private Logger logger;

    //PID Derivitive Gain
    private final double PID_D = 0.004;
    //PID Proportional Gain
    private final double PID_P = 0.0004;
    //PID Feed-Forward Gain
    private final double PID_FF = 0.0002;

    // solenoid dedicated to moving the turret up and down to have a close and far
    // range
    //private final Solenoid solenoid;

    public ShooterSubsystem() {
        this(new CANSparkMax(7, MotorType.kBrushless), new CANSparkMax(9, MotorType.kBrushless)); //new CANSparkMax(2, MotorType.kBrushless), new CANSparkMax(3, MotorType.kBrushless));
    }
    
    public ShooterSubsystem(CANSparkMax lMotor, CANSparkMax rMotor) {
        this.motorLead = lMotor;
        this.motor2 = rMotor;
        
        this.pidcontroller = motorLead.getPIDController();
        //this.pidcontroller.setFF(PID_FF);
        this.pidcontroller.setOutputRange(0, 1);
        //this.pidcontroller.setP(PID_P);
        //this.pidcontroller.setD(PID_D);
        motor2.follow(motorLead, true);
        
        //solenoid = new Solenoid(0);
        
        logger = Logger.getLogger(ShooterSubsystem.class.getName());
        logger.setLevel(Level.INFO); 
    }

    public void setSpeed(double speed) {
        logger.entering(this.getClass().getName(), "setSpeed()");

        //if(speed <= 1.0 && speed >= 0.0)
        //{
            // Set the speed in percent output * the max RPM of the NEO.
            //this.pidcontroller.setReference(speed * 5880, ControlType.kVelocity);
            motorLead.set(speed);
            // motor2.set(-speed);
        //}
        logger.log(Level.FINE, "Set shooter speed to " + speed);

        logger.exiting(this.getClass().getName(), "setSpeed()");
    }

    public void stop() {
        logger.entering(getClass().getName(), "stop()");
        motorLead.set(0.0);
        logger.exiting(getClass().getName(), "stop()");
    }

    public void angleChange(boolean solenoidStatus) {
        logger.entering(getClass().getName(), "angleChange()");
        //solenoid.set(true);
        logger.exiting(getClass().getName(), "angleChange()");
    }

    public boolean getAngle() {
        logger.entering(getClass().getName(), "getAngle()");
        logger.exiting(getClass().getName(), "getAngle()");
        //return solenoid.get();
        return true;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("LeftRPM", motorLead.getEncoder().getVelocity());
        SmartDashboard.putNumber("RightRPM", motor2.getEncoder().getVelocity());
    }
} // End ShooterSubsystem