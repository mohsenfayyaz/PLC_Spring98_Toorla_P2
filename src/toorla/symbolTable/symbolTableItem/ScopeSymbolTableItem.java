package toorla.symbolTable.symbolTableItem;

import toorla.ast.declaration.classDecs.classMembersDecs.AccessModifier;
import toorla.ast.statement.Statement;
import toorla.symbolTable.SymbolTable;
import toorla.symbolTable.symbolTableItem.varItems.LocalVariableSymbolTableItem;
import toorla.types.Type;

import java.util.ArrayList;


public class ScopeSymbolTableItem extends SymbolTableItem {
//    private ArrayList<Statement> statements;

    private SymbolTable symbolTable;
//    public ScopeSymbolTableItem () {
//        statements = new ArrayList<Statement>();
//    }

//    public void addStatement(Statement statement) {
//        statements.add(statement);
//    }

    public ScopeSymbolTableItem(String name, SymbolTable pre){
        this.name=name;
        this.symbolTable = new SymbolTable(pre);
    }


    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    @Override
    public String getKey() { return name; }

}
