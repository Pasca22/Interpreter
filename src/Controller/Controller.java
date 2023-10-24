package Controller;

import Model.Exceptions.MyException;
import Model.Statements.IStatement;
import Model.Structures.MyIStack;
import Model.Structures.ProgramState;
import Repository.IRepository;
import Repository.Repository;

public class Controller {

    IRepository repository = new Repository();

    ProgramState oneStep(ProgramState state) throws Exception {
        MyIStack<IStatement> stack = state.getStack();
        if(stack.isEmpty()) {
            throw new MyException("Program state stack is empty");
        }
        IStatement createdStatement = stack.pop();
        return createdStatement.execute(state);
    }

    void allSteps() {
        ProgramState programState;
        try {
            programState = repository.getCurrentProgramState();
        } catch (MyException e) {
            return;
        }

        while (!programState.getStack().isEmpty()) {
            try {
                oneStep(programState);
            } catch (Exception e) {
                return;
            }
            //here you can display the programState state
        }
    }
}
