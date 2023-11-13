package Model.Values;

import Model.Types.IType;
import Model.Types.ReferenceType;

public class ReferenceValue implements IValue {

    int address;
    IType locationType;

    public ReferenceValue(int address, IType inner) {
        this.address = address;
        this.locationType = inner;
    }

    public int getAddress() {
        return address;
    }

    @Override
    public IType getType() {
        return new ReferenceType(locationType);
    }

    @Override
    public IValue deepCopy() {
        return new ReferenceValue(address, locationType.deepCopy());
    }
}
