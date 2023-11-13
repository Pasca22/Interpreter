package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Structures.MyIDictionary;
import Model.Structures.ProgramState;
import Model.Types.ReferenceType;
import Model.Values.IValue;
import Model.Values.ReferenceValue;

public class HeapAllocationStatement implements IStatement {

    String variable;
    Expression expression;

    public HeapAllocationStatement(String var, Expression e) {
        variable = var;
        expression = e;
    }
    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();

        if(!symbolTable.isDefined(variable)) {
            throw new MyException("Variable is not defined");
        }

        IValue variableValue = symbolTable.lookup(variable);

        if (!variableValue.getType().equals(new ReferenceType())) {
            throw new MyException("Variable is not of type Reference");
        }

        ReferenceValue referenceValue = (ReferenceValue) variableValue;
        IValue expressionValue = expression.evaluation(symbolTable);

        if (!referenceValue.getType().equals(expressionValue.getType())) {
            throw new MyException("Both types should be Reference Type");
        }







        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new HeapAllocationStatement(variable, expression.deepCopy());
    }
}
