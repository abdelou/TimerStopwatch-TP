package org.example.component;

import org.example.GUI.ClockFrame;
import org.example.state.alarm.TriggeredAlarm;
import org.example.state.compositestate.ClockState;
import org.example.state.compositestate.time.TimeDisplayState;
import org.example.state.time.DisplayNormalTime;
import org.example.utility.PointedArray;
import org.example.utility.Time;
import org.example.utility.TimeOwner;
import org.example.utility.TimeTimer;

import java.awt.*;

public class ClockSystem implements TimeOwner {

	private ClockFrame clock;
	private Time time;
	private StopWatch stopWatch;
	private ClockTimer clockTimer;
	private PointedArray<Alarm> alarms;
	private ClockState currentState;
	private TimeDisplayState displayState;
	private TimeTimer timer;

	public ClockSystem() {
		time = new Time();
		stopWatch = new StopWatch();
		stopWatch.setClockSystem(this);
		clockTimer = new ClockTimer();
		clockTimer.setClockSystem(this);
		Alarm[] alarmsArray = { new Alarm(), new Alarm() };
		alarms = new PointedArray<>(alarmsArray);
		currentState = DisplayNormalTime.Instance();
		displayState = DisplayNormalTime.Instance();
		timer = new TimeTimer(this);
		timer.start();
	}

	public Boolean hasClock() {
		return (clock != null);
	}

	public void setClock(ClockFrame c) {
		clock = c;
	}

	public ClockFrame getClock() {
		return clock;
	}

	public void notifyClock() {
		if (hasClock())
			clock.update();
	}

	public Integer getAlarmsPointer() {
		return alarms.getPointer();
	}

	public void setAlarmsPointer(int i) throws IndexOutOfBoundsException {
		alarms.setPointer(i);
	}

	public void tick() {
		time.tickUp();
		// During each tick, cycle over all alarms to check whether they need to be
		// triggered...
		// there is more opportunity for refactoring below, the code is nearly
		// identical!
		if (getAlarm(0).getPowerState() && getAlarm(0).getTime().equals(time)) { // Watching if the first alarm has to
																					// ring
			TriggeredAlarm.initializeMemory(this);
			alarms.setPointer(0);
			getAlarm(0).switchPowerState();
			setState(TriggeredAlarm.getInstance());
			if (hasClock()) {
				clock.setLineInfoOneColor(new Color(46, 127, 189));
				clock.setLineInfoTwoColor(Color.BLACK);
			}
		}
		if (getAlarm(1).getPowerState() && getAlarm(1).getTime().equals(time)) { // Watching if the second alarm has to
																					// ring
			TriggeredAlarm.initializeMemory(this);
			alarms.setPointer(1);
			getAlarm(1).switchPowerState();
			setState(TriggeredAlarm.getInstance());
			if (hasClock()) {
				clock.setLineInfoTwoColor(new Color(46, 127, 189));
				clock.setLineInfoOneColor(Color.BLACK);
			}
		}
		notifyClock();
	}

	public void setTimer() {
		timer = new TimeTimer(this);
		timer.start();
	}

	public void button1Pressed() {
		currentState.button1Pressed(this);
	}

	public void button2Pressed() {
		currentState.button2Pressed(this);
	}

	public void button3Pressed() {
		currentState.button3Pressed(this);
	}

	public void setState(ClockState state) {
		if (currentState != null) {
			currentState.exit(this);
		}
		currentState = state;
		if (currentState != null) {
			currentState.entry(this);
		}
		notifyClock();
	}

	public void setDisplayState(TimeDisplayState state) {
		displayState = state;
		notifyClock();
	}

	public Time getObservedTime() {
		return currentState.getObservedTime(this);
	}

	public Alarm getAlarmObserved() {
		return alarms.getPointedItem();
	}

	public void changeAlarmObserved() {
		alarms.changePointer();
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time t) {
		time = t;
	}

	public TimeTimer getTimer() {
		return timer;
	}

	public Alarm getAlarm(int i) { // start counting from 0 for first alarm!
		return alarms.get(i);
	}

	public StopWatch getStopWatch() {
		return stopWatch;
	}

	public ClockTimer getClockTimer() {
		return clockTimer;
	}

	public TimeDisplayState getDisplayState() {
		return displayState;
	}

	public ClockState getState() {
		return currentState;
	}

	public String getIcon() {
		return currentState.getIcon(this);
	}

	public String getLineInfo1() {
		return currentState.getLineInfo1(this);
	}

	public String getLineInfo2() {
		return currentState.getLineInfo2(this);
	}

	public String getLineInfo3() {
		return currentState.getLineInfo3(this);
	}

	public Mode getMode() {
		return currentState.getMode();
	}
}
