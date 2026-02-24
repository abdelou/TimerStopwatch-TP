package org.example.utility;

import java.util.Timer;
import java.util.TimerTask;

public class TimeTimer extends Timer {
	
	private static class TimeTicker extends TimerTask {
		
		TimeOwner timeOwner;
		
		public TimeTicker(TimeOwner timeOwner) {
			this.timeOwner = timeOwner; }
			
		public void run() {
			timeOwner.tick(); } }
	
	private TimeTicker task;
				
	public TimeTimer(TimeOwner timeOwner) {
		task = new TimeTicker(timeOwner); }
		
	public void start() {
		this.schedule(task, 1000, 1000); } }
