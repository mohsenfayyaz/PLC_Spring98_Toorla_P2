package toorla.visitor;

import sun.jvm.hotspot.debugger.cdbg.Sym;
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
    SymbolTable symbolTable = new SymbolTable();
    int varIndex = 1;

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
        ScopeSymbolTableItem scopeSymbolTableItem = new ScopeSymbolTableItem();
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
        LocalVariableSymbolTableItem lvt = new LocalVariableSymbolTableItem(localVarDef.getLocalVarName().toString(), varIndex);
        try {
            symbolTable.top.put(lvt);
        }
        catch (Exception exception) {
            System.out.println("Error:Line:#" + localVarDef.line + ":Redefinition of Local Variable localVar in current scope");
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
            exception.emitErrorMessage(classDeclaration.line, classDeclaration.getName().toString(), "class");
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
            exception.emitErrorMessage(entryClassDeclaration.line, entryClassDeclaration.getName().toString(), "class");
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
        FieldSymbolTableItem ft = new FieldSymbolTableItem(fieldDeclaration.getIdentifier().toString());
        ft.setAccessModifier(fieldDeclaration.getAccessModifier());
        ft.setType(fieldDeclaration.getType());
        try {
            if (fieldDeclaration.getIdentifier().toString().equals("length"))
                throw new LengthFieldDeclarationException();
        }
        catch (LengthFieldDeclarationException exception) {
            exception.emitErrorMessage(fieldDeclaration.line);
        }
        try {
            symbolTable.top.put(ft);
        }
        catch (ItemAlreadyExistsException exception) {
            exception.emitErrorMessage(fieldDeclaration.line, fieldDeclaration.getIdentifier().toString(), "field");
        }
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
            symbolTable.top.put(methodSymbolTableItem);
        }
        catch (ItemAlreadyExistsException exception) {
            exception.emitErrorMessage(methodDeclaration.line, methodDeclaration.getName().toString(), "method");
        }
//        SymbolTable st = new SymbolTable(symbolTable.top);
        symbolTable.top.push(methodSymbolTableItem.getSymbolTable());
        for (ParameterDeclaration param : methodDeclaration.getArgs()) {
            LocalVariableSymbolTableItem lvt = new LocalVariableSymbolTableItem(param.getIdentifier().toString(), varIndex);
            param.getIdentifier().setIndex(varIndex);
            varIndex ++;
            try {
                symbolTable.top.put(lvt);
            }
            catch (Exception exception) {
                // nothing to do with this one cause absolutely no errors gonna happen
            }
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
        List<ClassDeclaration> classes;
        classes = program.getClasses();
        for (ClassDeclaration cd : classes) {
            cd.accept(this);
        }
        return null;
    }
}
