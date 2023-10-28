package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Structures.MyIDictionary;
import Model.Types.BoolType;
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
    public IValue evaluation(MyIDictionary<String, IValue> table) throws Exception {

        IValue firstEvaluation = e1.evaluation(table);

        if (firstEvaluation.getType().equals(new BoolType())) {
            IValue secondEvaluation = e2.evaluation(table);

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
}
