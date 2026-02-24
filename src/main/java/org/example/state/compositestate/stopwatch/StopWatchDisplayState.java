package org.example.state.compositestate.stopwatch;

import org.example.component.*;
import org.example.utility.Time;

public abstract class StopWatchDisplayState extends StopWatchState {

	String emptySpaces = "      ";

	@Override
	public String getLineInfo1(ClockSystem context) {
		Time time = context.getStopWatch().getMemoryTime().get(2);
		if (time!=null) {
			return emptySpaces+time; }
		else {
			return ""; } }

	@Override
	public String getLineInfo2(ClockSystem context) {
		Time time = context.getStopWatch().getMemoryTime().get(1);
		if (time!=null) {
			return emptySpaces+time; }
		else {
			return ""; } }

	@Override
	public String getLineInfo3(ClockSystem context) {
		Time time = context.getStopWatch().getMemoryTime().get(0);
		if (time!=null) {
			return emptySpaces+time; }
		else {
			return ""; } }
	
}
