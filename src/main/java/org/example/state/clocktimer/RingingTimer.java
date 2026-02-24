package org.example.state.clocktimer;

import org.example.component.ClockSystem;
import org.example.state.compositestate.clocktimer.ClockTimerDisplayState;

public class RingingTimer extends ClockTimerDisplayState {

    private static RingingTimer instance;

    private RingingTimer() {
        BUTTON_ONE_NAME = "STOP";
        BUTTON_TWO_NAME = "";
        BUTTON_THREE_NAME = "";
    }

    public static RingingTimer getInstance() {
        if (instance == null) {
            instance = new RingingTimer();
        }
        return instance;
    }

    @Override
    public void entry(ClockSystem context) {
        context.getClockTimer().setIsRinging(true);
        context.getClockTimer().ring();
    }

    @Override
    public void exit(ClockSystem context) {
        context.getClockTimer().setIsRinging(false);
    }

    @Override
    public void button1Pressed(ClockSystem context) {
        context.setState(DisplayClockTimerOff.getInstance());
    }
}
