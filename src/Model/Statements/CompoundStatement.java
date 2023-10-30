package Model.Statements;

import Model.Structures.MyIStack;
import Model.Structures.ProgramState;

public class CompoundStatement implements IStatement {
    IStatement firstStatement;
    IStatement secondStatement;

    public CompoundStatement(IStatement fs, IStatement ss) {
        firstStatement = fs;
        secondStatement = ss;
    }
    @Override
    public String toString() {
        return firstStatement.toString() + "\n" + secondStatement.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) {
        MyIStack<IStatement> stack = state.getExeStack();
        stack.push(secondStatement);
        stack.push(firstStatement);
        return state;
    }
}
