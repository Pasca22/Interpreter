package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Structures.MyIDictionary;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;

import java.util.Objects;

public class RelationalExpression implements Expression {

    Expression e1, e2;
    String relationalOperation;
    @Override
    public IValue evaluation(MyIDictionary<String, IValue> table) throws Exception {

        IValue v1, v2;
        v1 = e1.evaluation(table);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.evaluation(table);
            if (v2.getType().equals(new IntType())) {
                int n1 = ((IntValue) v1).getValue();
                int n2 = ((IntValue) v2).getValue();

                if (Objects.equals(relationalOperation, "<")) return new BoolValue(n1 < n2);
                if (Objects.equals(relationalOperation, "<=")) return new BoolValue(n1 <= n2);
                if (Objects.equals(relationalOperation, ">")) return new BoolValue(n1 > n2);
                if (Objects.equals(relationalOperation, ">=")) return new BoolValue(n1 >= n2);
                if (Objects.equals(relationalOperation, "==")) return new BoolValue(n1 == n2);
                if (Objects.equals(relationalOperation, "!=")) return new BoolValue(n1 != n2);

            } else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");
        return null;
    }
}
