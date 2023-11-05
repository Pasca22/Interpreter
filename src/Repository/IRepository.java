package Repository;

import Model.Exceptions.MyException;
import Model.Structures.ProgramState;

import java.io.IOException;

public interface IRepository {
    ProgramState getCurrentProgramState() throws MyException;
    public void addProgramState(ProgramState ps);

    void logProgramStateExec() throws Exception;
}
