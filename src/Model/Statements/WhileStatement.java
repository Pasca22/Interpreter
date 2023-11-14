package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIStack;
import Model.Structures.ProgramState;
import Model.Types.BoolType;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class WhileStatement implements IStatement {

    Expression expression;
    IStatement statement;

    public WhileStatement(Expression e, IStatement stmt) {
        expression = e;
        statement = stmt;
    }

    @Override
    public String toString() {
        return "while (" + this.expression.toString() + ") { " + this.statement.toString() + " }";
    }
    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        MyIStack<IStatement> executionStack = state.getExeStack();
        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();

        IValue expressionValue = this.expression.evaluation(symbolTable, state.getHeap());

        if (!expressionValue.getType().equals(new BoolType())) {
            throw new MyException("The expression is not a logic expression");
        }

        BoolValue expressionTruthValue = (BoolValue) expressionValue;

        if (expressionTruthValue.getValue())
        {
            executionStack.push(this);
            executionStack.push(this.statement);
        }

        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new WhileStatement(expression.deepCopy(), statement.deepCopy());
    }
}
