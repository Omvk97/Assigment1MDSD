package metamodel.transition;

import metamodel.State;
import metamodel.transition.transitionconditional.TransitionConditional;
import metamodel.transition.transitionconditional.TransitionConditionalType;
import metamodel.transition.transitionoperation.TransitionOperation;
import metamodel.transition.transitionoperation.TransitionOperationType;

public class TransitionBuilder {

    private String transitionEvent = null;
    private State transitionTarget = null;

    public Transition build() throws Exception {
        if (transitionTarget == null || transitionEvent == null)
            throw new Exception("Transition requires both an event and a target");

        Transition transition = new Transition(this.transitionEvent, transitionTarget);
        resetBuilder();
        return transition;
    }

    private void resetBuilder() {
        this.transitionEvent = null;
        this.transitionTarget = null;
    }

    public void setTransitionEvent(String transitionEvent) {
        this.transitionEvent = transitionEvent;
    }

    public void setTransitionTarget(State transitionTarget) {
        this.transitionTarget = transitionTarget;
    }

    public void addSetOperation(String operationVariableName, int setAmount, Transition transition) {
        transition.setTransitionOperation(new TransitionOperation(operationVariableName, TransitionOperationType.SET, setAmount));
    }

    public void addIncrementOperation(String operationVariableName, Transition transition) {
        transition.setTransitionOperation(new TransitionOperation(operationVariableName, TransitionOperationType.INCREMENT, 1));
    }

    public void addDecrementOperation(String operationVariableName, Transition transition) {
        transition.setTransitionOperation(new TransitionOperation(operationVariableName, TransitionOperationType.DECREMENT, 1));
    }

    public void addIfEqualConditional(String conditionVariableName, int amount, Transition transition) {
        transition.setConditional(new TransitionConditional(conditionVariableName, amount, TransitionConditionalType.EQUAL));
    }

    public void addIfGreaterThanConditional(String conditionVariableName, int amount, Transition transition) {
        transition.setConditional(new TransitionConditional(conditionVariableName, amount, TransitionConditionalType.GREATER_THAN));
    }

    public void addIfLessThanConditional(String conditionVariableName, int amount, Transition transition) {
        transition.setConditional(new TransitionConditional(conditionVariableName, amount, TransitionConditionalType.LESS_THAN));
    }
}
