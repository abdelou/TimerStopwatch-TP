package org.example.state.compositestate.clocktimer;

import org.example.component.ClockSystem;
import org.example.component.Mode;
import org.example.state.compositestate.ClockState;
import org.example.utility.Time;

public abstract class ClockTimerState extends ClockState {
		
	public Mode getMode() { return Mode.CLOCKTIMER; }

	public Time getObservedTime(ClockSystem context) {
		return context.getClockTimer().getTime();
		}

	}