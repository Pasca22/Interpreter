package Model.Structures;

import Model.Statements.IStatement;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;

public class ProgramState {
    MyIStack<IStatement> exeStack;
    MyIDictionary<String, IValue> symbolTable;
    MyIList<IValue> outputList;
    IStatement originalProgram;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    MyIHeap<Integer, IValue> heap;

    public ProgramState(IStatement program) {
        exeStack = new MyStack<>();
        symbolTable = new MyDictionary<>();
        outputList = new MyList<>();
        fileTable = new MyDictionary<>();
        heap = new MyHeap<>();
        originalProgram = program.deepCopy();
        exeStack.push(program);
    }

    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<String, IValue> getSymbolTable() { return symbolTable; }

    public MyIList<IValue> getOutputList() { return outputList; }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() { return fileTable; }

    public MyIHeap<Integer, IValue> getHeap() {return heap;}

}
