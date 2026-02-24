package states.stopwatch;

import states.ClockState;

public class ResetStopwatch extends AbstractStopwatch {

	private ResetStopwatch() {
	}

	private static ResetStopwatch instance = null;

	public static ResetStopwatch Instance() {
		if (instance == null)
			instance = new ResetStopwatch();
		return instance;
	}

	@Override
	public ClockState up() {
		return transition(RunningStopwatch.Instance());
	}

	@Override
	public String getUpText() {
		return "run";
	}

	@Override
	protected ClockState doIt() {
		return this;
	}

	public String getDisplayString() {
		return "totalTime = " + totalTime;
	}
}
