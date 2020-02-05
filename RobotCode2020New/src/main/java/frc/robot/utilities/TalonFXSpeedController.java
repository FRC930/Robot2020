package frc.robot.utilities;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

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
        //System.out.println("double get "+super.getMotorOutputPercent());
        return super.getMotorOutputPercent();
    }


    /*
    * Sets speed by percent from -1 to 1.
    */
    public void set(double speed) { //that ----------------.
        System.out.println("void set");
        super.set(TalonFXControlMode.PercentOutput, speed);    // |
    }                                                   // |
                                                        // |
                                                        // to
    /*                                                  // |
    * Stops motor by setting speed to 0.0.              // |
    */                                                  // |
    public void stopMotor() {                           // |
        System.out.println("void stopmotor");
        //this.set(0.0); //this -----------------------------'
        //System.out.flush();
        //throw new RuntimeException("thingy");
    }
    
    public void setvolts(double speed) { //that ----------------.
        System.out.println("void set 2");
        super.set(TalonFXControlMode.Current, speed);    // |
    }  

    /*
    * Since pidWrite is never called within the DifferentialDrive() class, we do not need to fill in anything.
    * 
    * pidWrite is located within SpeedController and PIDOutput class.
    */
    public void pidWrite(double output) {
        System.out.println("pidwrite");
    }
    public double getRPMLeft(TalonFXSpeedController left1)
    {
        
      // The velocity from the falcon encoder doesn't give use RPM, but rather their own units. There are 2048 position units per 100ms.
      // One unit = one position unit / 100ms; One revolution per 2048 units;
      // So if we do, (1 Revolution/ 2048)*(1000ms/1s)*(60s/1min) that converts our revolutions to minutes.
      // The conversion will result in 60000/204800 (600/2048 simplified (as seen in the return statement))
      // So our final equation will be (getIntergratedSensorVelocity() * (600.0 / 2048.0)) resulting in a pretty accurate RPM (+-10).
      return left1.getSensorCollection().getIntegratedSensorVelocity() * (600.0 / 2048.0);
    }
    public double getRPMRight(TalonFXSpeedController right1)
    {
        
      // The velocity from the falcon encoder doesn't give use RPM, but rather their own units. There are 2048 position units per 100ms.
      // One unit = one position unit / 100ms; One revolution per 2048 units;
      // So if we do, (1 Revolution/ 2048)*(1000ms/1s)*(60s/1min) that converts our revolutions to minutes.
      // The conversion will result in 60000/204800 (600/2048 simplified (as seen in the return statement))
      // So our final equation will be (getIntergratedSensorVelocity() * (600.0 / 2048.0)) resulting in a pretty accurate RPM (+-10).
      return right1.getSensorCollection().getIntegratedSensorVelocity() * (600.0 / 2048.0);
    }

}