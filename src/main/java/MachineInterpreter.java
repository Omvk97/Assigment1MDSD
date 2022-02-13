import metamodel.Machine;
import metamodel.State;
import metamodel.transition.Transition;

import java.util.List;


public class MachineInterpreter {

    private Machine machine;

    public void run(Machine m) {
        this.machine = m;
    }

    public State getCurrentState() {
        return machine.getCurrentState();
    }

    public void processEvent(String eventName) {
        State currentState = machine.getCurrentState();
        List<Transition> transitions = currentState.getAllTransitionsByEventName(eventName);
        for (Transition transition : transitions) {
            if (machine.condtionalSatisfied(transition)) {
                machine.setCurrentState(transition.getTarget());
                machine.processTransition(transition);
                break; // Only process the first valid translation
            }
        }
    }

    public int getInteger(String integerName) {
        return machine.getIntegerValue(integerName);
    }

}
