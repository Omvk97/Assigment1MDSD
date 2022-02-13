package metamodel.transition.transitionoperation;

public class TransitionOperation {

    private final String operationVariableName;

    private final TransitionOperationType operationType;

    private final int amount;

    public TransitionOperation(String operationVariableName, TransitionOperationType operationType, int amount) {
        this.operationVariableName = operationVariableName;
        this.operationType = operationType;
        this.amount = amount;
    }

    public String getOperationVariableName() {
        return operationVariableName;
    }

    public TransitionOperationType getOperationType() {
        return operationType;
    }

    public int getAmount() {
        return amount;
    }
}
