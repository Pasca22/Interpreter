package Model.Values;

import Model.Types.IType;

public interface IValue {
    public IType getType();

    public IValue deepCopy();
}
