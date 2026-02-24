package org.example.state.clocktimer;

import org.example.component.*;
import org.example.state.compositestate.clocktimer.ClockTimerDisplayState;

public class DisplayClockTimerOn extends ClockTimerDisplayState {
	
	// use Singleton design pattern
	private static DisplayClockTimerOn instance;
	private DisplayClockTimerOn() { // make default constructor private
		BUTTON_ONE_NAME = "MODE";
		BUTTON_TWO_NAME = "";
		BUTTON_THREE_NAME = "OFF";
	}
	public static DisplayClockTimerOn Instance() {
		if (instance==null) {
			instance = new DisplayClockTimerOn(); }
		return instance; }

	@Override
	public void button1Pressed(ClockSystem context) {
		context.setState(context.getStopWatch().getPowerState()); }

	@Override
	public void button3Pressed(ClockSystem context) {
		context.getClockTimer().stop();
		context.setState(DisplayClockTimerOff.getInstance()); } }