package Model.Statements;

import Model.Structures.ProgramState;

public class NopStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState state){
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new NopStatement();
    }

    @Override
    public String toString() { return "NOP"; }
}
