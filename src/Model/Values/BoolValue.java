package Model.Values;

import Model.Types.BoolType;
import Model.Types.IType;
import Model.Types.IntType;

public class BoolValue implements IValue {
    boolean value;
    public BoolValue(boolean v){value =v;}

    public boolean getValue() {return value;}

    @Override
    public String toString(){return "" + value;};
    @Override
    public IType getType() { return new BoolType();}

    @Override
    public IValue deepCopy() {
        return new BoolValue(value);
    }
}
