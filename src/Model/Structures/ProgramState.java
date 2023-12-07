package Model.Structures;

import Model.Exceptions.MyException;
import Model.Statements.IStatement;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.util.Vector;

public class ProgramState {

    Integer id;
    MyIStack<IStatement> exeStack;
    SymbolTable symbolTable;
    MyIList<IValue> outputList;
    IStatement originalProgram;
    FileTable fileTable;
    MyIHeap heap;

    TypeTable typeTable;

    static Vector<Integer> usedId = new Vector<>();

    @Override
    public String toString() {
        return "Id = " + id + "\n\n" +
                "ExeStack:\n" + exeStack.toString() + "\n" +
                "SymbolTable:\n" + symbolTable.toString() + "\n" +
                "Heap:\n" + heap.toString() + "\n" +
                "Out:\n" + outputList.toString() + "\n" +
                "FileTable:\n" + fileTable.toString() + "\n" +
                "-----------------------------------\n\n";
    }

    private int generateId() {
        int newId = 1;
        while (usedId.contains(newId)) {
            newId++;
        }
        return newId;
    }

    public ProgramState(IStatement program) throws Exception {
        exeStack = new MyStack<>();
        symbolTable = new SymbolTable();
        outputList = new MyList<>();
        fileTable = new FileTable();
        heap = new MyHeap();
        typeTable = new TypeTable();

        originalProgram = program.deepCopy();
        this.originalProgram.typeCheck(typeTable);
        exeStack.push(originalProgram);

        id = generateId();
        usedId.add(id);
    }

    public ProgramState(MyIStack<IStatement> exeStack, SymbolTable symbolTable, MyIList<IValue> outputList, FileTable fileTable, MyIHeap heap, TypeTable typeTable,IStatement program) throws Exception {
        this.exeStack = exeStack;
        this.symbolTable = symbolTable;
        this.outputList = outputList;
        this.fileTable = fileTable;
        this.heap = heap;
        this.typeTable = typeTable;

        this.originalProgram = program.deepCopy();
        this.originalProgram.typeCheck(typeTable);
        this.exeStack.push(this.originalProgram);

        id = generateId();
        usedId.add(id);
    }
    public int getId() {
        return id;
    }
    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    public SymbolTable getSymbolTable() { return symbolTable; }

    public MyIList<IValue> getOutputList() { return outputList; }

    public FileTable getFileTable() { return fileTable; }

    public MyIHeap getHeap() {return heap;}

    public TypeTable getTypeTable() {return typeTable;}

    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public ProgramState oneStep() throws Exception {

        if(exeStack.isEmpty()) {
            throw new MyException("Program state stack is empty");
        }
        IStatement createdStatement = exeStack.pop();
        return createdStatement.execute(this);
    }

}
