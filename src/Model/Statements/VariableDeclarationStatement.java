package Model.Statements;

import Model.Structures.ProgramState;

import java.lang.reflect.Type;

public class VariableDeclarationStatement implements IStatement {
    String name;
    Type type;

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        return null;
    }
}
