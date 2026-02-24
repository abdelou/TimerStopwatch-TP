package org.example.state.clocktimer;

import java.awt.Color;


import org.example.component.ClockSystem;
import org.example.state.compositestate.clocktimer.ClockTimerSettingState;

public class SetClockTimerSecond extends ClockTimerSettingState {
	
	// use Singleton design pattern
	private static SetClockTimerSecond instance;
	private SetClockTimerSecond() { // make default constructor private
		BUTTON_ONE_NAME = "CONFIRM";
		BUTTON_TWO_NAME = "+";
		BUTTON_THREE_NAME = "-";
	}
	public static SetClockTimerSecond Instance() {
		if (instance==null) {
			instance = new SetClockTimerSecond(); }
		return instance; }

	@Override
	public void button1Pressed(ClockSystem context) {
		if (context.hasClock()) {
			context.getClock().setSecondColor(Color.BLACK); }
		context.setState(DisplayClockTimerOff.getInstance()); }

	@Override
	public void button2Pressed(ClockSystem context) {
		context.getClockTimer().getTime().increaseSecond();
		context.notifyClock(); }

	@Override
	public void button3Pressed(ClockSystem context) {
		context.getClockTimer().getTime().decreaseSecond();
		context.notifyClock(); }

 }