package org.example.state.stopwatch;

import org.example.component.*;
import org.example.state.alarm.DisplayAlarm;
import org.example.state.compositestate.stopwatch.StopWatchDisplayState;

public class DisplayStopWatchOff extends StopWatchDisplayState {

	// use Singleton design pattern
	private static DisplayStopWatchOff instance;
	private DisplayStopWatchOff() { // make default constructor private
		BUTTON_ONE_NAME = "MODE";
		BUTTON_TWO_NAME = "RESET";
		BUTTON_THREE_NAME = "ON";
	}
	public static DisplayStopWatchOff getInstance() {
		if (instance==null) {
			instance = new DisplayStopWatchOff(); }
		return instance; }

	@Override
	public void button1Pressed(ClockSystem context) {
		DisplayAlarm state = DisplayAlarm.Instance(); 
		state.setClockColor(context);
		context.setState(state); }

	@Override
	public void button2Pressed(ClockSystem context) {
		context.getStopWatch().reset();	}

	@Override
	public void button3Pressed(ClockSystem context) {
		context.getStopWatch().start();
		context.setState(DisplayStopWatchOn.Instance()); }

}