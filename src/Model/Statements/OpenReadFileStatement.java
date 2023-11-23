package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Structures.ProgramState;
import Model.Types.StringType;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;

public class OpenReadFileStatement implements IStatement  {

    Expression expression;

    public OpenReadFileStatement(Expression e) {
        expression = e;
    }

    @Override
    public String toString()
    {
        return "openReadFile(" + this.expression.toString() + ");";
    }
    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIHeap heap = state.getHeap();

        IValue expressionValue = expression.evaluation(symbolTable, heap);

        if (!expressionValue.getType().equals(new StringType())) {
            throw new MyException("The expression is not of StringType");
        }

        StringValue fileName = (StringValue) expressionValue;

        if (fileTable.isDefined(fileName)) {
            throw new MyException("File with same name already open");
        }

        BufferedReader bufferedReader;
        try
        {
            bufferedReader = new BufferedReader(new FileReader(fileName.getValue()));
        }
        catch (Exception e)
        {
            throw new MyException(e.toString());
        }

        fileTable.update(fileName, bufferedReader);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new OpenReadFileStatement(expression.deepCopy());
    }
}
