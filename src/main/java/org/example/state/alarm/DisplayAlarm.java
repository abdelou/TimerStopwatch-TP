package org.example.state.alarm;

import java.awt.Color;


import org.example.component.ClockSystem;
import org.example.state.compositestate.alarm.AlarmDisplayState;

public class DisplayAlarm extends AlarmDisplayState {
	
	// use Singleton design pattern
	private static DisplayAlarm instance;
	private DisplayAlarm() { // make default constuctor private
			BUTTON_ONE_NAME = "MODE";
			BUTTON_TWO_NAME = "SET";
			BUTTON_THREE_NAME = "NEXT";
	}
	public static DisplayAlarm Instance() {
		if (instance==null) {
			instance = new DisplayAlarm(); }
		return instance; }
	
	@Override
	public void button1Pressed(ClockSystem context) {
		if (context.hasClock()) {
			context.getClock().setLineInfoOneColor(Color.BLACK);
			context.getClock().setLineInfoTwoColor(Color.BLACK); }
		context.setState(context.getDisplayState()); }

	@Override
	public void button2Pressed(ClockSystem context) {
		if (context.hasClock()) {
			context.getClock().setHourColor(new Color(46,127, 189)); }
		context.getAlarmObserved().setPowerState(false);
		context.setState(AlarmHourSetting.getInstance());
		context.notifyClock(); }

	@Override
	public void button3Pressed(ClockSystem context) {
		context.changeAlarmObserved();
		this.setClockColor(context);
		context.notifyClock(); } }