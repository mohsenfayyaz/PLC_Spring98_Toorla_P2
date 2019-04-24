package toorla.visitor;

import toorla.ast.Program;
import toorla.ast.declaration.classDecs.ClassDeclaration;
import toorla.ast.declaration.classDecs.EntryClassDeclaration;
import toorla.ast.declaration.classDecs.classMembersDecs.FieldDeclaration;
import toorla.ast.declaration.classDecs.classMembersDecs.MethodDeclaration;
import toorla.ast.declaration.localVarDecs.ParameterDeclaration;
import toorla.ast.expression.*;
import toorla.ast.expression.binaryExpression.*;
import toorla.ast.expression.unaryExpression.Neg;
import toorla.ast.expression.unaryExpression.Not;
import toorla.ast.expression.value.BoolValue;
import toorla.ast.expression.value.IntValue;
import toorla.ast.expression.value.StringValue;
import toorla.ast.statement.*;
import toorla.ast.statement.localVarStats.LocalVarDef;
import toorla.ast.statement.localVarStats.LocalVarsDefinitions;
import toorla.ast.statement.returnStatement.Return;

import java.util.List;

public class NameAnalyzer implements Visitor<Void> {
    @Override
    public Void visit(PrintLine printStat) {
        try {
            printStat.getArg().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(Assign assignStat) {
        try {
            assignStat.getLvalue().accept(this);
        }
        catch (Exception exception) {
            //
        }
        try {
            assignStat.getRvalue().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(Block block) {
        for (Statement s : block.body) {
            try {
                s.accept(this);
            }
            catch (Exception exception) {
                //
            }
        }
        return null;
    }

    @Override
    public Void visit(Conditional conditional) {
        try {
            conditional.getCondition().accept(this);
        }
        catch (Exception exception) {
            //
        }
        try {
            conditional.getThenStatement().accept(this);
        }
        catch (Exception exception) {
            //
        }
        try {
            conditional.getElseStatement().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(While whileStat) {
        try {
            whileStat.expr.accept(this);
        }
        catch (Exception exception) {
            //
        }
        try {
            whileStat.body.accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(Return returnStat) {
        try {
            returnStat.getReturnedExpr().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(Break breakStat) {
        // has nothing to check
        return null;
    }

    @Override
    public Void visit(Continue continueStat) {
        // has nothing to check
        return null;
    }

    @Override
    public Void visit(Skip skip) {
        // has nothing to check
        return null;
    }

    @Override
    public Void visit(LocalVarDef localVarDef) {
        // has a lot to do with this one
        return null;
    }

    @Override
    public Void visit(IncStatement incStatement) {
        try {
            incStatement.getOperand().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(DecStatement decStatement) {
        try {
            decStatement.getOperand().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(Plus plusExpr) {
        try {
            plusExpr.getLhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        try {
            plusExpr.getRhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(Minus minusExpr) {
        try {
            minusExpr.getLhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        try {
            minusExpr.getRhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(Times timesExpr) {
        try {
            timesExpr.getLhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        try {
            timesExpr.getRhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(Division divExpr) {
        try {
            divExpr.getLhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        try {
            divExpr.getRhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(Modulo moduloExpr) {
        try {
            moduloExpr.getLhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        try {
            moduloExpr.getRhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(Equals equalsExpr) {
        try {
            equalsExpr.getLhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        try {
            equalsExpr.getRhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(GreaterThan gtExpr) {
        try {
            gtExpr.getLhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        try {
            gtExpr.getRhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(LessThan lessThanExpr) {
        try {
            lessThanExpr.getLhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        try {
            lessThanExpr.getRhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(And andExpr) {
        try {
            andExpr.getLhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        try {
            andExpr.getRhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(Or orExpr) {
        try {
            orExpr.getLhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        try {
            orExpr.getRhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(Neg negExpr) {
        try {
            negExpr.getExpr().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(Not notExpr) {
        try {
            notExpr.getExpr().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(MethodCall methodCall) { // has some works to do with
//        try {
//            methodCall.getInstance().accept(this);
//        }
//        catch (Exception exception) {
            //
//        }
//        try {
//            methodCall.getMethodName()
//        }
//        catch (Exception exception) {
            //
//        }
        return null;
    }

    @Override
    public Void visit(Identifier identifier) {
        // ....
        return null;
    }

    @Override
    public Void visit(Self self) {
        // ...
        return null;
    }

    @Override
    public Void visit(IntValue intValue) {
        // ...
        return null;
    }

    @Override
    public Void visit(NewArray newArray) {
        return null;
    }

    @Override
    public Void visit(BoolValue booleanValue) {
        // ...
        return null;
    }

    @Override
    public Void visit(StringValue stringValue) {
        // ...
        return null;
    }

    @Override
    public Void visit(NewClassInstance newClassInstance) {
        // ...
        return null;
    }

    @Override
    public Void visit(FieldCall fieldCall) {
        // ...
        return null;
    }

    @Override
    public Void visit(ArrayCall arrayCall) {
        // ...
        return null;
    }

    @Override
    public Void visit(NotEquals notEquals) {
        try {
            notEquals.getLhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        try {
            notEquals.getRhs().accept(this);
        }
        catch (Exception exception) {
            //
        }
        return null;
    }

    @Override
    public Void visit(ClassDeclaration classDeclaration) {
        return null;
    }

    @Override
    public Void visit(EntryClassDeclaration entryClassDeclaration) {
        return null;
    }

    @Override
    public Void visit(FieldDeclaration fieldDeclaration) {
        return null;
    }

    @Override
    public Void visit(ParameterDeclaration parameterDeclaration) {
        return null;
    }

    @Override
    public Void visit(MethodDeclaration methodDeclaration) {
        return null;
    }

    @Override
    public Void visit(LocalVarsDefinitions localVarsDefinitions) {
        return null;
    }

    @Override
    public Void visit(Program program) {
        try {
            List<ClassDeclaration> classes;
            classes = program.getClasses();
//            for (ClassDeclaration cd : classes) {
//                cd.getClassMembers()
//            }
        }
        catch (Exception exception) {
            //
        }
        return null;
    }
}
