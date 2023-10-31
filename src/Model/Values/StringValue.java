package Model.Values;

import Model.Types.IType;
import Model.Types.StringType;

public class StringValue implements IValue {

    String value;

    public StringValue(String v) {
        value = v;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public IValue deepCopy() {
        return new StringValue(value);
    }
}
