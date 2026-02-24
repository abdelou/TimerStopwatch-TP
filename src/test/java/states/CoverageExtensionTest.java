package states;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import states.timer.*;
import states.stopwatch.*;

@DisplayName("Tests d'extension de couverture (Point 4)")
class CoverageExtensionTest {

    private Context context;

    @BeforeEach
    void setup() {
        context = new Context();
        AbstractTimer.resetInitialValues();
        AbstractStopwatch.resetInitialValues();
        // Reset singletons to known initial states if possible,
        // but here we rely on Context constructor which sets IdleTimer.Instance()
    }

    @Nested
    @DisplayName("Tests d'extension du Timer")
    class TimerExtension {

        @Test
        @DisplayName("Test de l'incrément +5 dans SetTimer")
        void testSetTimerIncrement5() {
            context.right(); // Idle -> SetTimer
            context.tick(); // memTimer becomes 1
            assertSame(SetTimer.Instance(), context.currentState);

            context.up(); // inc 5 -> 1 + 5 = 6
            assertEquals(6, AbstractTimer.getMemTimer());
            assertEquals("memTimer = 6", context.getDisplayText());
        }

        @Test
        @DisplayName("Test du reset de mémoire dans SetTimer")
        void testSetTimerReset() {
            context.right(); // Idle -> SetTimer
            context.tick(); // memTimer = 1
            context.up(); // memTimer = 6
            assertEquals(6, AbstractTimer.getMemTimer());

            context.left(); // reset
            assertEquals(0, AbstractTimer.getMemTimer());
        }

        @Test
        @DisplayName("Test de l'incrément automatique dans SetTimer (doIt)")
        void testSetTimerAutoIncrement() {
            context.right();
            context.tick(); // memTimer = 1
            assertEquals(1, AbstractTimer.getMemTimer());
            context.tick(); // memTimer = 2
            assertEquals(2, AbstractTimer.getMemTimer());
        }

        @Test
        @DisplayName("Test du bouton stop dans RunningTimer")
        void testRunningTimerStop() {
            // Setup: Idle -> Set -> Running
            context.right();
            context.tick();
            context.up(); // memTimer = 5
            context.right();
            context.tick(); // -> Idle
            context.up(); // -> Running
            assertSame(RunningTimer.Instance(), context.currentState);

            context.right(); // stop
            assertSame(IdleTimer.Instance(), context.currentState);
        }

        @Test
        @DisplayName("Test de reprise (run) dans PausedTimer")
        void testPausedTimerResume() {
            // Setup: Running -> Paused
            context.right();
            context.tick();
            context.up();
            context.right();
            context.tick();
            context.up();
            context.up(); // pause
            assertSame(PausedTimer.Instance(), context.currentState);

            context.up(); // run
            assertSame(RunningTimer.Instance(), context.currentState);
        }
    }

    @Nested
    @DisplayName("Tests d'extension du Stopwatch")
    class StopwatchExtension {

        @Test
        @DisplayName("Test du timeout automatique de Laptime (5s)")
        void testLaptimeTimeout() {
            context.left(); // -> ResetStopwatch
            context.up(); // -> RunningStopwatch
            context.up(); // -> LaptimeStopwatch
            assertSame(LaptimeStopwatch.Instance(), context.currentState);

            // 5 ticks for timeout
            for (int i = 0; i < 5; i++) {
                context.tick();
            }
            assertSame(RunningStopwatch.Instance(), context.currentState);
        }

        @Test
        @DisplayName("Test de l'unsplit manuel dans Laptime")
        void testLaptimeUnsplit() {
            context.left();
            context.up();
            context.up();
            assertSame(LaptimeStopwatch.Instance(), context.currentState);

            context.up(); // unsplit
            assertSame(RunningStopwatch.Instance(), context.currentState);
        }
    }

    @Nested
    @DisplayName("Tests des textes et getters du contexte")
    class UIAndContextGetters {

        @Test
        @DisplayName("Vérification des textes de boutons et états")
        void testButtonTexts() {
            // IdleTimer
            assertEquals("run", context.getUpText());
            assertEquals("set", context.getRightText());
            assertEquals("change mode", context.getLeftText());
            assertEquals("timer", context.getModeText());
            assertEquals("IdleTimer", context.getStateText());

            // SetTimer
            context.right();
            context.tick();
            assertEquals("inc 5", context.getUpText());
            assertEquals("done", context.getRightText());
            assertEquals("reset", context.getLeftText());

            // RunningTimer
            context.right();
            context.tick(); // back to Idle
            AbstractTimer.resetInitialValues();
            context.right();
            context.tick();
            context.up();
            context.right();
            context.tick();
            context.up(); // run
            assertEquals("pause", context.getUpText());
            assertEquals("stop", context.getRightText());

            // ResetStopwatch
            context.left();
            assertEquals("run", context.getUpText());
            assertEquals("(unused)", context.getRightText());
        }
    }
}
