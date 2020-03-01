package frc.robot.triggers;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * TriggerCommand
 */
public class TriggerCommand extends CommandBase {
    private JoystickButton button;

    public TriggerCommand(JoystickButton triggerButton) {
        this.button = triggerButton;
    }

    @Override
    public boolean isFinished() {
        return !this.button.get();
    }
}
