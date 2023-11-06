import Controller.Controller;
import Model.Expressions.ArithmeticExpression;
import Model.Expressions.ValueExpression;
import Model.Expressions.VariableExpression;
import Model.Statements.*;
import Model.Structures.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
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

        IStatement s4 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenReadFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseReadFileStatement(new VariableExpression("varf"))))))))));

        Scanner input = new Scanner(System.in);
        System.out.print("Enter output file name: ");
        String fileName = input.next();
        System.out.println();

        ProgramState p1 = new ProgramState(s1);
        ProgramState p2 = new ProgramState(s2);
        ProgramState p3 = new ProgramState(s3);
        ProgramState p4 = new ProgramState(s4);

        Repository repository1 = new Repository(p1, fileName);
        Controller controller1 = new Controller(repository1);

        Repository repository2 = new Repository(p2, fileName);
        Controller controller2 = new Controller(repository2);

        Repository repository3 = new Repository(p3, fileName);
        Controller controller3 = new Controller(repository3);

        Repository repository4 = new Repository(p4, fileName);
        Controller controller4 = new Controller(repository4);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", s1.toString(), controller1));
        menu.addCommand(new RunExample("2", s2.toString(), controller2));
        menu.addCommand(new RunExample("3", s3.toString(), controller3));
        menu.addCommand(new RunExample("4", s4.toString(), controller4));

        menu.show();

        }
    }