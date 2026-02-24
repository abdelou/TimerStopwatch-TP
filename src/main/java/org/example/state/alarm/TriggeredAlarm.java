package org.example.state.alarm;

import java.awt.Color;

import org.example.component.Alarm;
import org.example.component.ClockSystem;
import org.example.state.compositestate.ClockState;
import org.example.state.compositestate.alarm.AlarmSettingState;
import org.example.state.compositestate.clocktimer.ClockTimerState;

public class TriggeredAlarm extends AlarmSettingState {

	// use of Singleton design pattern
	private static TriggeredAlarm instance;

	private TriggeredAlarm() { // make constructor private
		BUTTON_ONE_NAME = "STOP ALARM";
		BUTTON_TWO_NAME = "";
		BUTTON_THREE_NAME = "";
	}

	public static TriggeredAlarm getInstance() {
		if (instance == null) {
			instance = new TriggeredAlarm();
		}
		return instance;
	}

	private static ClockState memoryState;
	private static Color memorySecondColor;
	private static Color memoryMinuteColor;
	private static Color memoryHourColor;
	private static Color memoryLineInfoOneColor;
	private static Color memoryLineInfoTwoColor;
	private static int memoryAlarmPointer;

	private static boolean triggering = false;

	@Override
	public void exit(ClockSystem context) {
		triggering = false;
	}

	@Override
	public void button1Pressed(ClockSystem context) {
		alarmConfirmedByUser(context);
	} // Take care of line info color of the graphic interface

	@Override
	public String getIcon(ClockSystem context) {
		return "!!";
	}

	public static ClockState getMemoryState() {
		return memoryState;
	}

	public static void setMemoryState(ClockTimerState newState) {
		memoryState = newState;
	}

	public static void initializeMemory(ClockSystem cs) {
		if (!triggering) {
			if (cs.hasClock()) {
				TriggeredAlarm.memoryHourColor = cs.getClock().getHourColor();
				TriggeredAlarm.memoryMinuteColor = cs.getClock().getMinuteColor();
				TriggeredAlarm.memorySecondColor = cs.getClock().getSecondColor();
				TriggeredAlarm.memoryLineInfoOneColor = cs.getClock().getLineInfoOneColor();
				TriggeredAlarm.memoryLineInfoTwoColor = cs.getClock().getLineInfoTwoColor();
				cs.getClock().setHourColor(Color.BLACK);
				cs.getClock().setMinuteColor(Color.BLACK);
				cs.getClock().setSecondColor(Color.BLACK);
			} // Take care of line info color of the graphic interface
			TriggeredAlarm.memoryAlarmPointer = cs.getAlarmsPointer();
			TriggeredAlarm.memoryState = cs.getState();
			Alarm.startRing();
			triggering = true;
		}
	}

	public static void alarmConfirmedByUser(ClockSystem cs) {
		Alarm.stopRing();
		triggering = false;
		cs.setAlarmsPointer(TriggeredAlarm.memoryAlarmPointer);
		cs.setState(memoryState);
		if (cs.hasClock()) {
			cs.getClock().setHourColor(TriggeredAlarm.memoryHourColor);
			cs.getClock().setMinuteColor(TriggeredAlarm.memoryMinuteColor);
			cs.getClock().setSecondColor(TriggeredAlarm.memorySecondColor);
			cs.getClock().setLineInfoOneColor(TriggeredAlarm.memoryLineInfoOneColor);
			cs.getClock().setLineInfoTwoColor(TriggeredAlarm.memoryLineInfoTwoColor);
		}
	}
}