package View;

import Controller.Controller;
import Model.Expressions.ArithmeticExpression;
import Model.Expressions.ValueExpression;
import Model.Expressions.VariableExpression;
import Model.Statements.*;
import Model.Structures.ProgramState;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IntValue;

import java.util.Scanner;

public class View {

    Controller controller;

    public View(Controller c) {
        controller = c;
    }

    void printMenu() {
        System.out.println("1 -> See the source code.");
        System.out.println("2 -> See the execution stack.");
        System.out.println("3 -> See the system table.");
        System.out.println("4 -> See the output list.");
        System.out.println("5 -> Execute one step.");
        System.out.println("6 -> Execute all steps.");
        System.out.println("7 -> Exit.");
    }

    void printChooseProgram() {
        System.out.println("1 -> Choose program 1.");
        System.out.println("2 -> Choose program 2.");
        System.out.println("3 -> Choose program 3.");
    }


    public IStatement chooseProgram(String choice) {
        return switch (choice) {
            case "1" -> new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                    new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new
                            VariableExpression("v"))));

            case "2" -> new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                    new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                            new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+", new ValueExpression(new IntValue(2)), new
                                    ArithmeticExpression("*", new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                    new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression("+", new VariableExpression("a"), new ValueExpression(new
                                            IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));

            case "3" -> new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                    new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                            new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                    new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new
                                            IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                            VariableExpression("v"))))));
            default -> null;
        };
    }



    public void run() {
        printChooseProgram();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        IStatement programChosen = chooseProgram(input);

        ProgramState programState = new ProgramState(programChosen);

        this.controller.addProgramState(programState);

        while (true) {
            printMenu();

            input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println(programChosen);
                    break;

                case "2":
                    System.out.println(programState.getStack());
                    break;

                case "3":
                    System.out.println(programState.getDictionary());
                    break;

                case "4":
                    System.out.println(programState.getOutputList());
                    break;

                case "5":
                    try {
                        this.controller.oneStep(programState);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    break;

                case "6":
                    try {
                        this.controller.allSteps();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    break;

                case "7":
                    return;

                default:
                    System.out.println("Wrong input!");
                    break;
            }

        }
    }
}
