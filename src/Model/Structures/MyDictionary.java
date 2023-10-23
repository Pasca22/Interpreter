package Model.Structures;

import Model.Values.IValue;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {

    Map<K, V> dictionary = new HashMap<>();

    @Override
    public boolean isDefined(K id) {
        return false;
    }

    @Override
    public IValue lookup(K id) {
        return null;
    }

    @Override
    public void update(K id, V val) {

    }
}
