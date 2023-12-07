package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Structures.ProgramState;
import Model.Types.IType;
import Model.Types.ReferenceType;
import Model.Values.IValue;
import Model.Values.ReferenceValue;

public class HeapWritingStatement implements IStatement {

    String variableName;
    Expression expression;

    public HeapWritingStatement(String var, Expression e) {
        variableName = var;
        expression = e;
    }

    @Override
    public String toString() {
        return "wH(" + variableName + ", " + expression + ");";
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        MyIHeap heap = state.getHeap();

        if(! symbolTable.isDefined(variableName)) {
            throw new MyException("Variable is not defined");
        }

        IValue variableValue = symbolTable.lookup(variableName);

        if (!(variableValue.getType() instanceof ReferenceType)) {
            throw new MyException("Variable is not of type Reference");
        }

        ReferenceValue referenceValue = (ReferenceValue)variableValue;

        if (!heap.isDefined(referenceValue.getAddress())) {
            throw new MyException("Address not in heap");
        }

        IValue expressionValue = expression.evaluation(symbolTable, heap);
        ReferenceType referenceType = (ReferenceType) referenceValue.getType();

        if (!expressionValue.getType().equals(referenceType.getInner())) {
            throw new MyException("Variable and reference do not have the same type");
        }

        heap.update(referenceValue.getAddress(), expressionValue);
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws Exception {
        IType variableType = typeEnv.lookup(variableName);
        IType expressionType = expression.typeCheck(typeEnv);

        if (!expressionType.equals(((ReferenceType)variableType).getInner())) {
            throw new MyException("WriteHeap: Declared type of variable " + variableName + " and type of assigned reference expression do not match.");
        }

        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new HeapWritingStatement(variableName, expression.deepCopy());
    }
}
