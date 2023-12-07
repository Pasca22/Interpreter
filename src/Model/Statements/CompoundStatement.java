package Model.Statements;

import Model.Structures.MyIDictionary;
import Model.Structures.MyIStack;
import Model.Structures.ProgramState;
import Model.Types.IType;

public class CompoundStatement implements IStatement {
    IStatement firstStatement;
    IStatement secondStatement;

    public CompoundStatement(IStatement fs, IStatement ss) {
        firstStatement = fs;
        secondStatement = ss;
    }
    @Override
    public String toString() {
        return firstStatement.toString() + " " + secondStatement.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) {
        MyIStack<IStatement> stack = state.getExeStack();
        stack.push(secondStatement);
        stack.push(firstStatement);
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws Exception {
        return secondStatement.typeCheck(firstStatement.typeCheck(typeEnv));
    }

    @Override
    public IStatement deepCopy() {
        return new CompoundStatement(firstStatement.deepCopy(), secondStatement.deepCopy());
    }
}
