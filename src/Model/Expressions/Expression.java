package Model.Expressions;

import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Values.IValue;;

public interface Expression {
    public IValue evaluation(MyIDictionary<String, IValue> table, MyIHeap heap) throws Exception;

    public Expression deepCopy();
}
