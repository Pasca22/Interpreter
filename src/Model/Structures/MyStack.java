package Model.Structures;

import java.util.Stack;
public class MyStack<T> implements MyIStack<T> {

    Stack<T> stack = new Stack<>();

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T value) {
        stack.push(value);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
