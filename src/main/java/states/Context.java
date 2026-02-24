package states;

import states.stopwatch.AbstractStopwatch;
import states.timer.AbstractTimer;

public class Context implements EventListener {

    public ClockState currentState;

    public Context() {
        currentState = AbstractTimer.Instance(); // set initial state
        currentState.setContext(this);
        AbstractTimer.historyState = AbstractTimer.Instance();
        AbstractStopwatch.historyState = AbstractStopwatch.Instance();
    }

    @Override
    public ClockState left() {
        currentState = currentState.left();
        currentState.setContext(this);
        return currentState;
    }

    @Override
    public ClockState up() {
        currentState = currentState.up();
        currentState.setContext(this);
        return currentState;
    }

    @Override
    public ClockState right() {
        currentState = currentState.right();
        currentState.setContext(this);
        return currentState;
    }

    public void tick() {
        currentState = currentState.doIt();
        currentState.setContext(this);
    }

    public String getDisplayText() {
        return currentState.getDisplayString();
    }

    public String getStateText() {
        return currentState.getClass().getSimpleName();
    }

    public String getModeText() {
        return currentState.getMode().name();
    }

    public String getLeftText() {
        return currentState.getLeftText();
    }

    public String getUpText() {
        return currentState.getUpText();
    }

    public String getRightText() {
        return currentState.getRightText();
    }
}
