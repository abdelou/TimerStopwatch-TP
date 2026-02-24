package org.example.state.compositestate.alarm;

import java.awt.Color;

import org.example.component.*;
import org.example.state.compositestate.ClockState;
import org.example.utility.Time;

public abstract class AlarmState extends ClockState {

	public Mode getMode() { return Mode.ALARM; }

	public Time getObservedTime(ClockSystem context) {
		return context.getAlarmObserved().getTime(); }

	public void setClockColor(ClockSystem context) {
		if (context.hasClock()) {
			if (context.getAlarmsPointer()==0) { // first alarm
				context.getClock().setLineInfoOneColor(new Color(46,127, 189));
				context.getClock().setLineInfoTwoColor(Color.BLACK); }
			else { // second alarm
				context.getClock().setLineInfoTwoColor(new Color(46,127, 189));
				context.getClock().setLineInfoOneColor(Color.BLACK); } } }

	@Override
	public String getIcon(ClockSystem context) {
		if (context.getAlarmObserved().getPowerState()) {
			return " ON"; }
		else {
			return " OFF"; } }

	@Override
	public String getLineInfo1(ClockSystem context) {
		String alarmState;
		if (context.getAlarm(0).getPowerState()) {
			alarmState = " (ON) "; }
		else {
			alarmState = " (OFF)"; }
		return "    First Alarm : " + context.getAlarm(0).getTime().toString() + alarmState; }

	@Override
	public String getLineInfo2(ClockSystem context) {
		String alarmState;
		if (context.getAlarm(1).getPowerState()) {
			alarmState = " (ON) "; }
		else {
			alarmState = " (OFF)"; }
		return "   Second Alarm : " + context.getAlarm(1).getTime().toString() + alarmState; }
	
}