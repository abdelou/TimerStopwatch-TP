package org.example.state.alarm;

import java.awt.Color;

import org.example.component.*;
import org.example.state.compositestate.alarm.AlarmSettingState;

public class AlarmHourSetting extends AlarmSettingState {
	
	// use of Singleton design pattern
	private static AlarmHourSetting instance;
	private AlarmHourSetting() { // make constructor private
		BUTTON_ONE_NAME = "OK";
		BUTTON_TWO_NAME = "+";
		BUTTON_THREE_NAME = "-";
		}
	public static AlarmHourSetting getInstance() {
		if (instance==null) {
			instance = new AlarmHourSetting(); }
		return instance; }
	
	@Override
	public void button1Pressed(ClockSystem context) {
		if (context.hasClock()) {
			context.getClock().setHourColor(Color.BLACK);
			context.getClock().setMinuteColor(new Color(46,127, 189));}
		context.setState(AlarmMinuteSetting.getInstance()); }

	@Override
	public void button2Pressed(ClockSystem context) {
		context.getAlarmObserved().getTime().increaseHour();
		context.notifyClock(); }

	@Override
	public void button3Pressed(ClockSystem context) {
		context.getAlarmObserved().getTime().decreaseHour();
		context.notifyClock(); } }

