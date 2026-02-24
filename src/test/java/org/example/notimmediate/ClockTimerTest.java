package org.example.notimmediate;

import org.example.component.ClockTimer;
import org.example.state.clocktimer.DisplayClockTimerOn;
import org.example.state.clocktimer.RingingTimer;
import org.example.utility.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests du Chronomètre (ClockTimer)")
class ClockTimerTest {

	private ClockTimer ct;

	@BeforeEach
	void setUp() {
		ct = new ClockTimer();
	}

	@Test
	@DisplayName("Test de l'arrêt automatique et passage à l'état de sonnerie")
	void autoStopStateTest() throws InterruptedException {
		ct.getTime().setSecond(2);
		ct.start();
		assertSame(DisplayClockTimerOn.Instance(), ct.getPowerState());

		Thread.sleep(2100);
		assertSame(RingingTimer.getInstance(), ct.getPowerState());
	}

	@Test
	@DisplayName("Test du décompte du temps (tick)")
	void tickTest() throws InterruptedException {
		ct.getTime().setMinute(1);
		ct.start();
		Thread.sleep(2100);
		ct.stop();
		assertEquals(ct.getTime(), new Time(0, 0, 58));
	}
}
