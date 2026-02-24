package org.example.state.time;

import java.awt.Color;

import org.example.component.*;
import org.example.state.compositestate.time.TimeSettingState;

public class TimeHourSetting extends TimeSettingState {
	
	// use Singleton design pattern
	private static TimeHourSetting instance;
	private TimeHourSetting() { // make default constructor private
			BUTTON_ONE_NAME = "NEXT";
			BUTTON_TWO_NAME = "+";
			BUTTON_THREE_NAME = "-";
	}
	public static TimeHourSetting getInstance() {
		if (instance==null) {
			instance = new TimeHourSetting(); }
		return instance; }

	@Override
	public void button1Pressed(ClockSystem context) {
		if (context.hasClock()) {
			context.getClock().setHourColor(Color.BLACK);
			context.getClock().setMinuteColor(new Color(46,127, 189));}
		context.setState(TimeMinuteSetting.getInstance()); }

	@Override
	public void button2Pressed(ClockSystem context) {
		context.getTime().increaseHour();
		context.notifyClock(); }

	@Override
	public void button3Pressed(ClockSystem context) {
		context.getTime().decreaseHour();
		context.notifyClock(); }

}
