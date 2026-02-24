package states;

import io.cucumber.java.fr.*;
import static org.junit.jupiter.api.Assertions.*;

import states.timer.*;
import states.stopwatch.*;

public class StepDefinitions {

    private Context context;

    @Etantdonnéque("je suis en mode {string}")
    public void je_suis_en_mode(String mode) {
        context = new Context();
        AbstractTimer.resetInitialValues();
        AbstractStopwatch.resetInitialValues();
        assertEquals(mode.toLowerCase(), context.getModeText().toLowerCase());
    }

    @Etantdonnéque("le minuteur est à l'état {string}")
    public void le_minuteur_est_à_l_état(String state) {
        assertEquals(state, context.getStateText());
    }

    @Quand("j'appuie sur le bouton {string}")
    public void j_appuie_sur_le_bouton(String button) {
        switch (button.toUpperCase()) {
            case "LEFT":
                context.left();
                break;
            case "UP":
                context.up();
                break;
            case "RIGHT":
                context.right();
                break;
        }
    }

    @Quand("j'attends un {string}")
    public void j_attends_un(String event) {
        if ("tick".equalsIgnoreCase(event)) {
            context.tick();
        }
    }

    @Alors("l'état doit être {string}")
    public void l_état_doit_être(String expectedState) {
        assertEquals(expectedState, context.getStateText());
    }

    @Alors("le mode doit être {string}")
    public void le_mode_doit_être(String expectedMode) {
        assertEquals(expectedMode.toLowerCase(), context.getModeText().toLowerCase());
    }

    @Alors("la mémoire doit être à {int}")
    public void la_mémoire_doit_être_à(Integer value) {
        assertEquals(value, AbstractTimer.getMemTimer());
    }

    @Alors("le temps restant doit être de {int} secondes")
    public void le_temps_restant_doit_être_de_secondes(Integer value) {
        assertEquals(value, AbstractTimer.getTimer());
    }

    @Alors("le temps total doit être de {int} seconde(s)")
    public void le_temps_total_doit_être_de_seconde(Integer value) {
        assertEquals(value, AbstractStopwatch.getTotalTime());
    }

    @Alors("le temps intermédiaire doit être de {int} seconde(s)")
    public void le_temps_intermédiaire_doit_être_de_seconde(Integer value) {
        assertEquals(value, AbstractStopwatch.getLapTime());
    }
}
