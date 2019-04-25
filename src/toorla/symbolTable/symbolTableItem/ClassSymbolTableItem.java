package toorla.symbolTable.symbolTableItem;

import toorla.symbolTable.SymbolTable;
import toorla.types.AnonymousType;

public class ClassSymbolTableItem extends SymbolTableItem {
    private String parentName;
    private SymbolTable symbolTable;

    public ClassSymbolTableItem(String name, SymbolTable pre){
        this.name=name;
        this.symbolTable = new SymbolTable(pre);
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentName() {
        return parentName;
    }

    public SymbolTable getSymbolTable() { return symbolTable; }
    @Override
    public String getKey() { return name; }
}
