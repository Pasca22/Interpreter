package Model.Types;

import Model.Values.IValue;
import Model.Values.IntValue;

public class IntType implements IType {

    public boolean equals(Object newObject){
        return newObject instanceof IntType;
    }
    public String toString() { return "int";}

    @Override
    public IValue defaultValue() {
        return new IntValue(0);
    }
}
