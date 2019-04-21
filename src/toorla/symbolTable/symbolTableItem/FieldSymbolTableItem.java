package toorla.symbolTable.symbolTableItem;


import toorla.ast.declaration.classDecs.classMembersDecs.AccessModifier;
import toorla.types.Type;

public class FieldSymbolTableItem extends SymbolTableItem {
    private Type type;
    private AccessModifier accessModifier;

    @Override
    public String getKey() { return name; }

    public Type getType() { return type; }

    public AccessModifier getAccessModifier() { return accessModifier; }
}
