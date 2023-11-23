package Model.Structures;

import Model.Values.IValue;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable implements MyIDictionary<String, IValue> {


    Map<String, IValue> dictionary = new HashMap<>();
    @Override
    public boolean isDefined(String id) {
        return dictionary.containsKey(id);
    }

    @Override
    public Map<String, IValue> getContent() {
        return dictionary;
    }

    @Override
    public IValue lookup(String id) {
        return dictionary.get(id);
    }

    @Override
    public void update(String id, IValue val) {
        dictionary.put(id, val);
    }

    @Override
    public Iterable<Map.Entry<String, IValue>> getIterableSet() {
        return dictionary.entrySet();
    }

    @Override
    public void remove(String id) {
        dictionary.remove(id);
    }

    @Override
    public String toString() {
        String string = "";
        for (Map.Entry<String, IValue> s : dictionary.entrySet()) {
            string += (s.getKey() + " --> " + s.getValue()) + "\n";
        }
        return string;
    }

    public SymbolTable deepCopy()
    {
        SymbolTable dict = new SymbolTable();

        for (Map.Entry<String, IValue> pair : dictionary.entrySet())
            dict.update(pair.getKey(), pair.getValue().deepCopy());

        return dict;
    }
}
