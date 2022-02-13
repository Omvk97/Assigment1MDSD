package metamodel.transition.transitionconditional;

public class TransitionConditional {
    private String variableName;
    private int valueToCompare;
    private TransitionConditionalType conditionalType;

    public TransitionConditional(String variableName, int valueToCompare, TransitionConditionalType conditionalType) {
        this.variableName = variableName;
        this.valueToCompare = valueToCompare;
        this.conditionalType = conditionalType;
    }

    public String getConditionalVariableName() {
        return this.variableName;
    }

    public int getValueToCompare() {
        return this.valueToCompare;
    }

    public TransitionConditionalType getConditionalType() {
        return this.conditionalType;
    }
}
