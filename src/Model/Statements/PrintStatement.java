package Model.Statements;

import Model.Expressions.Expression;
import Model.Structures.ProgramState;

public class PrintStatement implements IStatement {
    Expression expression;

    public String toString() {return "print(" + expression.toString() + ")";};

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        return state;
    }
}
