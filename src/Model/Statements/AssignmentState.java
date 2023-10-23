package Model.Statements;

import Model.Expressions.Expression;
import Model.Structures.MyIStack;
import Model.Structures.MyIDictionary;
import Model.Structures.ProgramState;
import Model.Types.IType;
import Model.Values.IValue;

public class AssignmentState implements IStatement {
    String id;
    Expression expression;

    public String toString(){ return id + "=" + expression.toString();}

    ProgramState execute(ProgramState state) throws Exception {
        MyIStack<IStatement> stack = state.getStack();
        MyIDictionary<String, IValue> systemTable= state.getDictionary();

        if (systemTable.isDefined(id)) {
            IValue val = expression.evaluation(systemTable);
            IType typId= (systemTable.lookup(id)).getType();
            if (val.getType(). equals(typId)) {
                systemTable.update(id, val);
            }
            else
                throw new Exception("declared type of variable"+id+" and type of the assigned expression do not match");
        }
        else
            throw new Exception("the used variable" +id + " was not declared before");
        return state;
    };
