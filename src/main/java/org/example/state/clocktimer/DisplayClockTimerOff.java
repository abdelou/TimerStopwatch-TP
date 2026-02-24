package org.example.state.clocktimer;

import java.awt.Color;

import org.example.component.*;
import org.example.state.compositestate.clocktimer.ClockTimerDisplayState;

public class DisplayClockTimerOff extends ClockTimerDisplayState {
	
	// use Singleton design pattern
	private static DisplayClockTimerOff instance;
	private DisplayClockTimerOff() { // make default constructor private
		BUTTON_ONE_NAME = "MODE";
		BUTTON_TWO_NAME = "SET";
		BUTTON_THREE_NAME = "ON";
	}
	public static DisplayClockTimerOff getInstance() {
		if (instance==null) {
			instance = new DisplayClockTimerOff(); }
		return instance; }
	
	@Override
	public void button1Pressed(ClockSystem context) {
		context.setState(context.getStopWatch().getPowerState()); }

	@Override
	public void button2Pressed(ClockSystem context) {
		if (context.hasClock()) {
			context.getClock().setHourColor(new Color(46,127, 189)); }
		context.setState(SetClockTimerHour.getInstance());
		context.notifyClock(); }

	@Override
	public void button3Pressed(ClockSystem context) {
		if (!context.getClockTimer().getTime().isZero()) {
			context.getClockTimer().start();
			context.setState(DisplayClockTimerOn.Instance()); } } }