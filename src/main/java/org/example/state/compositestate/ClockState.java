package org.example.state.compositestate;

import org.example.utility.Time;
import org.example.component.*;
import org.example.component.ClockSystem;

public abstract class ClockState {

	protected String BUTTON_ONE_NAME;
	protected String BUTTON_TWO_NAME;
	protected String BUTTON_THREE_NAME;

	public final String getButton1Name() {
		return BUTTON_ONE_NAME;
	}

	public final String getButton2Name() {
		return BUTTON_TWO_NAME;
	}

	public final String getButton3Name() {
		return BUTTON_THREE_NAME;
	}

	public void button1Pressed(ClockSystem context) {

		// by default, pressing a button has no effect
	}

	public void button2Pressed(ClockSystem context) {
		// by default, pressing a button has no effect
	}

	public void button3Pressed(ClockSystem context) {
		// by default, pressing a button has no effect
	}

	public void entry(ClockSystem context) {
		// by default, entering a state has no effect
	}

	public void exit(ClockSystem context) {
		// by default, exiting a state has no effect
	}

	public abstract Time getObservedTime(ClockSystem context);

	public abstract Mode getMode();

	public String getIcon(ClockSystem context) {
		return "";
	}

	public String getLineInfo1(ClockSystem context) {
		return "";
	}

	public String getLineInfo2(ClockSystem context) {
		return "";
	}

	public String getLineInfo3(ClockSystem context) {
		return "";
	}
}
