package metamodel.transition;

import metamodel.State;
import metamodel.transition.transitionconditional.TransitionConditional;
import metamodel.transition.transitionconditional.TransitionConditionalType;
import metamodel.transition.transitionoperation.TransitionOperation;
import metamodel.transition.transitionoperation.TransitionOperationType;

public class Transition {

    private final String event;

    private final State target;

    private TransitionOperation transitionOperation;

    private TransitionConditional transitionConditional;

    public Transition(String event, State target) {
        this.event = event;
        this.target = target;
    }

    public String getEvent() {
        return this.event;
    }

    public State getTarget() {
        return this.target;
    }

    public boolean hasSetOperation() {
        return this.transitionOperation.getOperationType().equals(TransitionOperationType.SET);
    }

    public boolean hasIncrementOperation() {
        return this.transitionOperation.getOperationType().equals(TransitionOperationType.INCREMENT);
    }

    public boolean hasDecrementOperation() {
        return this.transitionOperation.getOperationType().equals(TransitionOperationType.DECREMENT);
    }

    public String getOperationVariableName() {
        return this.transitionOperation.getOperationVariableName();
    }

    public boolean isConditional() {
        return this.transitionConditional != null;
    }

    public String getConditionVariableName() {
        return this.transitionConditional.getConditionalVariableName();
    }

    public Integer getConditionComparedValue() {
        return this.transitionConditional.getValueToCompare();
    }

    public TransitionConditionalType getConditionalType() {
        return this.transitionConditional.getConditionalType();
    }

    public boolean isConditionEqual() {
        return this.transitionConditional.getConditionalType().equals(TransitionConditionalType.EQUAL);
    }

    public boolean isConditionGreaterThan() {
        return this.transitionConditional.getConditionalType().equals(TransitionConditionalType.GREATER_THAN);
    }

    public boolean isConditionLessThan() {
        return this.transitionConditional.getConditionalType().equals(TransitionConditionalType.LESS_THAN);
    }

    public boolean hasOperation() {
        return this.transitionOperation != null;
    }

    public void setTransitionOperation(TransitionOperation transitionOperation) {
        this.transitionOperation = transitionOperation;
    }

    public void setConditional(TransitionConditional transitionConditional) {
        this.transitionConditional = transitionConditional;
    }

    public TransitionOperationType getOperationType() {
        return this.transitionOperation.getOperationType();
    }

    public Integer getOperationAmount() {
        return this.transitionOperation.getAmount();
    }
}
