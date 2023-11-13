package Model.Structures;

import java.util.Map;

public interface MyIHeap<K, V> {

    boolean isDefined(K id);

    V lookup(K id);

    void update(K id, V val);

    Iterable<Map.Entry<K,V>> getIterableSet();

    void remove(K id);
}
