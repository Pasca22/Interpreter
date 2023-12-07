package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Structures.ProgramState;
import Model.Types.IType;
import Model.Values.IValue;

public class AssignmentStatement implements IStatement {
    String id;
    Expression expression;

    public AssignmentStatement(String id, Expression expr) {
        this.id = id;
        this.expression = expr;
    }

    @Override
    public String toString() {
        return id + "=" + expression.toString() + ";";
    }

    public ProgramState execute(ProgramState state) throws Exception {
        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        MyIHeap heap = state.getHeap();

        if (symbolTable.isDefined(id)) {
            IValue val = expression.evaluation(symbolTable, heap);
            IType typeId = symbolTable.lookup(id).getType();
            if (val.getType().equals(typeId)) {
                symbolTable.update(id, val);
            } else
                throw new MyException("declared type of variable \"" + id + "\" and type of the assigned expression do not match");
        } else
            throw new MyException("the used variable " + id + " was not declared before");
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws Exception {
        IType typeVariable = typeEnv.lookup(id);
        IType typeExpression = expression.typeCheck(typeEnv);
        if (typeVariable.equals(typeExpression)) {
            return typeEnv;
        }
        else {
            throw new MyException("Assignment: right hand side and left hand side have different types ");
        }
    }

    @Override
    public IStatement deepCopy() {
        return new AssignmentStatement(id, expression.deepCopy());
    }
}
