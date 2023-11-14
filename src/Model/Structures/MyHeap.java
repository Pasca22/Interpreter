package Model.Structures;

import Model.Values.IValue;

import java.util.HashMap;
import java.util.Map;

public class MyHeap implements MyIHeap {
    Map<Integer, IValue> heap = new HashMap<>();
    int freeAddress = 1;

    public int getFreeAddress() {
        return freeAddress;
    }

    @Override
    public Map<Integer, IValue> getContent() {
        return heap;
    }

    @Override
    public void setContent(Map<Integer, IValue> newHeap) {
        heap = newHeap;
    }

    @Override
    public boolean isDefined(Integer id) {
        return heap.containsKey(id);
    }

    @Override
    public IValue lookup(Integer id) { return heap.get(id); }

    @Override
    public void allocate(IValue val) {
        heap.put(freeAddress, val);
        freeAddress++;
    }

    @Override
    public void update(Integer id, IValue val) {
        heap.put(id, val);
    }

    @Override
    public Iterable<Map.Entry<Integer, IValue>> getIterableSet() {
        return heap.entrySet();
    }

    @Override
    public void remove(Integer id) {
        heap.remove(id);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
