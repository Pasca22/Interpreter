package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Structures.MyIDictionary;
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
        MyIDictionary<String, IValue> systemTable = state.getSystemTable();

        if (systemTable.isDefined(id)) {
            IValue val = expression.evaluation(systemTable);
            IType typeId = systemTable.lookup(id).getType();
            if (val.getType().equals(typeId)) {
                systemTable.update(id, val);
            } else
                throw new MyException("declared type of variable \"" + id + "\" and type of the assigned expression do not match");
        } else
            throw new MyException("the used variable " + id + " was not declared before");
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new AssignmentStatement(id, expression.deepCopy());
    }
}
