/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
//-------- SUBSYSTEM CLASS --------\\

public class EndgameSubsystem extends SubsystemBase {
    private final int LEAD_ID = 12;
    private final int FOLLOWER1_ID = 13;
    private final int FOLLOWER2_ID = 14;

    private TalonSRX endgameTalonLead;
    private VictorSPX endgameVictorFollower;
    private VictorSPX endgameVictorFollower2;
    public EndgameSubsystem(){
        endgameTalonLead = new TalonSRX(LEAD_ID);
        endgameVictorFollower = new VictorSPX(FOLLOWER1_ID);
        endgameVictorFollower2 = new VictorSPX(FOLLOWER2_ID);
        endgameVictorFollower.follow(endgameTalonLead);
        endgameVictorFollower2.follow(endgameTalonLead);
    }
    public void setEndgameMotorSpeed(double speed){
        endgameTalonLead.set(ControlMode.PercentOutput, speed);
    }
    public double getEndgameMotorSpeed(){
        return endgameTalonLead.getMotorOutputPercent();
    }
}