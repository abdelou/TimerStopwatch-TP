package org.example.state.clocktimer;

import java.awt.Color;


import org.example.component.ClockSystem;
import org.example.state.compositestate.clocktimer.ClockTimerSettingState;

public class SetClockTimerHour extends ClockTimerSettingState {
	
	// use Singleton design pattern
	private static SetClockTimerHour instance;
	private SetClockTimerHour() { // make default constructor private
		BUTTON_ONE_NAME = "NEXT";
		BUTTON_TWO_NAME = "+";
		BUTTON_THREE_NAME = "-";
	}
	public static SetClockTimerHour getInstance() {
		if (instance==null) {
			instance = new SetClockTimerHour(); }
		return instance; }
	
	@Override
	public void button1Pressed(ClockSystem context) {
		if (context.hasClock()) {
			context.getClock().setHourColor(Color.BLACK);
			context.getClock().setMinuteColor(new Color(46,127, 189));}
		context.setState(SetClockTimerMinute.getInstance()); }

	@Override
	public void button2Pressed(ClockSystem context) {
		context.getClockTimer().getTime().increaseHour();
		context.notifyClock(); }

	@Override
	public void button3Pressed(ClockSystem context) {
		context.getClockTimer().getTime().decreaseHour();
		context.notifyClock(); }

}