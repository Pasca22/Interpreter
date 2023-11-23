package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Structures.ProgramState;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Values.StringValue;

import java.io.BufferedReader;

public class ReadFileStatement implements IStatement {

    Expression expression;
    String variableName;

    public ReadFileStatement(Expression e, String name) {
        expression = e;
        variableName = name;
    }

    @Override
    public String toString()
    {
        return "readFile(" + this.expression.toString() + ", " + this.variableName + ");";
    }
    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        MyIHeap heap = state.getHeap();

        if (!symbolTable.isDefined(variableName)) {
            throw new MyException("Variable " + variableName + " is not defined");
        }

        IValue value = symbolTable.lookup(variableName);

        if (!value.getType().equals(new IntType())) {
            throw new MyException("Variable type is not int");
        }

        IntValue intValue;
        IValue expressionValue = expression.evaluation(symbolTable, heap);

        if (!expressionValue.getType().equals(new StringType())) {
            throw new MyException("The expression is not of StringType");
        }

        StringValue fileName = (StringValue) expressionValue;
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

        BufferedReader bufferedReader = fileTable.lookup(fileName);

        if (bufferedReader == null) {
            throw new MyException("File was not found");
        }

        String readValue;
        try
        {
            readValue = bufferedReader.readLine();
        }
        catch (Exception e)
        {
            throw new MyException(e.toString());
        }

        if (readValue == null)
            intValue = new IntValue(0);
        else
            intValue = new IntValue(Integer.parseInt(readValue));

        symbolTable.update(this.variableName, intValue);

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFileStatement(expression.deepCopy(), variableName);
    }
}
