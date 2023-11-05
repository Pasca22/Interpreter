package Repository;

import Model.Exceptions.MyException;
import Model.Statements.IStatement;
import Model.Structures.*;
import Model.Values.IValue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Repository implements IRepository {

    List<ProgramState> programStates = new ArrayList<>();
    String filePath;

    public Repository(ProgramState ps, String filePath) {
        programStates.add(ps);
        this.filePath = filePath;
    }

    @Override
    public void addProgramState(ProgramState ps) {
        programStates.add(ps);
    }

    @Override
    public void logProgramStateExec() throws Exception {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
        } catch (Exception e) {
            throw new MyException("File could not be opened");
        }

        MyIStack<IStatement> exeStack = getCurrentProgramState().getExeStack();
        MyIDictionary<String, IValue> systemTable = getCurrentProgramState().getSystemTable();
        MyIList<IValue> outputList = getCurrentProgramState().getOutputList();

        MyIStack<IStatement> auxiliaryStack = new MyStack<>();

        logFile.println("ExeStack:");
        while (!exeStack.isEmpty()) {
            IStatement statement = exeStack.pop();
            logFile.println(statement);
            auxiliaryStack.push(statement);
        }

        while (!auxiliaryStack.isEmpty()) {
            IStatement statement = auxiliaryStack.pop();
            exeStack.push(statement);
        }
        logFile.println();

        logFile.println("SymTable:");
        for (Map.Entry<String,IValue> s : systemTable.getIterableSet()) {
            logFile.println(s.getKey() + " --> " + s.getValue());
        }
        logFile.println();

        logFile.println("Out:");
        for (int i = 0; i < outputList.getSize(); i++) {
            logFile.println(outputList.get(i));
        }
        logFile.println();

        logFile.println("FileTable:");
        logFile.println();
        logFile.println();

        logFile.close();

    }

    @Override
    public ProgramState getCurrentProgramState() throws MyException {
        if (programStates.isEmpty())
            throw new MyException("No program states available");
        return programStates.get(programStates.size() - 1);
    }
}
