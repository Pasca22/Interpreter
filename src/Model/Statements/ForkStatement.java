package Model.Statements;

import Model.Structures.*;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;

public class ForkStatement implements IStatement {

    IStatement statement;

    public ForkStatement(IStatement statement){
        this.statement = statement;
    }

    @Override
    public String toString()
    {
        return "fork{" + this.statement.toString() + "};";
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        MyIHeap heap = state.getHeap();
        SymbolTable symbolTable = state.getSymbolTable().deepCopy();
        MyIList<IValue> outputList = state.getOutputList();
        FileTable fileTable = state.getFileTable();

        return new ProgramState(new MyStack<>(), symbolTable, outputList, fileTable, heap, statement);
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(statement.deepCopy());
    }
}
