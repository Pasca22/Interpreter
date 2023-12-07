package Model.Expressions;

import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.IType;
import Model.Values.IValue;;

public interface Expression {
    IValue evaluation(MyIDictionary<String, IValue> table, MyIHeap heap) throws Exception;

    IType typeCheck(MyIDictionary<String,IType> typeEnv) throws Exception;

    Expression deepCopy();
}
