package Model.Statements;

import Model.Structures.MyIDictionary;
import Model.Structures.ProgramState;
import Model.Types.IType;

public class NopStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState state){
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws Exception {
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new NopStatement();
    }

    @Override
    public String toString() { return "NOP"; }
}
