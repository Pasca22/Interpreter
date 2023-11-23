package Model.Structures;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {

    List<T> list = new ArrayList<>();

    @Override
    public void add(T val) {
        list.add(val);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public T get(int i) {
        return list.get(i);
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public String toString() {
        String string = "";
        for (T t : list) {
            string += t + "\n";
        }
        ;
        return string;
    }
}
