package states;

public interface EventListener {

    ClockState left(); // button 1 pressed

    ClockState up(); // button 2 pressed

    ClockState right(); // button 3 pressed

}
