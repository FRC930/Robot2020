package dc;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class TalonFXEncoders extends SubsystemBase {
    public TalonFX leftfalcon500;
    public TalonFX rightfalcon500;

    public TalonFXEncoders() {
      leftfalcon500 = new TalonFX(3);
      rightfalcon500 = new TalonFX(1);
    }

    

    public double getRPMLeft()
    {
        
      // The velocity from the falcon encoder doesn't give use RPM, but rather their own units. There are 2048 position units per 100ms.
      // One unit = one position unit / 100ms; One revolution per 2048 units;
      // So if we do, (1 Revolution/ 2048)*(1000ms/1s)*(60s/1min) that converts our revolutions to minutes.
      // The conversion will result in 60000/204800 (600/2048 simplified (as seen in the return statement))
      // So our final equation will be (getIntergratedSensorVelocity() * (600.0 / 2048.0)) resulting in a pretty accurate RPM (+-10).
      return leftfalcon500.getSensorCollection().getIntegratedSensorVelocity() * (600.0 / 2048.0);
    }
  
    public double getRPMRight()
    {
      // The velocity from the falcon encoder doesn't give use RPM, but rather their own units. There are 2048 position units per 100ms.
      // One unit = one position unit / 100ms; One revolution per 2048 units;
      // So if we do, (1 Revolution/ 2048)*(1000ms/1s)*(60s/1min) that converts our revolutions to minutes.
      // The conversion will result in 60000/204800 (600/2048 simplified (as seen in the return statement))
      // So our final equation will be (getIntergratedSensorVelocity() * (600.0 / 2048.0)) resulting in a pretty accurate RPM (+-10).
      return rightfalcon500.getSensorCollection().getIntegratedSensorVelocity() * (600.0 / 2048.0);
    }

    public double getLeftRate() {  
        return getRPMLeft()/60;
    }

    public double getRightRate() {
        return getRPMRight()/60;
    }

    @Override
    public void periodic() {
        getRPMLeft();
        getRPMRight();
    }
  }
  