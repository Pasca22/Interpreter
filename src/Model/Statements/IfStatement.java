package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIStack;
import Model.Structures.ProgramState;
import Model.Types.BoolType;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;

public class IfStatement implements IStatement {
    Expression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    IfStatement (Expression e, IStatement t, IStatement el) {
        expression = e;
        thenStatement = t;
        elseStatement = el;
    }
    public String toString(){ return "(IF("+ expression.toString()+") THEN(" + thenStatement.toString() +")ELSE("+ elseStatement.toString()+"))";}

    @Override
    public ProgramState execute(ProgramState state) throws Exception{
        MyIStack<IStatement> stack = state.getStack();
        MyIDictionary<String, IValue> systemTable = state.getDictionary();
        IValue condition = expression.evaluation(systemTable);

        if (!condition.getType().equals(new BoolType())) {
            throw new MyException("Conditional expression not a boolean");
        } else if (((BoolValue) condition).getValue()) {
            IStatement statement = stack.pop();
            stack.pop();
            stack.push(statement);
        } else {
            stack.pop();
        }



        return state;
    }
}
