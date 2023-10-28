package Model.Types;

import Model.Values.BoolValue;
import Model.Values.IValue;

public class BoolType implements IType{
    public boolean equals(Object newObject){
        return newObject instanceof BoolType;
    }

    @Override
    public String toString() { return "bool";}

    @Override
    public IValue defaultValue() {
        return new BoolValue(false);
    }
}
