package Model.Types;

public class BoolType {
    public boolean equals(Object newObject){
        return newObject instanceof IntType;
    }
    public String toString() { return "bool";}
}
