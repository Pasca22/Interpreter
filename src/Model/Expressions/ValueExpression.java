package Model.Expressions;

import Model.Structures.MyIDictionary;
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
    public IValue evaluation(MyIDictionary<String, IValue> table) throws Exception {
        if (value.getType().equals(new IntType())) {
            return new IntValue(((IntValue) value).getValue());
        }
        return new BoolValue(((BoolValue) value).getValue());
    }
}
