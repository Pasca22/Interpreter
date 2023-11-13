package Model.Structures;

import Model.Values.IValue;

import java.util.Map;

public interface MyIHeap {

    int getFreeAddress();

    boolean isDefined(Integer id);

    IValue lookup(Integer id);

    void allocate(IValue val);

    void update(Integer id, IValue val);

    Iterable<Map.Entry<Integer,IValue>> getIterableSet();

    void remove(Integer id);
}
