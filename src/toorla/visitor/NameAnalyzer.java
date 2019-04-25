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
import toorla.symbolTable.symbolTableItem.varItems.VarSymbolTableItem;

import javax.sound.midi.SysexMessage;
import java.util.List;

public class NameAnalyzer implements Visitor<Void> {
    private SymbolTable symbolTable = new SymbolTable();
    int varIndex = 1;
    int scopeIndex = 0;
    String SCOPE_PREFIX = "#SCOPE";

    private String generateScopeName(){
        scopeIndex++;
        return SCOPE_PREFIX + String.valueOf(scopeIndex);
    }

    public NameAnalyzer(){
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
        block.symbolTable = symbolTable.top;
        scopeIndex++;
        ScopeSymbolTableItem scopeSymbolTableItem = new ScopeSymbolTableItem(generateScopeName(), symbolTable.top);
        symbolTable.top.push(scopeSymbolTableItem.getSymbolTable());
        for (Statement statement : block.body) {
            statement.accept(this);
        }
        symbolTable.top.pop();
        return null;
    }

    @Override
    public Void visit(Conditional conditional) {
        conditional.getThenStatement().accept(this);
        conditional.getElseStatement().accept(this);
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
        String varName = localVarDef.getLocalVarName().getName();
        LocalVariableSymbolTableItem localVariableSymbolTableItem = new LocalVariableSymbolTableItem(varName, varIndex);
        try {
            symbolTable.top.put(localVariableSymbolTableItem);
        }
        catch (ItemAlreadyExistsException exception) {
            exception.emitErrorMessage(localVarDef.line, varName);
        }
        localVarDef.getLocalVarName().setIndex(varIndex);
        varIndex++;
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
        try {
            symbolTable.top.put(myClassScope);
        }
        catch (ItemAlreadyExistsException exception) {
            exception.emitErrorMessage(classDeclaration.line, classDeclaration.getName().getName(), "class");
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
        String className = entryClassDeclaration.getName().getName();
        ClassSymbolTableItem myClassScope = new ClassSymbolTableItem(className, symbolTable.top);
        try {
            symbolTable.top.put(myClassScope);
        }
        catch (ItemAlreadyExistsException exception) {
            exception.emitErrorMessage(entryClassDeclaration.line, entryClassDeclaration.getName().getName(), "class");
        }
        symbolTable.top.push(myClassScope.getSymbolTable());
        for (ClassMemberDeclaration md : entryClassDeclaration.getClassMembers()) {
            md.accept(this);
        }
        symbolTable.top.pop();
        return null;
    }

    @Override
    public Void visit(FieldDeclaration fieldDeclaration) {
        fieldDeclaration.symbolTable = symbolTable.top;

        FieldSymbolTableItem ft = new FieldSymbolTableItem(fieldDeclaration.getIdentifier().getName());
        ft.setAccessModifier(fieldDeclaration.getAccessModifier());
        ft.setType(fieldDeclaration.getType());
        try {
            if (fieldDeclaration.getIdentifier().getName().equals("length"))
                throw new LengthFieldDeclarationException();

            try {
                symbolTable.top.putClassMembers(ft, symbolTable.top);

            }
            catch (ItemAlreadyExistsException exception) {
                exception.emitErrorMessage(fieldDeclaration.line, fieldDeclaration.getIdentifier().getName(), "field");
            }
        }
        catch (LengthFieldDeclarationException exception) {
            exception.emitErrorMessage(fieldDeclaration.line);
        }
        return null;
    }

    @Override
    public Void visit(ParameterDeclaration parameterDeclaration) {
        parameterDeclaration.symbolTable = symbolTable.top;
        String argName = parameterDeclaration.getIdentifier().getName();
        LocalVariableSymbolTableItem lvt = new LocalVariableSymbolTableItem(argName, varIndex);
        parameterDeclaration.getIdentifier().setIndex(varIndex);
        varIndex ++;
        try {
            symbolTable.top.put(lvt);
        }
        catch (ItemAlreadyExistsException exception) {
            exception.emitErrorMessage(parameterDeclaration.line, argName);
        }
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
            symbolTable.top.putClassMembers(methodSymbolTableItem, symbolTable.top);
        }
        catch (ItemAlreadyExistsException exception) {
            exception.emitErrorMessage(methodDeclaration.line, methodName, "method");
        }
//        SymbolTable st = new SymbolTable(symbolTable.top);
        symbolTable.top.push(methodSymbolTableItem.getSymbolTable());
        for (ParameterDeclaration param : methodDeclaration.getArgs()) {
            param.accept(this);
        }
        for (Statement statement: methodDeclaration.getBody()) {
            statement.accept(this);
        }
        varIndex = 1;
        symbolTable.top.pop();
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
        ClassSymbolTableItem myClassScope = new ClassSymbolTableItem("Any", symbolTable.top);
        try {
            symbolTable.top.put(myClassScope);
        }
        catch (ItemAlreadyExistsException exception) {
        }

        List<ClassDeclaration> classes;
        classes = program.getClasses();
        for (ClassDeclaration cd : classes) {
            cd.accept(this);
        }
        return null;
    }
}
