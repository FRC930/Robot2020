package dc;

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
        System.out.println("constructor");
    }


    /*
    * disable() sets the motor output to neutral.
    * 
    * super goes to BaseMotorController class above TalonFX and BaseTalon.
    */
    public void disable() {
        System.out.println("void disable");
        super.neutralOutput();
    }


    /*
    * Gets the output percentage of the motor controller.
    * 
    * super goes to BaseMotorController class above TalonFX and BaseTalon.
    */
    @Override
    public double get() {
        System.out.println("double get "+super.getMotorOutputPercent());
        return super.getMotorOutputPercent();
    }


    /*
    * Sets speed by percent from -1 to 1.
    */
    public void set(double speed) { //that ----------------.
        System.out.println("void set");
        super.set(ControlMode.PercentOutput, speed);    // |
    }                                                   // |
                                                        // |
                                                        // to
    /*                                                  // |
    * Stops motor by setting speed to 0.0.              // |
    */                                                  // |
    public void stopMotor() {                           // |
        System.out.println("void stopmotor");
        this.set(0.0); //this -----------------------------'
    }


    /*
    * Since pidWrite is never called within the DifferentialDrive() class, we do not need to fill in anything.
    * 
    * pidWrite is located within SpeedController and PIDOutput class.
    */
    public void pidWrite(double output) {
        System.out.println("pidwrite");
    }
}
