package Model.Statements;

import Model.Structures.ProgramState;

public interface IStatement {
    public ProgramState execute(ProgramState state) throws Exception;

    public IStatement deepCopy();
}
