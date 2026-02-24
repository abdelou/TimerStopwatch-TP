package org.example.state.alarm;

import java.awt.Color;

import org.example.component.*;
import org.example.state.compositestate.alarm.AlarmSettingState;

public class AlarmActiveSetting extends AlarmSettingState {
	
	// use of Singleton design pattern
	private static AlarmActiveSetting instance;
	private AlarmActiveSetting() { // make constructor private
		BUTTON_ONE_NAME = "CONFIRM";
		BUTTON_TWO_NAME = "ON/OFF";
		BUTTON_THREE_NAME = "";
	}
	public static AlarmActiveSetting getInstance() {
		if (instance==null) {
			instance = new AlarmActiveSetting(); }
		return instance; }
	
	@Override
	public void button1Pressed(ClockSystem context) {
		if (context.hasClock()) {
			context.getClock().setIconColor(Color.BLACK); }
		context.setState(DisplayAlarm.Instance()); }

	@Override
	public void button2Pressed(ClockSystem context) {
		context.getAlarmObserved().switchPowerState();
		context.notifyClock(); } }