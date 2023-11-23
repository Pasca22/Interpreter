package Model.Structures;

import Model.Values.StringValue;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class FileTable implements MyIDictionary<StringValue, BufferedReader> {

    Map<StringValue, BufferedReader> dictionary = new HashMap<>();

    @Override
    public boolean isDefined(StringValue id) {
        return dictionary.containsKey(id);
    }

    @Override
    public Map<StringValue, BufferedReader> getContent() {
        return dictionary;
    }

    @Override
    public BufferedReader lookup(StringValue id) {
        return dictionary.get(id);
    }

    @Override
    public void update(StringValue id, BufferedReader val) {
        dictionary.put(id, val);
    }

    @Override
    public Iterable<Map.Entry<StringValue, BufferedReader>> getIterableSet() {
        return dictionary.entrySet();
    }

    @Override
    public void remove(StringValue id) {
        dictionary.remove(id);
    }

    @Override
    public String toString() {
        String string = "";
        for (Map.Entry<StringValue, BufferedReader> s : dictionary.entrySet()) {
            string += (s.getKey().toString()) + "\n";
        }
        return string;
    }
}
