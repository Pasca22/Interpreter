package Model.Statements;

import Model.Expressions.Expression;
import Model.Structures.ProgramState;

public class IfStatement implements IStatement {
    Expression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    IfStatement (Expression e, IStatement t, IStatement el) {
        expression =e;
        thenStatement =t;
        elseStatement =el;
    }
    public String toString(){ return "(IF("+ expression.toString()+") THEN(" + thenStatement.toString() +")ELSE("+ elseStatement.toString()+"))";}

    @Override
    public ProgramState execute(ProgramState state) throws Exception{

        return state;
    }
}
