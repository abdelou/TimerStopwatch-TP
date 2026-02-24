package org.example.state.compositestate.time;

import org.example.component.ClockSystem;
import org.example.state.time.TimeHourSetting;

import java.awt.*;

public abstract class TimeDisplayState extends TimeState {

 @Override
    public void button1Pressed(ClockSystem context) {
        context.setState(context.getClockTimer().getPowerState()); }

    @Override
    public void button2Pressed(ClockSystem context) {
        if (context.hasClock()) {
            context.getClock().setHourColor(new Color(46,127, 189)); }
        context.setState(TimeHourSetting.getInstance());
        context.getTimer().cancel(); }
}
