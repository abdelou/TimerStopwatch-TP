package org.example.state.clocktimer;

import java.awt.Color;


import org.example.component.ClockSystem;
import org.example.state.compositestate.clocktimer.ClockTimerSettingState;

public class SetClockTimerMinute extends ClockTimerSettingState {
	
	// use Singleton design pattern
	private static SetClockTimerMinute instance;
	private SetClockTimerMinute() { // make default constructor private
		BUTTON_ONE_NAME = "NEXT";
		BUTTON_TWO_NAME = "+";
		BUTTON_THREE_NAME = "-";
	}
	public static SetClockTimerMinute getInstance() {
		if (instance==null) {
			instance = new SetClockTimerMinute(); }
		return instance; }

	@Override
	public void button1Pressed(ClockSystem context) {
		if (context.hasClock()) {
			context.getClock().setMinuteColor(Color.BLACK);
			context.getClock().setSecondColor(new Color(46,127, 189));}
		context.setState(SetClockTimerSecond.Instance()); }

	@Override
	public void button2Pressed(ClockSystem context) {
		context.getClockTimer().getTime().increaseMinute();
		context.notifyClock(); }

	@Override
	public void button3Pressed(ClockSystem context) {
		context.getClockTimer().getTime().decreaseMinute();
		context.notifyClock(); }

 }