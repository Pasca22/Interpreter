package Model.Types;

public class IntType implements IType {

    public boolean equals(Object newObject){
        return newObject instanceof IntType;
    }
    public String toString() { return "int";}
}
