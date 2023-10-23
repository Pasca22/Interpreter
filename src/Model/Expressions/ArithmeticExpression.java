package Model.Expressions;

import Model.Structures.MyIDictionary;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Types.IntType;

public class ArithmeticExpression implements Expression {

    Expression e1, e2;
    int operand; // 1-plus, 2-minus, 3-star, 4-divide

    @Override
    public IValue evaluation(MyIDictionary<String, IValue> table) throws Exception {
        IValue v1, v2;
        v1 = e1.evaluation(table);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.evaluation(table);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (operand == 1) return new IntValue(n1 + n2);
                if (operand == 2) return new IntValue(n1 - n2);
                if (operand == 3) return new IntValue(n1 * n2);
                if (operand == 4)
                    if (n2 == 0) throw new Exception("division by zero");
                    else return new IntValue(n1 / n2);
            } else
                throw new Exception("second operand is not an integer");
        } else
            throw new Exception("first operand is not an integer");
        return null;
    }
}

