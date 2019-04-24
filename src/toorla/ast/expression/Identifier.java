package toorla.ast.expression;

import toorla.visitor.Visitor;

public class Identifier extends Expression {
    private String name;
    private int index;

    public Identifier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        if( name != null )
            return "(Identifier," + name + ")";
        else return "(Identifier,Dummy)";
    }
}
