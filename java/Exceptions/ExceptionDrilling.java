import java.io.IOException;

class ExceptionDrilling {

    // This method declares that it throws an IOException.
    // This means any code calling this method must handle or propagate the exception.
    static void add() throws IOException {
        System.out.println("hi");
        // An IOException is explicitly thrown here.
        // Since the exception is not caught within this method, it propagates to the caller.
        throw new IOException("Exception");
    }

    // This method also declares that it throws an IOException.
    // The add() method is called here, and since add() can throw an IOException,
    // performOP() is also required to propagate or handle the exception.
    static void performOP() throws IOException {
        // Exception from add() is propagated up to performOP().
        add();
    }

    public static void main(String[] args) {
        
        try {
            // performOP() calls add(), and if an exception is thrown in add(),
            // it propagates through performOP() to this point.
            performOP();
        } catch (IOException e) {
            e.printStackTrace();
        }

    
        System.out.println("END");
    }
}
