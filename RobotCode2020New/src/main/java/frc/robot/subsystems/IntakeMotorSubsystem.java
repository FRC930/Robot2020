package frc.robot.subsystems;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class IntakeMotorSubsystem extends SubsystemBase {
  

    private final Logger logger = Logger.getLogger(IntakeMotorSubsystem.class.getName());

    private final int INTAKE_TALONSRX_ID = 6;

    private TalonSRX intakeMotorController; 

    public IntakeMotorSubsystem() {
        intakeMotorController = new TalonSRX(INTAKE_TALONSRX_ID);
    
    }

    //-------- METHODS --------\\
    
    //sets motor speed 
    public void setMotorSpeed(double speed) {
        intakeMotorController.set(ControlMode.PercentOutput, speed);
        logger.log(Level.FINE,"sets motor speed");
    }
    public double getMotorSpeed() {
        return intakeMotorController.getMotorOutputPercent();  
       // return 0.0;
    }

}//END OF CLASS
