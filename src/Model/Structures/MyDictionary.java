package Model.Structures;

import Model.Values.IValue;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {

    Map<K, V> dictionary = new HashMap<>();

    @Override
    public boolean isDefined(K id) {
        return dictionary.containsKey(id);
    }

    @Override
    public V lookup(K id) {
        return dictionary.get(id);
    }

    @Override
    public void update(K id, V val) {
        dictionary.put(id, val);
    }
}
