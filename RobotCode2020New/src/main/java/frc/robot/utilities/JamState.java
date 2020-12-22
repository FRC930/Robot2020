package frc.robot.utilities;

/**
 * StopHopperState
 */
public class JamState {
    private static JamState instance = null;
    private boolean state;

    private JamState() {
        this.state = false;
    }

    public static JamState getInstance() {
        if (instance == null) {
            instance = new JamState();
        }
        return instance;
    }

    public void invertState() {
        this.state = !this.state;
    }

    public boolean getState() {
        return this.state;
    }
}