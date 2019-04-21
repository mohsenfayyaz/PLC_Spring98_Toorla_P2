package toorla.symbolTable.symbolTableItem;

public class ClassSymbolTableItem extends SymbolTableItem {
    private String parentName;

    @Override
    public String getKey() { return name; }

    public String getParentName() {
        return parentName;
        }
}
