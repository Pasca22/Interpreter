package Model.Expressions;

import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;

public class ValueExpression implements Expression {

    IValue value;

    public  ValueExpression(IValue v) {
        value = v;
    }

    @Override
    public String toString() {
        return "" + value;
    }
    @Override
    public IValue evaluation(MyIDictionary<String, IValue> table,  MyIHeap heap) throws Exception {
        return value;
    }

    @Override
    public Expression deepCopy() {
        return new ValueExpression(value.deepCopy());
    }
}
