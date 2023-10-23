package Model.Structures;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {

    Map<K, V> dictionary = new HashMap<>();

}
