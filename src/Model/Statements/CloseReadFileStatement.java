package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Structures.ProgramState;
import Model.Types.IType;
import Model.Types.StringType;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;

public class CloseReadFileStatement implements IStatement {

    Expression expression;

    public CloseReadFileStatement(Expression e) {
        expression = e;
    }

    @Override
    public String toString()
    {
        return "closeReadFile(" + this.expression.toString() + ");";
    }
    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        MyIHeap heap = state.getHeap();

        IValue expressionValue = expression.evaluation(symbolTable, heap);

        if (!expressionValue.getType().equals(new StringType())) {
            throw new MyException("The expression is not of StringType");
        }

        StringValue fileName = (StringValue)expressionValue;

        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

        BufferedReader bufferedReader = fileTable.lookup(fileName);

        if (bufferedReader == null) {
            throw new MyException("File with given name was not found");
        }

        try {
            bufferedReader.close();
        }
        catch (Exception e) {
            throw new MyException(e.toString());
        }

        fileTable.remove(fileName);
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws Exception {
        IType expressionType = this.expression.typeCheck(typeEnv);

        if (!(expressionType instanceof StringType)) {
            throw new MyException("The given expression is not of StringType");
        }

        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new CloseReadFileStatement(expression.deepCopy());
    }
}
