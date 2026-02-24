package org.example.state.alarm;

import java.awt.Color;

import org.example.component.ClockSystem;
import org.example.state.compositestate.alarm.AlarmSettingState;

public class AlarmMinuteSetting extends AlarmSettingState {
	
	// use of Singleton design pattern
	private static AlarmMinuteSetting instance;
	private AlarmMinuteSetting() { // make constructor private
			BUTTON_ONE_NAME = "OK";
			BUTTON_TWO_NAME = "+";
			BUTTON_THREE_NAME = "-";
			}
	public static AlarmMinuteSetting getInstance() {
		if (instance==null) {
			instance = new AlarmMinuteSetting(); }
		return instance; }
	
	@Override
	public void button1Pressed(ClockSystem context) {
		if (context.hasClock()) {
			context.getClock().setMinuteColor(Color.BLACK);
			context.getClock().setSecondColor(new Color(46,127, 189));}
		context.setState(AlarmSecondSetting.Instance()); }

	@Override
	public void button2Pressed(ClockSystem context) {
		context.getAlarmObserved().getTime().increaseMinute();
		context.notifyClock(); }

	@Override
	public void button3Pressed(ClockSystem context) {
		context.getAlarmObserved().getTime().decreaseMinute();
		context.notifyClock(); } }

