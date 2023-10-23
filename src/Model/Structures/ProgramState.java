package Model.Structures;

import Model.Statements.IStatement;
import Model.Values.IValue;

public class ProgramState {
    MyIStack<IStatement> exeStack;
    MyIDictionary<String, IValue> systemTable;
    MyIList<IValue> outputList;

    public MyIStack getStack() {
        return exeStack;
    }

    IStatement originalProgram;
}
