package org.example.immediate.component;

import org.example.component.ClockSystem;

import org.example.state.alarm.DisplayAlarm;
import org.example.state.clocktimer.DisplayClockTimerOff;
import org.example.state.stopwatch.DisplayStopWatchOff;
import org.example.state.time.DisplayBritishTime;
import org.example.state.time.DisplayNormalTime;
import org.example.utility.Time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests du Système d'Horloge (ClockSystem)")
class ClockSystemTest {

	private ClockSystem cs;

	@BeforeEach
	void setUp() {
		cs = new ClockSystem();
	}

	@Test
	@DisplayName("Test du basculement des modes de fonctionnement")
	void getModeStringTest() {
		assertEquals("WATCH", cs.getMode().toString());
		cs.button1Pressed();
		assertEquals("CLOCKTIMER", cs.getMode().toString());
		cs.button1Pressed();
		assertEquals("STOPWATCH", cs.getMode().toString());
		cs.button1Pressed();
		assertEquals("ALARM", cs.getMode().toString());
		cs.button1Pressed();
		assertEquals("WATCH", cs.getMode().toString());
	}

	@Test
	@DisplayName("Test de l'icône AM/PM en mode WATCH")
	void getIconTestOne() {
		assertEquals("", cs.getIcon());
		cs.button3Pressed();
		assertEquals(" AM", cs.getIcon());
		cs.setTime(new Time(20, 0, 0));
		assertEquals(" PM", cs.getIcon());
	}

	@Test
	@DisplayName("Test de l'icône ON/OFF en mode ALARM")
	void getIconTestTwo() {
		cs.button1Pressed();
		cs.button1Pressed();
		cs.button1Pressed();
		assertEquals(" OFF", cs.getIcon());
		cs.getAlarmObserved().switchPowerState();
		assertEquals(" ON", cs.getIcon());
	}

	@Test
	@DisplayName("Test de la sélection des alarmes")
	void getAlarmObservedTest() {
		assertEquals(cs.getAlarm(0), cs.getAlarmObserved());
		cs.changeAlarmObserved();
		assertEquals(cs.getAlarm(1), cs.getAlarmObserved());
	}

	@Test
	@DisplayName("Test de l'affichage PM (convertisseur 12h/24h)")
	void displayPMTestOne() {
		cs.setTime(new Time(20, 00, 00));
		cs.button3Pressed();
		assertEquals(8, cs.getObservedTime().getHour());
	}

	@Test
	@DisplayName("Test de l'affichage Minuit (convertisseur 12h/24h)")
	void displayPMTestTwo() {
		cs.setTime(new Time(00, 00, 00));
		cs.button3Pressed();
		assertEquals(12, cs.getObservedTime().getHour());
	}

	/*
	 * The next test controls if the order of state is correct :
	 * Time, ClockTimer, StopWatch, Alarm
	 */

	@Test
	@DisplayName("Test du cycle complet des changements d'états")
	void testSwitch() {
		assertSame(DisplayNormalTime.Instance(), cs.getState());
		// Alternative test at class level would be:
		// assertTrue(DisplayNormalTime.class,cs.getState().getClass());

		cs.button3Pressed();
		assertSame(DisplayBritishTime.getInstance(), cs.getState());

		cs.button1Pressed();
		assertSame(DisplayClockTimerOff.getInstance(), cs.getState());

		cs.button1Pressed();
		assertSame(DisplayStopWatchOff.getInstance(), cs.getState());

		cs.button1Pressed();
		assertSame(DisplayAlarm.Instance(), cs.getState());

		cs.button1Pressed();
		assertSame(DisplayBritishTime.getInstance(), cs.getState());

		cs.button3Pressed();
		assertSame(DisplayNormalTime.Instance(), cs.getState());
	}

	@Test
	@DisplayName("Test de l'observation du temps selon le mode courant")
	void getObservedTimeTest() {
		assertEquals(cs.getObservedTime(), cs.getTime());
		cs.button1Pressed();
		assertEquals(cs.getObservedTime(), cs.getClockTimer().getTime());
		cs.button1Pressed();
		assertEquals(cs.getObservedTime(), cs.getStopWatch().getTime());
		cs.button1Pressed();
		assertEquals(cs.getObservedTime(), cs.getAlarm(0).getTime()); // first alarm
		cs.button3Pressed();
		assertEquals(cs.getObservedTime(), cs.getAlarm(1).getTime()); // second alarm
	}
}