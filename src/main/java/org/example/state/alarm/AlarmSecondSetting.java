package org.example.state.alarm;

import org.example.component.ClockSystem;
import org.example.state.compositestate.alarm.AlarmSettingState;

import java.awt.Color;

public class AlarmSecondSetting extends AlarmSettingState {
	
	// use of Singleton design pattern
	private static AlarmSecondSetting instance;
	private AlarmSecondSetting() { // make constructor private
		BUTTON_ONE_NAME = "OK";
		BUTTON_TWO_NAME = "+";
		BUTTON_THREE_NAME = "-";
		}
	public static AlarmSecondSetting Instance() {
		if (instance==null) {
			instance = new AlarmSecondSetting(); }
		return instance; }
	
	@Override
	public void button1Pressed(ClockSystem context) {
		if (context.hasClock()) {
			context.getClock().setSecondColor(Color.BLACK);
			context.getClock().setIconColor(new Color(46,127, 189)); }
		context.setState(AlarmActiveSetting.getInstance()); }

	@Override
	public void button2Pressed(ClockSystem context) {
		context.getAlarmObserved().getTime().increaseSecond();
		context.notifyClock(); }

	@Override
	public void button3Pressed(ClockSystem context) {
		context.getAlarmObserved().getTime().decreaseSecond();
		context.notifyClock(); } }