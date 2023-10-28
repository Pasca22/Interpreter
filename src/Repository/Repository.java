package Repository;

import Model.Exceptions.MyException;
import Model.Statements.IStatement;
import Model.Structures.ProgramState;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {

    List<ProgramState> programStates = new ArrayList<>();

    @Override
    public void addProgramState(ProgramState ps) {
        programStates.add(ps);
    }

    @Override
    public ProgramState getCurrentProgramState() throws MyException {
        if (programStates.isEmpty())
            throw new MyException("No program states available");
        return programStates.get(programStates.size() - 1);
    }
}
