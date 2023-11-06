package Model.Structures;

import Model.Statements.IStatement;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;

public class ProgramState {
    MyIStack<IStatement> exeStack;
    MyIDictionary<String, IValue> systemTable;
    MyIList<IValue> outputList;
    IStatement originalProgram;
    MyIDictionary<StringValue, BufferedReader> fileTable;

    public ProgramState(IStatement program) {
        exeStack = new MyStack<>();
        systemTable = new MyDictionary<>();
        outputList = new MyList<>();
        fileTable = new MyDictionary<>();
        originalProgram = program.deepCopy();
        exeStack.push(program);
    }

    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<String, IValue> getSystemTable() { return systemTable; }

    public MyIList<IValue> getOutputList() { return outputList; }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() { return fileTable; }

}
