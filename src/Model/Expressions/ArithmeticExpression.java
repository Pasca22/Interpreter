package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Structures.MyIDictionary;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Types.IntType;

import java.util.Objects;

public class ArithmeticExpression implements Expression {

    Expression e1, e2;
    String operand; // "+"-plus, "-"-minus, "*"-star, "/"-divide

    public ArithmeticExpression(String op, Expression ex1, Expression ex2) {
        operand = op;
        e1 = ex1;
        e2 = ex2;
    }

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
                if (Objects.equals(operand, "+")) return new IntValue(n1 + n2);
                if (Objects.equals(operand, "-")) return new IntValue(n1 - n2);
                if (Objects.equals(operand, "*")) return new IntValue(n1 * n2);
                if (Objects.equals(operand, "/"))
                    if (n2 == 0) throw new MyException("division by zero");
                    else return new IntValue(n1 / n2);
            } else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");
        return null;
    }
}

