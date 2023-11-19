package Controller;

import Model.Exceptions.MyException;
import Model.Statements.IStatement;
import Model.Structures.MyIStack;
import Model.Structures.ProgramState;
import Model.Values.IValue;
import Model.Values.ReferenceValue;
import Repository.IRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public void addProgramState(ProgramState state) {
        this.repository.addProgramState(state);
    }

    public void oneStep(ProgramState state) throws Exception {
        MyIStack<IStatement> stack = state.getExeStack();

        if(stack.isEmpty()) {
            throw new MyException("Program state stack is empty");
        }
        IStatement createdStatement = stack.pop();
        createdStatement.execute(state);
    }

    public void allSteps() throws Exception {
        ProgramState programState;
            programState = repository.getCurrentProgramState();
            repository.logProgramStateExec();

        while (!programState.getExeStack().isEmpty()) {
            oneStep(programState);
            repository.logProgramStateExec();

            programState.getHeap().setContent(unsafeGarbageCollector(
                    getAddrFromSymTable(programState.getSymbolTable().getContent().values()),
                    programState.getHeap().getContent()));

            repository.logProgramStateExec();
        }
    }

    Map<Integer, IValue> unsafeGarbageCollector(List<Integer> symbolTableAddresses, Map<Integer,IValue> heap){
        return heap.entrySet().stream().
                filter(e -> symbolTableAddresses.contains(e.getKey()) || heap.containsKey(e.getKey())).
                        collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    List<Integer> getAddrFromSymTable(Collection<IValue> symbolTableAddresses){
        return symbolTableAddresses.stream().filter(v-> v instanceof ReferenceValue)
                .map(v -> {
                    ReferenceValue v1 = (ReferenceValue)v;
                    return v1.getAddress();})
                .collect(Collectors.toList());
    }
}
