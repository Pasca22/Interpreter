package Model.Values;

import Model.Types.IType;
import Model.Types.IntType;

public class BoolValue implements IValue{
    boolean value;
    public BoolValue(boolean v){value =v;}

    public boolean getValue() {return value;}
    public String toString(){return "" + value;};
    @Override
    public IType getType() { return new IntType();}
}
