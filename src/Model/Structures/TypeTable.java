package Model.Structures;

import Model.Types.IType;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class TypeTable implements MyIDictionary<String, IType> {

    Map<String, IType> dictionary = new HashMap<>();

    @Override
    public boolean isDefined(String id) {
        return dictionary.containsKey(id);
    }

    @Override
    public Map<String, IType> getContent() {
        return dictionary;
    }

    @Override
    public IType lookup(String id) {
        return dictionary.get(id);
    }

    @Override
    public void update(String id, IType val) {
        dictionary.put(id, val);
    }

    @Override
    public Iterable<Map.Entry<String, IType>> getIterableSet() {
        return dictionary.entrySet();
    }

    @Override
    public void remove(String id) {
        dictionary.remove(id);
    }

    public TypeTable deepCopy()
    {
        TypeTable typeTable = new TypeTable();

        for (Map.Entry<String, IType> pair : this.dictionary.entrySet())
            typeTable.update(pair.getKey(), pair.getValue().deepCopy());

        return typeTable;
    }

    @Override
    public String toString() {
        String string = "";
        for (Map.Entry<String, IType> s : dictionary.entrySet()) {
            string += (s.getKey()) + "\n";
        }
        return string;
    }
}
