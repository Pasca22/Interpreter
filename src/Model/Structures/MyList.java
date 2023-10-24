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
}
