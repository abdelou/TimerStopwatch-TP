package org.example.state.time;

import java.awt.Color;

import org.example.component.*;
import org.example.state.compositestate.time.TimeSettingState;

public class TimeMinuteSetting extends TimeSettingState {
	
	// use Singleton design pattern
	private static TimeMinuteSetting instance;
	private TimeMinuteSetting() { // make default constructor private
			BUTTON_ONE_NAME = "NEXT";
			BUTTON_TWO_NAME = "+";
			BUTTON_THREE_NAME = "-";
	}
	public static TimeMinuteSetting getInstance() {
		if (instance==null) {
			instance = new TimeMinuteSetting(); }
		return instance; }

	@Override
	public void button1Pressed(ClockSystem context) {
		if (context.hasClock()) {
			context.getClock().setMinuteColor(Color.BLACK);
			context.getClock().setSecondColor(new Color(46,127, 189));}
		context.setState(TimeSecondSetting.getInstance()); }

	@Override
	public void button2Pressed(ClockSystem context) {
		context.getTime().increaseMinute();
		context.notifyClock(); }

	@Override
	public void button3Pressed(ClockSystem context) {
		context.getTime().decreaseMinute();
		context.notifyClock(); }
	
}