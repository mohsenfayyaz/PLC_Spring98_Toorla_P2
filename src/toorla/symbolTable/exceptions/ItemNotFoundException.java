package toorla.symbolTable.exceptions;


public class ItemNotFoundException extends Exception {
    public void emitErrorMessage(int line) {
        System.out.println("Error:Line:#" + line + ":item not found");
    }
}
