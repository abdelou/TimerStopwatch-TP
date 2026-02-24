package org.example.component;

import org.example.utility.Audio;
import org.example.utility.Time;

import java.util.Timer;
import java.util.TimerTask;

public class Alarm {

	private Time time;
	private Boolean powerState;
	private static Timer ringer;

	public Alarm() {
		time = new Time();
		powerState = false;
	}

	public Time getTime() {
		return time;
	}

	public Boolean getPowerState() {
		return powerState;
	}

	public void setPowerState(Boolean state) {
		powerState = state;
	}

	public void switchPowerState() {
		powerState = !powerState;
	}

	public static void startRing() {
		ringer = new Timer();
		TimerTask ringerTask = new TimerTask() {
			public void run() {
				Audio mySound = new Audio("/sound/alarmsound.wav");
				mySound.run();
			}
		};
		ringer.schedule(ringerTask, 0, 1000);
	}

	public static void stopRing() throws NullPointerException {
		ringer.cancel();
	}
}
