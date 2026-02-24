package org.example.state.compositestate.time;

import org.example.component.ClockSystem;
import org.example.utility.Time;

public abstract class TimeSettingState extends TimeState {

	public Time getObservedTime(ClockSystem context) {
		return context.getDisplayState().getObservedTime(context); }

	@Override
	public String getIcon(ClockSystem context) {
		return context.getDisplayState().getIcon(context); }
}
