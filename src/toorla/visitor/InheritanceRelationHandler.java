package toorla.visitor;

import org.antlr.v4.parse.ScopeParser;
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
import toorla.symbolTable.exceptions.LengthFieldDeclarationException;
import toorla.symbolTable.symbolTableItem.ClassSymbolTableItem;
import toorla.symbolTable.symbolTableItem.FieldSymbolTableItem;
import toorla.symbolTable.symbolTableItem.MethodSymbolTableItem;
import toorla.symbolTable.symbolTableItem.ScopeSymbolTableItem;
import toorla.symbolTable.symbolTableItem.varItems.LocalVariableSymbolTableItem;
import java.util.List;

public class InheritanceRelationHandler implements Visitor<Void> {
    private SymbolTable symbolTable = new SymbolTable();
    int varIndex = 1;
    int scopeIndex = 0;
    private SymbolTable programSymbolTable;

    public InheritanceRelationHandler(SymbolTable symbolTable){
        this.programSymbolTable = symbolTable;
    }
    @Override
    public Void visit(PrintLine printStat) {
        return null;
    }

    @Override
    public Void visit(Assign assignStat) {
        return null;
    }

    @Override
    public Void visit(Block block) {
        return null;
    }

    @Override
    public Void visit(Conditional conditional) {
        return null;
    }

    @Override
    public Void visit(While whileStat) {
        whileStat.body.accept(this);
        return null;
    }

    @Override
    public Void visit(Return returnStat) {
        return null;
    }

    @Override
    public Void visit(Break breakStat) {
        return null;
    }

    @Override
    public Void visit(Continue continueStat) {
        return null;
    }

    @Override
    public Void visit(Skip skip) {
        return null;
    }

    @Override
    public Void visit(LocalVarDef localVarDef) {
        return null;
    }

    @Override
    public Void visit(IncStatement incStatement) {
        return null;
    }

    @Override
    public Void visit(DecStatement decStatement) {
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
    public Void visit(Program program) { // DONE
        List<ClassDeclaration> classes;
        classes = program.getClasses();
        for (ClassDeclaration cd : classes) {
            cd.accept(this);
        }
        return null;
    }
}
