import metamodel.Machine;
import metamodel.State;
import metamodel.transition.Transition;
import metamodel.transition.TransitionBuilder;

import java.util.List;

public class StateMachine extends Machine {

    private final TransitionBuilder transitionBuilder = new TransitionBuilder();

    public Machine build() {
        currentState = initialState;
        return this;
    }

    public StateMachine state(String name) {
        State state = getState(name);
        if (state == null) { // state doesn't exist yet
            state = new State(name);
            states.add(state);
        }
        this.currentState = state;
        return this;
    }

    public StateMachine initial() {
        this.initialState = currentState;
        return this;
    }

    public StateMachine when(String event) {
        this.transitionBuilder.setTransitionEvent(event);
        return this;
    }

    public StateMachine to(String targetStateName) {
        State targetState = this.getState(targetStateName);
        if (targetState == null) { // hasn't been made yet
            targetState = new State(targetStateName);
            states.add(targetState);
        }
        this.transitionBuilder.setTransitionTarget(targetState);
        try {
            currentState.addTransition(transitionBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public StateMachine integer(String string) {
        integerVariables.put(string, 0);
        return this;
    }

    private Transition getLatestTransition() {
        List<Transition> transitions = currentState.getTransitions();
        return transitions.get(transitions.size() - 1);
    }

    public StateMachine set(String variableName, int setAmount) {
        transitionBuilder.addSetOperation(variableName, setAmount, getLatestTransition());
        return this;
    }

    public StateMachine increment(String variableName) {
        transitionBuilder.addIncrementOperation(variableName, getLatestTransition());
        return this;
    }

    public StateMachine decrement(String variableName) {
        transitionBuilder.addDecrementOperation(variableName, getLatestTransition());
        return this;
    }

    public StateMachine ifEquals(String conditionVariableName, int amount) {
        transitionBuilder.addIfEqualConditional(conditionVariableName, amount, getLatestTransition());
        return this;
    }

    public StateMachine ifGreaterThan(String conditionVariableName, int amount) {
        transitionBuilder.addIfGreaterThanConditional(conditionVariableName, amount, getLatestTransition());
        return this;
    }

    public StateMachine ifLessThan(String conditionVariableName, int amount) {
        transitionBuilder.addIfLessThanConditional(conditionVariableName, amount, getLatestTransition());
        return this;
    }

}
