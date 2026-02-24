package org.example.component;

import org.example.state.alarm.TriggeredAlarm;
import org.example.state.clocktimer.DisplayClockTimerOff;
import org.example.state.clocktimer.DisplayClockTimerOn;
import org.example.state.clocktimer.RingingTimer;
import org.example.state.compositestate.clocktimer.ClockTimerDisplayState;
import org.example.utility.*;

public class ClockTimer implements TimeOwner {

	private ClockSystem clockSystem;
	private Time time;
	private ClockTimerDisplayState powerState;
	private TimeTimer clockTimerTimer;
	// declarer la variable isRinging dans le fichier ClockTimer.java
	private boolean isRinging = false;

	public ClockTimer() {
		time = new Time();
		powerState = DisplayClockTimerOff.getInstance();
	}

	public Time getTime() {
		return time;
	}

	public void setClockSystem(ClockSystem clock) {
		clockSystem = clock;
	}

	public void notifyClockSystem() {
		if (clockSystem != null)
			clockSystem.notifyClock();
	}

	public void notifyClockTimerIsOff() {
		if (clockSystem != null &&
				clockSystem.getState() == DisplayClockTimerOn.Instance()) {
			clockSystem.setState(powerState);
		}
		if (clockSystem != null &&
				clockSystem.getState() == TriggeredAlarm.getInstance() &&
				TriggeredAlarm.getMemoryState() == DisplayClockTimerOn.Instance()) {
			TriggeredAlarm.setMemoryState(powerState);
		}
	}

	public ClockTimerDisplayState getPowerState() {
		return powerState;
	}

	public TimeTimer getClockTimerTimer() {
		return clockTimerTimer;
	}

	public boolean isRinging() {
		return isRinging;
	}

	public void setIsRinging(boolean isRinging) {
		this.isRinging = isRinging;
	}

	public void ring() {
		Audio mySound = new Audio("/sound/bipbip.wav");
		mySound.start();
	}

	public void tick() {
		time.tickDown();
		if (time.isZero()) {
			clockTimerTimer.cancel();
			powerState = RingingTimer.getInstance();
			if (clockSystem != null) {
				clockSystem.setState(RingingTimer.getInstance());
			}
		}
		notifyClockSystem();
	}

	public void stop() {
		powerState = DisplayClockTimerOff.getInstance();
		clockTimerTimer.cancel();
	}

	public void start() {
		if (!time.isZero()) {
			powerState = DisplayClockTimerOn.Instance();
			clockTimerTimer = new TimeTimer(this);
			clockTimerTimer.start();
		}
	}
}
