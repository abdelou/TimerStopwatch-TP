package states.timer;

import states.ClockState;

public class PausedTimer extends ActiveTimer {

    private PausedTimer() {
    }

    private static PausedTimer instance = null;

    public static PausedTimer Instance() {
        if (instance == null)
            instance = new PausedTimer();
        return instance;
    }

    @Override
    public ClockState up() {
        return transition(RunningTimer.Instance());
    }

    public String getUpText() {
        return "resume";
    }

    @Override
    public ClockState right() {
        return transition(IdleTimer.Instance());
    }

    public String getRightText() {
        return "stop";
    }

    @Override
    protected ClockState doIt() {
        return this;
    }

    public String getDisplayString() {
        return "timer = " + timer;
    }
}
