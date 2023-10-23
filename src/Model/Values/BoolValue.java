package Model.Values;

import Model.Types.IType;
import Model.Types.IntType;

public class BoolValue {
    boolean value;
    public BoolValue(boolean v){value =v;}

    public boolean getValue() {return value;}
    public String toString(){return "" + value;};
    public IType getType() { return new IntType();}
}
