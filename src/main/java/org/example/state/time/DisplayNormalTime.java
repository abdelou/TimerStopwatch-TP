package org.example.state.time;

import org.example.component.*;
import org.example.state.compositestate.time.TimeDisplayState;
import org.example.utility.Time;

public class DisplayNormalTime extends TimeDisplayState {
	
	// use Singleton design pattern
	private static DisplayNormalTime instance;
	private DisplayNormalTime() { // make default constructor private
			BUTTON_ONE_NAME = "MODE";
			BUTTON_TWO_NAME = "SET";
			BUTTON_THREE_NAME = "DISPLAY MODE";
	}

	public static DisplayNormalTime Instance() {
		if (instance==null) {
			instance = new DisplayNormalTime(); }
		return instance; }


	@Override
	public void button3Pressed(ClockSystem context) {
		context.setDisplayState(DisplayBritishTime.getInstance()) ;
		context.setState(DisplayBritishTime.getInstance()); }

	public Time getObservedTime(ClockSystem context) {
		return context.getTime(); } }
