/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ledcommands;

import frc.robot.subsystems.LEDSubsystem;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class IntakeLEDs extends CommandBase {
    private final int BUFFER_LENGTH = 60;
    private final LEDSubsystem m_LEDSubsystem;
    private AddressableLEDBuffer buffer;
    private long counter;
    private int index1;
    private int index2;
    private int indexA;
    private int indexB;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public IntakeLEDs(LEDSubsystem LEDSubsystem) {
        m_LEDSubsystem = LEDSubsystem;
        buffer = new AddressableLEDBuffer(BUFFER_LENGTH);
        counter = 0;
        index1 = 0;
        index2 = 19;
        indexA = 30;
        indexB = 49;

        addRequirements(LEDSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        for (int i = 0; i < buffer.getLength(); i++) {
            buffer.setRGB(i, 0, 0, 0);
        }
        m_LEDSubsystem.updateBuffer(buffer);
    }

    private void moveLEDs() {
        index1++;
        index2++;
        indexA++;
        indexB++;

        if (index2 >= BUFFER_LENGTH) {
            index2 = 0;
        }
        if (index1 >= BUFFER_LENGTH) {
            index1 = 0;
        }
        if (indexA >= BUFFER_LENGTH) {
            indexA = 0;
        }
        if (indexB >= BUFFER_LENGTH) {
            indexB = 0;
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        counter++;

        // Keeping track of animation speed.
        if (counter % 2 == 0) {
            buffer.setRGB(index1, 0, 0, 0);
            buffer.setRGB(indexA, 0, 0, 0);
            moveLEDs();
            buffer.setRGB(index2, 0, 0, 255);
            buffer.setRGB(indexB, 255, 255, 0);
            m_LEDSubsystem.updateBuffer(buffer);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
