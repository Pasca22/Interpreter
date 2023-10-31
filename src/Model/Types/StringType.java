package Model.Types;

import Model.Values.IValue;
import Model.Values.StringValue;

public class StringType implements IType {

    public boolean equals(Object newObject){
        return newObject instanceof StringType;
    }

    @Override
    public String toString() { return "string";}
    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }

    @Override
    public IType deepCopy() {
        return new StringType();
    }
}
