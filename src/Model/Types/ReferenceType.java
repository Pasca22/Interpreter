package Model.Types;

import Model.Values.IValue;
import Model.Values.ReferenceValue;

public class ReferenceType implements IType {

    IType inner;

    public ReferenceType(IType inner) {
        this.inner = inner;
    }

    public boolean equals(Object another){
        if (another instanceof ReferenceType)
            return inner.equals(((ReferenceType) another).getInner());
        else
            return false;
    }

    @Override
    public String toString() {
        return "Ref(" + inner.toString()+ ")";
    }

    public IType getInner() {
        return inner;
    }

    @Override
    public IValue defaultValue() {
        return new ReferenceValue(0, inner);
    }

    @Override
    public IType deepCopy() {
        return new ReferenceType(inner.deepCopy());
    }
}
