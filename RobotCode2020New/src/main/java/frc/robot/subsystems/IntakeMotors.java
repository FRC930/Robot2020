package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class IntakeMotors extends SubsystemBase {

    private final int INTAKE_TALONSRX_ID = 6;

    private TalonSRX intakeMotorController; 

    public IntakeMotors() {
        setControllers();
    }

    //-------- METHODS --------\\

    public void setControllers() {
        setControllers(
            new TalonSRX(INTAKE_TALONSRX_ID)
        );
    }

    public void setControllers(TalonSRX motorController) {
        intakeMotorController = motorController;
    }
    //sets motor speed 
    public void setMotorSpeed(double speed) {
        intakeMotorController.set(ControlMode.PercentOutput, speed);
    }
    public double getMotorSpeed() {
        //return intakeMotorController.get(); //TODO: Fix me
        return 0.0;
    }

}
