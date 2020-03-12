// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// //-------- IMPORTS --------\\

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


// //-------- SUBSYSTEM CLASS --------\\

public class LEDSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\
    
    private final int PORT = 0;
    private final int LEDS = 60;

//     //-------- DECLARATIONS --------\\

    private AddressableLEDBuffer ledsBuffer = new AddressableLEDBuffer(LEDS);
    private AddressableLED leds = new AddressableLED(PORT);

    //private boolean doneLoop;

//     //-------- CONSTRUCTOR --------\\

    public LEDSubsystem() {
        leds.setLength(ledsBuffer.getLength());
        setLEDs(0, 0, 0);
        leds.start();

        //doneLoop = false;
    }

    //-------- METHODS --------\\
    
    public void updateBuffer(AddressableLEDBuffer buffer)
    {
        this.ledsBuffer = buffer;
        leds.setData(ledsBuffer);
    }

    public void resetLEDs()
    {
        for(int i = 0; i < ledsBuffer.getLength(); i++)
            this.ledsBuffer.setRGB(i, 0, 0, 0);

        leds.setData(ledsBuffer);
    }

    public void setLEDs(int r, int g, int b) {
        for(int i = 0; i < ledsBuffer.getLength(); i++) {
            ledsBuffer.setRGB(i, r, g, b);
        }
        leds.setData(ledsBuffer);
    }

    // @Override
    // public void periodic() {      
    // }
    
} // end of class LEDSubsystem
