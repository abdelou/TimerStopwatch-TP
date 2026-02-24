package states.timer;

import states.ClockState;

public class RingingTimer extends ActiveTimer {

    // use Singleton design pattern
    private RingingTimer() {
    }

    private static RingingTimer instance = null;

    public static RingingTimer Instance() {
        if (instance == null)
            instance = new RingingTimer();
        return instance;
    }

    @Override
    public ClockState up() {
        context.currentState = IdleTimer.Instance();
        return IdleTimer.Instance();
    }

    @Override
    public ClockState doIt() {
        java.awt.Toolkit.getDefaultToolkit().beep();
        return this;
    }

    public String getDisplayString() {
        // display decreasing values starting from memTimer counting down to 0
        return "Time's up !";
    }

}
