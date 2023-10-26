package Model.Statements;

import Model.Expressions.Expression;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIList;
import Model.Structures.ProgramState;
import Model.Values.IValue;

public class PrintStatement implements IStatement {
    Expression expression;

    public PrintStatement(Expression expr) {
        expression = expr;
    }

    public String toString() {return "print(" + expression.toString() + ")";};

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        MyIList<IValue> outputList = state.getOutputList();
        MyIDictionary<String, IValue> systemTable = state.getDictionary();
        outputList.add(expression.evaluation(systemTable));

        return state;
    }
}
