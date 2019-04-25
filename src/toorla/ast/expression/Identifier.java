package toorla.ast.expression;

import toorla.visitor.Visitor;

public class Identifier extends Expression {
    private String name;
    private int index = -1;

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
        if( name != null ) {
            if(index == -1) {
                return "(Identifier," + name + ")";
            }else{
                return "(Identifier," + name + "_" + index + ")";
            }
        }
        else return "(Identifier,Dummy)";
    }
}
