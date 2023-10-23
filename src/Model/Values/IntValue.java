package Model.Values;

import Model.Types.IType;
import Model.Types.IntType;

public class IntValue {
    int value;
    public IntValue(int v){
        value =v;}

    public int getValue() {return value;}
    public String toString(){return "" + value;};
    public IType getType() { return new IntType();}
}
