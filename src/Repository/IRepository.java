package Repository;

import Model.Exceptions.MyException;
import Model.Structures.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    public void addProgramState(ProgramState ps);

    List<ProgramState> getProgramStateList();
    void setProgramStateList(List<ProgramState> l);

    void logProgramStateExec(ProgramState ps);
}
