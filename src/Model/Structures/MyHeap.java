package Model.Structures;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<K, V> implements MyIHeap<K, V> {
    Map<K, V> heap = new HashMap<>();
    int newAddress;

    @Override
    public boolean isDefined(K id) {
        return heap.containsKey(id);
    }

    @Override
    public V lookup(K id) { return heap.get(id); }

    @Override
    public void update(K id, V val) {
        heap.put(id, val);
    }

    @Override
    public Iterable<Map.Entry<K, V>> getIterableSet() {
        return heap.entrySet();
    }

    @Override
    public void remove(K id) {
        heap.remove(id);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
