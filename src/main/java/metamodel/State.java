package metamodel;

import metamodel.transition.Transition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class State {

    private final String name;

    private final List<Transition> transitions = new ArrayList<>();

    public State(String name) {
        this.name = name;
    }

    public Object getName() {
        return this.name;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public Transition getTransitionByEvent(String event) {
        Optional<Transition> transition = this.transitions.stream()
                .filter(t -> t.getEvent().equals(event))
                .findFirst();
        return transition.orElse(null);
    }

    public List<Transition> getAllTransitionsByEventName(String eventName) {
        return this.transitions.stream()
                .filter(t -> t.getEvent().equals(eventName))
                .collect(Collectors.toList());
    }

    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }

}
