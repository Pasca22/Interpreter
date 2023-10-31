package Model.Expressions;

import Model.Structures.MyIDictionary;
import Model.Values.IValue;;

public interface Expression {
    public IValue evaluation(MyIDictionary<String, IValue> table) throws Exception;

    public Expression deepCopy();
}
