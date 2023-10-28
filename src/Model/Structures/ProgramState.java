package Model.Structures;

import Model.Statements.IStatement;
import Model.Values.IValue;

public class ProgramState {
    MyIStack<IStatement> exeStack = new MyStack<>();
    MyIDictionary<String, IValue> systemTable = new MyDictionary<>();
    MyIList<IValue> outputList = new MyList<>();

    IStatement originalProgram;

    public ProgramState(IStatement originalProgram) {
        this.originalProgram = originalProgram;
        exeStack.push(originalProgram);
    }

    public MyIStack<IStatement> getStack() {
        return exeStack;
    }

    public MyIDictionary<String, IValue> getDictionary() { return systemTable; }

    public MyIList<IValue> getOutputList() { return outputList; }


}
