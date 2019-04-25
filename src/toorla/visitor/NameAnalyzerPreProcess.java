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
import toorla.symbolTable.symbolTableItem.*;
import toorla.symbolTable.symbolTableItem.varItems.LocalVariableSymbolTableItem;
import toorla.symbolTable.symbolTableItem.varItems.VarSymbolTableItem;

import javax.sound.midi.SysexMessage;
import java.util.List;
import java.util.Map;

public class NameAnalyzerPreProcess implements Visitor<Void> {
    private SymbolTable symbolTable = new SymbolTable();
    int varIndex = 1;
    int scopeIndex = 0;
    private Map<String, SymbolTable> classSymbolTable;

    public Map<String, SymbolTable> getClassSymbolTable(){
        return classSymbolTable;
    }

    public NameAnalyzerPreProcess(){
        symbolTable.push(symbolTable);
        symbolTable.root = symbolTable;
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
        String className = classDeclaration.getName().getName();
        ClassSymbolTableItem myClassScope = new ClassSymbolTableItem(className, symbolTable.top);
        classSymbolTable.put(className, myClassScope.getSymbolTable());

        try {
            symbolTable.top.put(myClassScope);
        }
        catch (ItemAlreadyExistsException exception) {}
        symbolTable.top.push(myClassScope.getSymbolTable());
        for (ClassMemberDeclaration md : classDeclaration.getClassMembers()) {
            md.accept(this);
        }
        symbolTable.top.pop();
        return null;
    }

    @Override
    public Void visit(EntryClassDeclaration entryClassDeclaration) {
        String className = entryClassDeclaration.getName().getName();
        ClassSymbolTableItem myClassScope = new ClassSymbolTableItem(className, symbolTable.top);
        classSymbolTable.put(className, myClassScope.getSymbolTable());


        try {
            symbolTable.top.put(myClassScope);
        }
        catch (ItemAlreadyExistsException exception) {}
        symbolTable.top.push(myClassScope.getSymbolTable());
        for (ClassMemberDeclaration md : entryClassDeclaration.getClassMembers()) {
            md.accept(this);
        }
        symbolTable.top.pop();
        return null;
    }

    @Override
    public Void visit(FieldDeclaration fieldDeclaration) {
        FieldSymbolTableItem ft = new FieldSymbolTableItem(fieldDeclaration.getIdentifier().getName());
        ft.setAccessModifier(fieldDeclaration.getAccessModifier());
        ft.setType(fieldDeclaration.getType());
        try {
            symbolTable.top.put(ft);
        }
        catch (ItemAlreadyExistsException exception) {}
        return null;
    }

    @Override
    public Void visit(ParameterDeclaration parameterDeclaration) {
        return null;
    }

    @Override
    public Void visit(MethodDeclaration methodDeclaration) {
        methodDeclaration.symbolTable = symbolTable.top;

        String methodName = methodDeclaration.getName().getName();
        MethodSymbolTableItem methodSymbolTableItem = new MethodSymbolTableItem(methodName, symbolTable.top);
        methodSymbolTableItem.setAccessModifier(methodDeclaration.getAccessModifier());
        methodSymbolTableItem.setReturnType(methodDeclaration.getReturnType());
        try {
            symbolTable.top.put(methodSymbolTableItem);
        }
        catch (ItemAlreadyExistsException exception) {}
        symbolTable.top.push(methodSymbolTableItem.getSymbolTable());
        symbolTable.top.pop();
        return null;
    }

    @Override
    public Void visit(LocalVarsDefinitions localVarsDefinitions) {
        return null;
    }

    @Override
    public Void visit(Program program) { // DONE
        ClassSymbolTableItem myClassScope = new ClassSymbolTableItem("Any", symbolTable.top);
        try {
            symbolTable.top.put(myClassScope);
        }
        catch (ItemAlreadyExistsException exception) {}

        List<ClassDeclaration> classes;
        classes = program.getClasses();
        for (ClassDeclaration cd : classes) {
            cd.accept(this);
        }
        return null;
    }
}
