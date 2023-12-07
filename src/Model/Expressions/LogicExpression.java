package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.BoolType;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IValue;

import java.util.Objects;

public class LogicExpression implements Expression {

    Expression e1, e2;
    String logicOperation; // && - and, || - or

    public LogicExpression(Expression e1, Expression e2, String op) {
        this.e1 = e1;
        this.e2 = e2;
        this.logicOperation = op;
    }
    @Override
    public String toString() {
        return e1 + " " + logicOperation + " " + e2;
    }

    @Override
    public IValue evaluation(MyIDictionary<String, IValue> table,  MyIHeap heap) throws Exception {

        IValue firstEvaluation = e1.evaluation(table, heap);

        if (firstEvaluation.getType().equals(new BoolType())) {
            IValue secondEvaluation = e2.evaluation(table, heap);

            if (secondEvaluation.getType().equals(new BoolType())) {

                BoolValue firstEvaluationBool = (BoolValue) firstEvaluation;
                BoolValue secondEvaluationBool = (BoolValue) secondEvaluation;

                if (Objects.equals(logicOperation, "&&")) {
                    return new BoolValue(firstEvaluationBool.getValue() && secondEvaluationBool.getValue());
                } else  if (Objects.equals(logicOperation, "||")) {
                    return new BoolValue(firstEvaluationBool.getValue() || secondEvaluationBool.getValue());
                }  else {
                    throw new MyException("Logic Operation not existent");
                }
            }
            else {
                throw new MyException("Operand2 is not a boolean");
            }
        }
        else {
            throw new MyException("Operand2 is not a boolean");
        }


    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws Exception {
        IType type1, type2;
        type1=e1.typeCheck(typeEnv);
        type2=e2.typeCheck(typeEnv);
        if (type1.equals(new BoolType())) {
            if (type2.equals(new BoolType())) {
                return new BoolType();
            } else {
                throw new MyException("second operand is not an integer");
            }
        }else {
            throw new MyException("first operand is not an integer");
        }
    }

    @Override
    public Expression deepCopy() {
        return new LogicExpression(e1.deepCopy(), e2.deepCopy(), logicOperation);
    }
}
