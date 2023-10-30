package Model.Statements;

import Model.Exceptions.MyException;
import Model.Structures.MyIDictionary;
import Model.Structures.ProgramState;
import Model.Types.IType;
import Model.Values.IValue;

public class VariableDeclarationStatement implements IStatement {
    String name;
    IType type;

    public VariableDeclarationStatement(String n, IType t) {
        name = n;
        type = t;
    }

    @Override
    public String toString() {
        return type + " " + name + ";";
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        MyIDictionary<String, IValue> systemTable = state.getSystemTable();
        if (systemTable.isDefined(name)) {
            throw new MyException("Variable already declared");
        } else {
            systemTable.update(name, type.defaultValue());
        }
        return state;
    }
}
