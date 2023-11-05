package Model.Structures;

public interface MyIList<T> {

    void add(T val);

    boolean isEmpty();

    T get(int i);

    int getSize();
}
