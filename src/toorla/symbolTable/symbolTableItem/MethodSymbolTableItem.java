package toorla.symbolTable.symbolTableItem;

import toorla.ast.declaration.classDecs.classMembersDecs.AccessModifier;
import toorla.symbolTable.symbolTableItem.varItems.LocalVariableSymbolTableItem;
import toorla.types.Type;

import java.util.ArrayList;

public class MethodSymbolTableItem extends SymbolTableItem {
    private AccessModifier accessModifier;
    private Type returnType;
//    private ArrayList<LocalVariableSymbolTableItem> args = new ArrayList<>();

    public MethodSymbolTableItem (String name) {
        this.name = name;
    }
    @Override
    public String getKey() { return name; }

    public Type getReturnType() {return returnType; }

//    public ArrayList<LocalVariableSymbolTableItem> getArgs() { return args; }

    public AccessModifier getAccessModifier() { return accessModifier; }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public void setAccessModifier(AccessModifier accessModifier) {
        this.accessModifier = accessModifier;
    }
}
