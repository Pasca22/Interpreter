package Repository;

import Model.Structures.ProgramState;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {

    List<ProgramState> programStates = new ArrayList<>();

    @Override
    public ProgramState getCurrentProgramState() {
        return null;
    }
}
