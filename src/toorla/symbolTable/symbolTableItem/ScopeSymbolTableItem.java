package toorla.symbolTable.symbolTableItem;

import toorla.ast.declaration.classDecs.classMembersDecs.AccessModifier;
import toorla.ast.statement.Statement;
import toorla.symbolTable.symbolTableItem.varItems.LocalVariableSymbolTableItem;
import toorla.types.Type;

import java.util.ArrayList;


public class ScopeSymbolTableItem extends SymbolTableItem {
    private ArrayList<Statement> statements;


    public ScopeSymbolTableItem () {
        statements = new ArrayList<Statement>();
    }

    public void addStatement(Statement statement) {
        statements.add(statement);
    }

    @Override
    public String getKey() { return name; }

}
