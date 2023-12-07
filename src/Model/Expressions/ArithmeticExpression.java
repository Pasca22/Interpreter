package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.IType;
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
    public String toString() {
        return e1 + operand + e2;
    }

    @Override
    public IValue evaluation(MyIDictionary<String, IValue> table,  MyIHeap heap) throws Exception {
        IValue v1, v2;
        v1 = e1.evaluation(table, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.evaluation(table, heap);
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

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws Exception {
        IType type1, type2;
        type1=e1.typeCheck(typeEnv);
        type2=e2.typeCheck(typeEnv);
        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new IntType();
            } else {
                throw new MyException("second operand is not an integer");
            }
        }else {
            throw new MyException("first operand is not an integer");
        }
    }

    @Override
    public Expression deepCopy() {
        return new ArithmeticExpression(operand, e1.deepCopy(), e2.deepCopy());
    }
}

