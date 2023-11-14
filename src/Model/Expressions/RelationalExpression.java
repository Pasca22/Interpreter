package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;

import java.util.Objects;

public class RelationalExpression implements Expression {

    Expression e1, e2;
    String relationalOperation;

    public RelationalExpression(Expression e1, Expression e2, String relation) {
        this.e1 = e1;
        this.e2 = e2;
        relationalOperation = relation;
    }

    @Override
    public String toString() {
        return e1 + relationalOperation + e2;
    }

    @Override
    public IValue evaluation(MyIDictionary<String, IValue> table,  MyIHeap heap) throws Exception {

        IValue v1, v2;
        v1 = e1.evaluation(table, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.evaluation(table, heap);
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

    @Override
    public Expression deepCopy() {
        return new RelationalExpression(e1.deepCopy(), e2.deepCopy(), relationalOperation);
    }
}
