package metamodel;

import metamodel.transition.Transition;

import java.util.*;

public class Machine {

    protected HashMap<String, Integer> integerVariables = new HashMap<>();

    protected List<State> states = new ArrayList<>();

    protected State initialState;

    protected State currentState;

    public List<State> getStates() {
        return Collections.unmodifiableList(states);
    }

    public State getInitialState() {
        return initialState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State nextState) {
        this.currentState = nextState;
    }

    public State getState(String stateName) {
        Optional<State> state = states.stream()
                .filter(s -> stateName.equals(s.getName()))
                .findFirst();
        return state.orElse(null);
    }

    public int numberOfIntegers() {
        return integerVariables.size();
    }

    public boolean hasInteger(String string) {
        return integerVariables.containsKey(string);
    }

    public Integer getIntegerValue(String integerName) {
        Optional<Map.Entry<String, Integer>> integer = integerVariables.entrySet().stream().filter(i -> i.getKey().equals(integerName))
                .findFirst();

        return integer.map(Map.Entry::getValue).orElse(null);
    }

    public void processTransition(Transition transition) {
        if (!transition.hasOperation()) {
            return;
        }
        String operationVariableName = transition.getOperationVariableName();
        if (!integerVariables.containsKey(operationVariableName))
            throw new RuntimeException("Variable does not exist");

        switch (transition.getOperationType()) {
            case SET:
                integerVariables.replace(operationVariableName, transition.getOperationAmount());
                break;
            case INCREMENT:
                Integer newIncrementValue = integerVariables.get(operationVariableName) + transition.getOperationAmount();
                integerVariables.replace(operationVariableName, newIncrementValue);
                break;
            case DECREMENT:
                Integer newDecrementValue = integerVariables.get(operationVariableName) - transition.getOperationAmount();
                integerVariables.replace(operationVariableName, newDecrementValue);
                break;
        }
    }

    public boolean condtionalSatisfied(Transition transition) {
        if (!transition.isConditional()) {
            return true;
        }

        String variableName = transition.getConditionVariableName();
        Integer currentValue = integerVariables.get(variableName);
        if (currentValue == null)
            throw new RuntimeException("Variable does not exist");

        int conditionComparedValue = transition.getConditionComparedValue();

        boolean conditionalSatisfied = false;

        switch (transition.getConditionalType()) {
            case EQUAL:
                conditionalSatisfied = currentValue == conditionComparedValue;
                break;
            case LESS_THAN:
                conditionalSatisfied = currentValue < conditionComparedValue;
                break;
            case GREATER_THAN:
                conditionalSatisfied = currentValue > conditionComparedValue;
                break;
        }
        return conditionalSatisfied;
    }
}
