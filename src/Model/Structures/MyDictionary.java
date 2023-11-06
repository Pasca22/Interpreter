package Model.Structures;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {

    Map<K, V> dictionary = new HashMap<>();

    @Override
    public boolean isDefined(K id) {
        return dictionary.containsKey(id);
    }

    @Override
    public V lookup(K id) { return dictionary.get(id); }

    @Override
    public void update(K id, V val) {
        dictionary.put(id, val);
    }

    @Override
    public Iterable<Map.Entry<K, V>> getIterableSet() {
        return dictionary.entrySet();
    }

    @Override
    public void remove(K id) {
        dictionary.remove(id);
    }

    @Override
    public String toString() {
        return dictionary.toString();
    }
}
