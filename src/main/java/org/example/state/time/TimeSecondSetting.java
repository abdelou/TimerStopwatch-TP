package org.example.state.time;

import java.awt.Color;

import org.example.component.*;
import org.example.state.compositestate.time.TimeSettingState;

public class TimeSecondSetting extends TimeSettingState {
	
	// use Singleton design pattern
	private static TimeSecondSetting instance;
	private TimeSecondSetting() { // make default constructor private
		BUTTON_ONE_NAME = "CONFIRM";
		BUTTON_TWO_NAME = "+";
		BUTTON_THREE_NAME = "-";
		}
	public static TimeSecondSetting getInstance() {
		if (instance==null) {
			instance = new TimeSecondSetting(); }
		return instance; }

	@Override
	public void button1Pressed(ClockSystem context) {
		if (context.hasClock()) {
			context.getClock().setSecondColor(Color.BLACK); }
		context.setState(context.getDisplayState());
		context.setTimer(); }

	@Override
	public void button2Pressed(ClockSystem context) {
		context.getTime().increaseSecond();
		context.notifyClock(); }

	@Override
	public void button3Pressed(ClockSystem context) {
		context.getTime().decreaseSecond();
		context.notifyClock(); }

}