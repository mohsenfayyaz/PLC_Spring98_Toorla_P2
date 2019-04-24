package toorla.visitor;

import toorla.ast.Program;
import toorla.ast.Tree;
import toorla.ast.declaration.classDecs.ClassDeclaration;
import toorla.ast.declaration.classDecs.EntryClassDeclaration;
import toorla.ast.declaration.classDecs.classMembersDecs.ClassMemberDeclaration;
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
import toorla.symbolTable.SymbolTable;
import toorla.symbolTable.exceptions.ItemAlreadyExistsException;
import toorla.symbolTable.symbolTableItem.ClassSymbolTableItem;
import toorla.symbolTable.symbolTableItem.MethodSymbolTableItem;
import toorla.symbolTable.symbolTableItem.varItems.LocalVariableSymbolTableItem;
import toorla.symbolTable.symbolTableItem.varItems.VarSymbolTableItem;

import java.util.List;

public class NameAnalyzer implements Visitor<Void> {
    SymbolTable symbolTable = new SymbolTable();
    int varIndex = 1;

    public NameAnalyzer(){
        symbolTable.push(symbolTable);
        symbolTable.root = symbolTable;
    }
    @Override
    public Void visit(PrintLine printStat) {
        try {
//            printStat.getArg().accept(this);
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
        //TODO
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
        //TODO
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
        //TODO
        String var_name = localVarDef.getLocalVarName().getName();


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
        return null;
    }

    @Override
    public Void visit(Minus minusExpr) {
        return null;
    }

    @Override
    public Void visit(Times timesExpr) {
        return null;
    }

    @Override
    public Void visit(Division divExpr) {
        return null;
    }

    @Override
    public Void visit(Modulo moduloExpr) {
        return null;
    }

    @Override
    public Void visit(Equals equalsExpr) {
        return null;
    }

    @Override
    public Void visit(GreaterThan gtExpr) {
        return null;
    }

    @Override
    public Void visit(LessThan lessThanExpr) {
        return null;
    }

    @Override
    public Void visit(And andExpr) {
        return null;
    }

    @Override
    public Void visit(Or orExpr) {
        return null;
    }

    @Override
    public Void visit(Neg negExpr) {
        return null;
    }

    @Override
    public Void visit(Not notExpr) {
        return null;
    }

    @Override
    public Void visit(MethodCall methodCall) {
        return null;
    }

    @Override
    public Void visit(Identifier identifier) {
        return null;
    }

    @Override
    public Void visit(Self self) {
        return null;
    }

    @Override
    public Void visit(IntValue intValue) {
        return null;
    }

    @Override
    public Void visit(NewArray newArray) {
        return null;
    }

    @Override
    public Void visit(BoolValue booleanValue) {
        return null;
    }

    @Override
    public Void visit(StringValue stringValue) {
        return null;
    }

    @Override
    public Void visit(NewClassInstance newClassInstance) {
        return null;
    }

    @Override
    public Void visit(FieldCall fieldCall) {
        return null;
    }

    @Override
    public Void visit(ArrayCall arrayCall) {
        return null;
    }

    @Override
    public Void visit(NotEquals notEquals) {
        return null;
    }

    @Override
    public Void visit(ClassDeclaration classDeclaration) { //DONE
        String className = classDeclaration.getName().getName();
        ClassSymbolTableItem myClassScope = new ClassSymbolTableItem(className, symbolTable.top);
        try {
            symbolTable.top.put(myClassScope);
        }
        catch (ItemAlreadyExistsException e) {
            System.out.println("Error:Line:#"+classDeclaration.line+":Redefinition of Class "+className );
        }
        symbolTable.top.push(myClassScope.getSymbolTable());
        for (ClassMemberDeclaration md : classDeclaration.getClassMembers()) {
            md.accept(this);
        }
        symbolTable.top.pop();

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
        MethodSymbolTableItem methodSymbolTableItem = new MethodSymbolTableItem(methodDeclaration.getName().toString());
        methodSymbolTableItem.setAccessModifier(methodDeclaration.getAccessModifier());
        methodSymbolTableItem.setReturnType(methodDeclaration.getReturnType());
        try {
            symbolTable.put(methodSymbolTableItem);
        }
        catch (Exception exception) {
            //
        }
        SymbolTable st = new SymbolTable(symbolTable);
        for (ParameterDeclaration param : methodDeclaration.getArgs()) {
            LocalVariableSymbolTableItem lvt = new LocalVariableSymbolTableItem(param.getIdentifier().toString(), varIndex);
            try {
                st.put(lvt);
            }
            catch (Exception exception) {
                // nothing to do with this one cause absolutely no errors gonna happen
            }
        }
        for (Statement statement: methodDeclaration.getBody()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(LocalVarsDefinitions localVarsDefinitions) {
        for (LocalVarDef var : localVarsDefinitions.getVarDefinitions()) {
            var.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(Program program) { // DONE
        List<ClassDeclaration> classes;
        classes = program.getClasses();
        for (ClassDeclaration cd : classes) {
            cd.accept(this);
        }
        return null;
    }
}
