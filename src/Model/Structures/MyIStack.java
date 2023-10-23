package Model.Structures;

public interface MyIStack<T> {

    T pop();
    void push(T value);

    boolean isEmpty();
}
