package toorla.symbolTable.exceptions;

import toorla.compileErrorException.CompileErrorException;

public class ItemAlreadyExistsException extends CompileErrorException {
    public void emitErrorMessage(int line, String name, String errorType) {
        if (errorType.equals("class")) {
            System.out.println("Error:Line:#" + line + ":Redefinition of Class "+ name );
        }
        else if (errorType.equals("field")) {
            System.out.println("Error:Line:#" + line +  ":Redefinition of Field " + name);
        }
        else if (errorType.equals("method")) {
            System.out.println("Error:Line:#" + line +  ":Redefinition of Method " + name);
        }
    }
}
