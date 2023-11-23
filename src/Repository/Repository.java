package Repository;

import Model.Statements.IStatement;
import Model.Structures.*;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.*;
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
    public List<ProgramState> getProgramStateList() {
        return programStates;
    }

    @Override
    public void setProgramStateList(List<ProgramState> l) {
        programStates = l;
    }

    @Override
    public void logProgramStateExec(ProgramState ps){

        try (PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.filePath, true))))
        {
            logFile.write(ps.toString());
            logFile.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
