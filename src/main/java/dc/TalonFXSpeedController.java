
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


/*
* TalonFXSpeedController is made to object cast a TalonFX motor to a SpeedController for the DifferentialDrive() used for autonomous.
*
* extends to TalonFX class, implements SpeedController interface.
*/
public class TalonFXSpeedController extends TalonFX implements SpeedController {


    /*
    * constructor.
    */
    public TalonFXSpeedController(int deviceNumber) {
        super(deviceNumber);
    }


    /*
    * disable() sets the motor output to neutral.
    * 
    * super goes to BaseMotorController class above TalonFX and BaseTalon.
    */
    public void disable() {
        super.neutralOutput();
    }


    /*
    * Gets the output percentage of the motor controller.
    * 
    * super goes to BaseMotorController class above TalonFX and BaseTalon.
    */
    public double get() {
        return super.getMotorOutputPercent();
    }


    /*
    * Sets speed by percent from -1 to 1.
    */
    public void set(double speed) { //that ----------------.
        super.set(ControlMode.PercentOutput, speed);    // |
    }                                                   // |
                                                        // |
                                                        // to
    /*                                                  // |
    * Stops motor by setting speed to 0.0.              // |
    */                                                  // |
    public void stopMotor() {                           // |
        this.set(0.0); //this -----------------------------'
    }


    /*
    * Since pidWrite is never called within the DifferentialDrive() class, we do not need to fill in anything.
    * 
    * pidWrite is located within SpeedController and PIDOutput class.
    */
    public void pidWrite(double output) {

    }
}
