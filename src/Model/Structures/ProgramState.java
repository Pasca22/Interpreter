package Model.Structures;

import Model.Statements.IStatement;
import Model.Values.IValue;

public class ProgramState {
    MyIStack<IStatement> exeStack;
    MyIDictionary<String, IValue> systemTable;
    MyIList<IValue> outputList;

    IStatement originalProgram;

    public ProgramState(MyIStack<IStatement> stack, MyIDictionary<String, IValue> dictionary, MyIList<IValue> list, IStatement program) {
        exeStack = stack;
        systemTable = dictionary;
        outputList = list;
        originalProgram = program.deepCopy();
        exeStack.push(program);
    }

    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<String, IValue> getSystemTable() { return systemTable; }

    public MyIList<IValue> getOutputList() { return outputList; }


}
