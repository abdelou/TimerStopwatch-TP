package org.example.state.compositestate.time;

import org.example.component.ClockSystem;
import org.example.component.Mode;
import org.example.state.compositestate.ClockState;
import org.example.utility.Time;

public abstract class TimeState extends ClockState {
	
	public Mode getMode() { return Mode.WATCH; }

	public abstract Time getObservedTime(ClockSystem context);
	
	}