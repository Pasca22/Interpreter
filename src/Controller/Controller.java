package Controller;

import Model.Exceptions.MyException;
import Model.Structures.ProgramState;
import Model.Values.IValue;
import Model.Values.ReferenceValue;
import Repository.IRepository;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {

    IRepository repository;

    ExecutorService executor;

    public Controller(IRepository repository) {
        this.repository = repository;
        executor = null;
    }

    public void addProgramState(ProgramState state) {
        this.repository.addProgramState(state);
    }

    List<ProgramState> removeCompletedProgram(List<ProgramState> inProgramList) {
        return inProgramList.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public void oneStepAllPrograms(List<ProgramState> programList) throws Exception {
        programList.forEach(p -> repository.logProgramStateExec(p));

        List<Callable<ProgramState>> callList = programList.stream()
                .map(p -> (Callable<ProgramState>)(p::oneStep))
                .toList();

        List<ProgramState> newProgramsList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch(InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(Objects::nonNull)
                .toList();

        programList.addAll(newProgramsList);
        programList.forEach(p -> repository.logProgramStateExec(p));
        repository.setProgramStateList(programList);

    }

    public void allSteps() throws Exception {
        executor = Executors.newFixedThreadPool(2);

        List<ProgramState> programStateList = removeCompletedProgram(repository.getProgramStateList());
        while(!programStateList.isEmpty()){
            programStateList
                    .forEach(program -> this.garbageCollector(
                            this.getAddrFromSymTable(program.getSymbolTable().getContent().values()),
                            program.getHeap().getContent()));

            oneStepAllPrograms(programStateList);
            programStateList = removeCompletedProgram(repository.getProgramStateList());
        }
        executor.shutdownNow();

        repository.setProgramStateList(programStateList);
    }


    Map<Integer, IValue> garbageCollector(List<Integer> symbolTableAddresses, Map<Integer,IValue> heap){
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
