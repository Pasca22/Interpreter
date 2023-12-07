import Controller.Controller;
import Model.Expressions.*;
import Model.Statements.*;
import Model.Structures.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.ReferenceType;
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
                        VariableExpression("v"))
                )
        );

        IStatement s2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+", new ValueExpression(new IntValue(2)), new
                                ArithmeticExpression("*", new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression("+", new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))
                                )
                        )
                )
        );

        IStatement s3 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new
                                        IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))
                                )
                        )
                )
        );

        IStatement s4 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenReadFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseReadFileStatement(new VariableExpression("varf"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        IStatement s5 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new WhileStatement(
                                        new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)), ">"),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new AssignmentStatement("v", new ArithmeticExpression("-", new VariableExpression("v"), new ValueExpression(new IntValue(1))))
)
                                ),
                                new PrintStatement(new VariableExpression("v"))
                        )
                )
        );

        IStatement s6 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a"))
                                        )
                                )
                        )
                )
        );

        IStatement s7 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression("+",
                                                                new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))),
                                                                new ValueExpression(new IntValue(5))
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        IStatement s8 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                new CompoundStatement(new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression("+",
                                                        new HeapReadingExpression(new VariableExpression("v")),
                                                        new ValueExpression(new IntValue(5))
                                                )
                                        )
                                )
                        )
                )
        );

        IStatement s9 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new HeapAllocationStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(
                                                        new CompoundStatement(new HeapWritingStatement("a", new ValueExpression(new IntValue(30))),
                                                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))
                                                )
                                        )
                                )
                        )
                )
        );

        Scanner input = new Scanner(System.in);
        System.out.print("Enter output file name: ");
        String fileName = input.next();
        System.out.println();

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));

        try {
            ProgramState p1 = new ProgramState(s1);
            Repository repository1 = new Repository(p1, fileName);
            Controller controller1 = new Controller(repository1);
            menu.addCommand(new RunExample("1", s1.toString(), controller1));
        } catch (Exception ignored) {}

        try {
            ProgramState p2 = new ProgramState(s2);
            Repository repository2 = new Repository(p2, fileName);
            Controller controller2 = new Controller(repository2);
            menu.addCommand(new RunExample("2", s2.toString(), controller2));
        } catch (Exception ignored) {}

        try {
            ProgramState p3 = new ProgramState(s3);
            Repository repository3 = new Repository(p3, fileName);
            Controller controller3 = new Controller(repository3);
            menu.addCommand(new RunExample("3", s3.toString(), controller3));
        } catch (Exception ignored) {}

        try {
            ProgramState p4 = new ProgramState(s4);
            Repository repository4 = new Repository(p4, fileName);
            Controller controller4 = new Controller(repository4);
            menu.addCommand(new RunExample("4", s4.toString(), controller4));
        } catch (Exception ignored) {}

        try {
            ProgramState p5 = new ProgramState(s5);
            Repository repository5 = new Repository(p5, fileName);
            Controller controller5 = new Controller(repository5);
            menu.addCommand(new RunExample("5", s5.toString(), controller5));
        } catch (Exception ignored) {}

        try {
            ProgramState p6 = new ProgramState(s6);
            Repository repository6 = new Repository(p6, fileName);
            Controller controller6 = new Controller(repository6);
            menu.addCommand(new RunExample("6", s6.toString(), controller6));
        } catch (Exception ignored) {}

        try {
            ProgramState p7 = new ProgramState(s7);
            Repository repository7 = new Repository(p7, fileName);
            Controller controller7 = new Controller(repository7);
            menu.addCommand(new RunExample("7", s7.toString(), controller7));
        } catch (Exception ignored) {}

        try {
            ProgramState p8 = new ProgramState(s8);
            Repository repository8 = new Repository(p8, fileName);
            Controller controller8 = new Controller(repository8);
            menu.addCommand(new RunExample("8", s8.toString(), controller8));
        } catch (Exception ignored) {}

        try {
            ProgramState p9 = new ProgramState(s9);
            Repository repository9 = new Repository(p9, fileName);
            Controller controller9 = new Controller(repository9);
            menu.addCommand(new RunExample("9", s9.toString(), controller9));
        } catch (Exception ignored) {}

        menu.show();

        }
    }