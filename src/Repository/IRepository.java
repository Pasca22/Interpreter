package Repository;

import Model.Exceptions.MyException;
import Model.Structures.ProgramState;

public interface IRepository {
    ProgramState getCurrentProgramState() throws MyException;
}
