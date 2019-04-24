package toorla.symbolTable.symbolTableItem;

import toorla.ast.declaration.classDecs.classMembersDecs.AccessModifier;
import toorla.symbolTable.symbolTableItem.varItems.LocalVariableSymbolTableItem;
import toorla.types.Type;

import java.util.ArrayList;


public class ScopeSymbolTableItem extends SymbolTableItem {
    private AccessModifier accessModifier;
    private Type returnType;
    private ArrayList<LocalVariableSymbolTableItem> args = new ArrayList<>();
    @Override
    public String getKey() { return name; }

    public Type getReturnType() { return returnType; }

    public ArrayList<LocalVariableSymbolTableItem> getArgs() { return args; }

    public AccessModifier getAccessModifier() { return accessModifier; }
}
