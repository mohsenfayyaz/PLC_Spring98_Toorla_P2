package toorla.symbolTable.exceptions;

public class LengthFieldDeclarationException extends Exception {


    public void emitErrorMessage(int line) {
        System.out.println("Error:Line:" + line + ":Definition of length as field of a class");
    }
}
