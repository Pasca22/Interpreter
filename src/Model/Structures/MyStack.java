package Model.Structures;

import Model.Statements.IStatement;

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
        return stack.isEmpty();
    }

    @Override
    public T peek() {
        return stack.peek();
    }

    @Override
    public String toString() {
        String string = "";
        Stack<T> auxiliaryStack = new Stack<>();
        while (!stack.isEmpty()) {
            T statement = stack.pop();
            string += statement.toString() + "\n";
            auxiliaryStack.push(statement);
        }

        while (!auxiliaryStack.isEmpty()) {
            T statement = auxiliaryStack.pop();
            stack.push(statement);
        }

        return string;
    }
}
