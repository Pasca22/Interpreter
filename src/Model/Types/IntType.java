package Model.Types;

import Model.Values.IValue;
import Model.Values.IntValue;

public class IntType implements IType {

    public boolean equals(Object newObject){
        return newObject instanceof IntType;
    }

    @Override
    public String toString() { return "int";}

    @Override
    public IValue defaultValue() {
        return new IntValue(0);
    }

    @Override
    public IType deepCopy() {
        return new IntType();
    }
}
