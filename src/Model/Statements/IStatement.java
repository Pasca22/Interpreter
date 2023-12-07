package Model.Statements;

import Model.Structures.MyIDictionary;
import Model.Structures.ProgramState;
import Model.Types.IType;

public interface IStatement {
    ProgramState execute(ProgramState state) throws Exception;

    MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws Exception;

    IStatement deepCopy();
}
