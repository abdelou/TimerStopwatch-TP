package org.example.notimmediate;

import org.example.component.ClockSystem;
import org.example.state.alarm.TriggeredAlarm;
import org.example.state.clocktimer.DisplayClockTimerOff;
import org.example.state.clocktimer.DisplayClockTimerOn;
import org.example.state.clocktimer.RingingTimer;
import org.example.state.stopwatch.DisplayStopWatchOff;
import org.example.state.stopwatch.DisplayStopWatchOn;
import org.example.state.time.DisplayNormalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("Tests des Scénarios Systèmes (ScenariTest)")
class ClockSystemScenariTest {

	private ClockSystem cs;

	@BeforeEach
	void setUp() {
		cs = new ClockSystem();
	}

	/*
	 * The next test controls if this scenario(1) is correct :
	 * - Switch in ClockTimer
	 * - Put 2seconds, start the ClockTimer and so switch in DisplayClockTimerOn
	 * - Switch in another state and waiting for MORE than 2sec
	 * - Switch in ClockTimer and being in DisplayClockTimerOff
	 */

	@Test
	@DisplayName("Scénario 1 : Chronomètre - Réglage, démarrage, attente hors vue et arrêt automatique")
	void scenarioOneTest() throws InterruptedException {

		assertSame(DisplayNormalTime.Instance(), cs.getState());

		cs.button1Pressed();

		// In ClockTimer
		assertSame(DisplayClockTimerOff.getInstance(), cs.getState());

		cs.getClockTimer().getTime().setSecond(2);
		cs.button3Pressed();

		cs.button1Pressed();
		cs.button1Pressed();
		cs.button1Pressed();

		// In DisplayTime
		assertSame(DisplayNormalTime.Instance(), cs.getState());

		// wait for more than 2 seconds to ensure timeout happens
		Thread.sleep(2100);

		cs.button1Pressed();

		assertSame(DisplayClockTimerOff.getInstance(), cs.getState());
	}

	/*
	 * The next test controls if this scenario(2) is correct :
	 * - Switch in ClockTimer
	 * - Put 2seconds, start the ClockTimer and so switch in DisplayClockTimerOn
	 * - Switch in another state and waiting for LESS than 2sec
	 * - Switch in ClockTimer and being in DisplayClockTimerOn
	 */

	@Test
	@DisplayName("Scénario 2 : Chronomètre - Réglage, démarrage, attente courte hors vue et reprise")
	void scenarioTwoTest() throws InterruptedException {

		assertSame(DisplayNormalTime.Instance(), cs.getState());

		cs.button1Pressed();

		// In ClockTimer
		assertSame(DisplayClockTimerOff.getInstance(), cs.getState());

		cs.getClockTimer().getTime().setSecond(2);
		cs.button3Pressed();

		cs.button1Pressed();
		cs.button1Pressed();
		cs.button1Pressed();

		// In DisplayTime
		assertSame(DisplayNormalTime.Instance(), cs.getState());

		// wait for LESS than 2 seconds
		Thread.sleep(1000);

		cs.button1Pressed();

		assertSame(DisplayClockTimerOn.Instance(), cs.getState(), "this is a flaky test location");
	}

	/*
	 * The next test controls if this scenario(3) is correct :
	 * - Switch in StopWatch
	 * - Switch in DisplayStopWatchOn
	 * - Switch in another state and waiting for 1sec
	 * - Switch in StopWatch and being in DisplayStopWatchOn
	 */

	@Test
	@DisplayName("Scénario 3 : Chronomètre de sport - Démarrage, changement de mode et retour")
	void scenarioThreeTest() throws InterruptedException {

		assertSame(DisplayNormalTime.Instance(), cs.getState());

		cs.button1Pressed();
		cs.button1Pressed();

		// In StopWatchState
		assertSame(DisplayStopWatchOff.getInstance(), cs.getState());

		cs.button3Pressed();

		cs.button1Pressed();
		cs.button1Pressed();
		cs.button1Pressed();

		// In ClockTimer
		assertSame(DisplayClockTimerOff.getInstance(), cs.getState());

		// wait for 1 sec
		Thread.sleep(1000);

		cs.button1Pressed();

		assertSame(DisplayStopWatchOn.Instance(), cs.getState());
	}

	/*
	 * The next test controls if this scenario(4) is correct :
	 * - Set Alarm at 00:00:02
	 * - Waiting for 2sec
	 * - Alarm is ringing (cs in TriggeredAlarm) ----> First test
	 * - Press button one
	 * - cs is not ringing anymore ----> second test
	 */

	@Test
	@DisplayName("Scénario 4 : Alarme - Réglage, sonnerie et arrêt par l'utilisateur")
	void scenarioFourTest() throws InterruptedException {

		assertSame(DisplayNormalTime.Instance(), cs.getState());

		cs.getAlarm(0).getTime().setSecond(2);
		cs.getAlarm(0).setPowerState(true);

		// wait for at least 2 seconds
		Thread.sleep(2500);

		assertSame(TriggeredAlarm.getInstance(), cs.getState());

		cs.button1Pressed();

		assertNotSame(TriggeredAlarm.getInstance(), cs.getState());
	}

	/*
	 * The next test controls if this scenario(5) is correct :
	 * - Set Alarm at 00:00:02
	 * - Switch in DisplayClockTimerOff
	 * - Alarm ring
	 * - Press button one
	 * - cs return in DisplayClockTimerOff
	 */

	@Test
	@DisplayName("Scénario 5 : Alarme - Sonnerie pendant le mode Chronomètre et retour au mode précédent")
	void scenarioFiveTest() throws InterruptedException {

		assertSame(DisplayNormalTime.Instance(), cs.getState());

		cs.getAlarm(0).getTime().setSecond(2);
		cs.getAlarm(0).setPowerState(true);

		cs.button1Pressed();

		// In ClockTimer
		assertSame(DisplayClockTimerOff.getInstance(), cs.getState());

		// wait for at least 2 seconds
		Thread.sleep(2500);

		// Alarm is triggering

		cs.button1Pressed();

		assertSame(DisplayClockTimerOff.getInstance(), cs.getState());

	}

	/*
	 * The next test controls if this scenario(6) is correct :
	 * - Set Alarm at 00:00:01
	 * - Switch in DisplayClockTimerOff
	 * - Put 2sec to ClockTimer and switch it on
	 * - cs is in DisplayClockTimerOn ----> First test
	 * - Wait for 2.1sec
	 * - Press button one
	 * - cs return in DisplayClockTimerOff ----> Second test
	 * 
	 * This this is really useful because it controls if the memoryState of
	 * TriggeredAlarm
	 * is aware of the ClockTimer's change of state
	 */

	@Test
	@DisplayName("Scénario 6 : Conflit Alarme/Chronomètre - Le chronomètre termine pendant l'alarme")
	void scenarioSixTest() throws InterruptedException {

		assertSame(DisplayNormalTime.Instance(), cs.getState());

		cs.getAlarm(0).getTime().setSecond(1);
		cs.getAlarm(0).setPowerState(true);

		cs.button1Pressed();

		// In ClockTimer
		assertSame(DisplayClockTimerOff.getInstance(), cs.getState());

		cs.getClockTimer().getTime().setSecond(2);
		cs.button3Pressed();

		assertSame(DisplayClockTimerOn.Instance(), cs.getState());

		// wait for at least 2 seconds
		Thread.sleep(2500);

		// Timer finishes and overrides Alarm
		assertSame(RingingTimer.getInstance(), cs.getState());

		cs.button1Pressed();

		assertSame(DisplayClockTimerOff.getInstance(), cs.getState(), "this is a flaky test location");
	}

	/*
	 * The next test controls if this scenario(7) is correct :
	 * - Set first alarm at 00:00:01
	 * - Set second alarm at 00:00:02
	 * - Switch in ClockTimerState
	 * - Wait that both alarm ring
	 * - Stop alarm
	 * - cs is in ClockTimerState
	 * 
	 * This is a really useful test because it controls if the second alarm
	 * triggered doesn't
	 * take TriggeredAlarm as a memoryState (else, we got a cycle...)
	 */

	@Test
	@DisplayName("Scénario 7 : Alarme - Sonneries consécutives de deux alarmes")
	void scenarioSevenTest() throws InterruptedException {

		assertSame(DisplayNormalTime.Instance(), cs.getState());

		// first alarm:
		cs.getAlarm(0).getTime().setSecond(1);
		cs.getAlarm(0).setPowerState(true);
		// second alarm:
		cs.getAlarm(1).getTime().setSecond(2);
		cs.getAlarm(1).setPowerState(true);

		cs.button1Pressed();

		// In ClockTimer
		assertSame(DisplayClockTimerOff.getInstance(), cs.getState());

		// wait for at least 2 seconds to trigger alarm
		Thread.sleep(2500);

		// Alarm is triggering
		assertSame(TriggeredAlarm.getInstance(), cs.getState());

		cs.button1Pressed();

		assertSame(DisplayClockTimerOff.getInstance(), cs.getState());
	}

	/*
	 * The next test controls if this scenario(8) is correct :
	 * - Set alarm at 01:00:00
	 * - Set time at 00:59:59
	 * - Wait for 1sec
	 * - Alarm is ringing
	 * 
	 * This is a useful test because it checks if the time modification doesn't
	 * influence
	 * the alarm
	 */

	@Test
	@DisplayName("Scénario 8 : Alarme - Influence du réglage de l'heure sur le déclenchement")
	void scenarioEigthTest() throws InterruptedException {

		assertSame(DisplayNormalTime.Instance(), cs.getState());

		cs.getAlarm(0).getTime().setHour(1);
		cs.getAlarm(0).setPowerState(true);

		cs.getTime().setMinute(59);
		cs.getTime().setSecond(59);

		// wait for at least 1 second
		Thread.sleep(1500);

		// Alarm is triggering
		assertSame(TriggeredAlarm.getInstance(), cs.getState());
	}
}
