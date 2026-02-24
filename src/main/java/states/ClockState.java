package states;

public abstract class ClockState implements EventListener {

    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public ClockState left() {
        return this;
    }

    public String getLeftText() {
        return "(unused)";
    }

    public ClockState up() {
        return this;
    }

    public String getUpText() {
        return "(unused)";
    }

    public ClockState right() {
        return this;
    } // button 3 pressed (by default do nothing)

    public String getRightText() {
        return "(unused)";
    } // text to display on button 3

    public abstract String getDisplayString(); // string to display in GUI

    public abstract Mode getMode();

    public void tick() {
    }

    public String getModeText() {
        return getMode().toString();
    }

    public String getStateText() {
        return getClass().getSimpleName();
    }

    protected ClockState transition(ClockState nextState) {
        nextState.setContext(this.context);
        nextState.entry();
        return nextState;
    }

    protected void entry() {
        // default empty entry
    }

    protected abstract ClockState doIt();
}
