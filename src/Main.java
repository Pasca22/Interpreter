import Controller.Controller;
import Model.Expressions.ArithmeticExpression;
import Model.Expressions.ValueExpression;
import Model.Expressions.VariableExpression;
import Model.Statements.*;
import Model.Structures.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;
import Repository.Repository;
import View.ExitCommand;
import View.RunExample;
import View.TextMenu;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        IStatement s1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new
                        VariableExpression("v"))));

        IStatement s2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+", new ValueExpression(new IntValue(2)), new
                                ArithmeticExpression("*", new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression("+", new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));

        IStatement s3 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new
                                        IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));

        MyIStack<IStatement> exeStack1 = new MyStack<>();
        MyIDictionary<String, IValue> systemTable1 = new MyDictionary<>();
        MyIList<IValue> outputList1 = new MyList<>();

        MyIStack<IStatement> exeStack2 = new MyStack<>();
        MyIDictionary<String, IValue> systemTable2 = new MyDictionary<>();
        MyIList<IValue> outputList2 = new MyList<>();

        MyIStack<IStatement> exeStack3 = new MyStack<>();
        MyIDictionary<String, IValue> systemTable3 = new MyDictionary<>();
        MyIList<IValue> outputList3 = new MyList<>();

        ProgramState p1 = new ProgramState(exeStack1, systemTable1, outputList1, s1);
        ProgramState p2 = new ProgramState(exeStack2, systemTable2, outputList2, s2);
        ProgramState p3 = new ProgramState(exeStack3, systemTable3, outputList3, s3);

        Repository repository1 = new Repository();
        repository1.addProgramState(p1);
        Controller controller1 = new Controller(repository1);

        Repository repository2 = new Repository();
        repository2.addProgramState(p2);
        Controller controller2 = new Controller(repository2);

        Repository repository3 = new Repository();
        repository3.addProgramState(p3);
        Controller controller3 = new Controller(repository3);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", s1.toString(), controller1));
        menu.addCommand(new RunExample("2", s2.toString(), controller2));
        menu.addCommand(new RunExample("3", s3.toString(), controller3));

        menu.show();

        }
    }