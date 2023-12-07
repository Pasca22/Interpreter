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

public class HeapAllocationStatement implements IStatement {

    String variableName;
    Expression expression;

    public HeapAllocationStatement(String var, Expression e) {
        variableName = var;
        expression = e;
    }

    @Override
    public String toString()
    {
        return "new(" + variableName + ", " + expression + ");";
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        MyIHeap heap = state.getHeap();

        if(!symbolTable.isDefined(variableName)) {
            throw new MyException("Variable is not defined");
        }

        IValue variableValue = symbolTable.lookup(variableName);

        if (!(variableValue.getType() instanceof ReferenceType)) {
            throw new MyException("Variable is not of type Reference");
        }

        ReferenceType referenceType = (ReferenceType) variableValue.getType();
        IValue expressionValue = expression.evaluation(symbolTable, heap);

        if (!referenceType.getInner().equals(expressionValue.getType())) {
            throw new MyException("Both types should be Reference Type");
        }

        int address = heap.getFreeAddress();
        heap.allocate(expressionValue);
        symbolTable.update(variableName, new ReferenceValue(address, expressionValue.getType()));

        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws Exception {
        IType typeVariable = typeEnv.lookup(variableName);
        IType typeExpression = expression.typeCheck(typeEnv);
        if (typeVariable.equals(new ReferenceType(typeExpression))) {
            return typeEnv;
        }
        else {
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
        }
    }

    @Override
    public IStatement deepCopy() {
        return new HeapAllocationStatement(variableName, expression.deepCopy());
    }
}
