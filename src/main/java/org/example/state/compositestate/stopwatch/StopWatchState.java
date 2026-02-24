package org.example.state.compositestate.stopwatch;

import org.example.component.ClockSystem;
import org.example.state.compositestate.ClockState;
import org.example.utility.Time;
import org.example.component.Mode;

public abstract class StopWatchState extends ClockState {
	
	public Mode getMode() { return Mode.STOPWATCH; }

	public Time getObservedTime(ClockSystem context) {
		return context.getStopWatch().getTime(); }

}
