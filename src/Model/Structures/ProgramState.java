package Model.Structures;

import Model.Statements.IStatement;
import Model.Values.IValue;

public class ProgramState {
    MyIStack<IStatement> exeStack;
    MyIDictionary<String, IValue> systemTable;
    MyIList<IValue> outputList;

    public MyIStack<IStatement> getStack() {
        return exeStack;
    }

    public MyIDictionary<String, IValue> getDictionary() { return systemTable; }

    public MyIList<IValue> getOutputList() { return outputList; }

    IStatement originalProgram;
}
