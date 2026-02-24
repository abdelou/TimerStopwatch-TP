package states.timer;

import states.ClockState;

public class IdleTimer extends AbstractTimer {

    private IdleTimer() {
    }

    private static IdleTimer instance = null;

    public static IdleTimer Instance() {
        if (instance == null)
            instance = new IdleTimer();
        return instance;
    }

    @Override
    public ClockState up() {
        if (memTimer > 0) {
            timer = memTimer;
            return transition(RunningTimer.Instance());
        }
        return this;
    }

    @Override
    public String getUpText() {
        return "run";
    }

    @Override
    public ClockState right() {
        return transition(SetTimer.Instance());
    }

    @Override
    public String getRightText() {
        return "set";
    }

    @Override
    protected ClockState doIt() {
        return this;
    }

    public String getDisplayString() {
        return "timer = " + timer;
    }
}
