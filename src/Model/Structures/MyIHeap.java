package Model.Structures;

import Model.Values.IValue;

import java.util.Map;

public interface MyIHeap {

    int getFreeAddress();

    Map<Integer, IValue> getContent();

    void setContent(Map<Integer, IValue> newHeap);

    boolean isDefined(Integer id);

    IValue lookup(Integer id);

    void allocate(IValue val);

    void update(Integer id, IValue val);

    Iterable<Map.Entry<Integer,IValue>> getIterableSet();

    void remove(Integer id);
}
