package org.example.component;


import org.example.state.compositestate.stopwatch.StopWatchDisplayState;
import org.example.state.stopwatch.DisplayStopWatchOff;
import org.example.state.stopwatch.DisplayStopWatchOn;
import org.example.utility.MemoryTimeArray;
import org.example.utility.Time;
import org.example.utility.TimeOwner;
import org.example.utility.TimeTimer;

public class StopWatch implements TimeOwner {
	
	private ClockSystem clockSystem;
	private Time time;
	private StopWatchDisplayState powerState;
	private MemoryTimeArray memoryTime;
	private TimeTimer stopWatchTimer;
	
	public StopWatch() {
		powerState = DisplayStopWatchOff.getInstance();
		time = new Time();
		memoryTime = new MemoryTimeArray(3); }
	
	public void setClockSystem(ClockSystem clockSystem) {
		this.clockSystem = clockSystem; }
	
	public void notifyClockSystem() {
		if (clockSystem != null) {
			clockSystem.notifyClock(); } }

	public Time getTime() {
		return time; }

	public StopWatchDisplayState getPowerState() {
		return powerState; }

	public MemoryTimeArray getMemoryTime() {
		return memoryTime; }

	public TimeTimer getStopWatchTimer() {
		return stopWatchTimer; }

	public void addMemoryTime() {
		memoryTime.add(new Time(time.getHour(), time.getMinute(), time.getSecond()));
		notifyClockSystem(); }

	public void reset() {
		time = new Time();
		memoryTime.clear();
		notifyClockSystem(); }

	public void tick() {
		time.tickUp();
		notifyClockSystem(); }
		
	public void start() {
		powerState = DisplayStopWatchOn.Instance();
		stopWatchTimer = new TimeTimer(this);
		stopWatchTimer.start(); }
		
	public void stop() {
		powerState = DisplayStopWatchOff.getInstance();
		stopWatchTimer.cancel(); } }
