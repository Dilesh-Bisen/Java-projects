class Custom_Exceptions extends Exception {
    public Custom_Exceptions(String message) {
        super(message);
    }
}

public class lab_5 {
    public static void main(String[] args) {
        try {
            @SuppressWarnings("unused")
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Exception is caught: " + e.getMessage());
        } finally {
            System.out.println("Finally the block has executed.");
        }

        // Custom Exceptions
        try {
            custom_method();
        } catch (Custom_Exceptions e) {
            System.out.println("Custom exception has caught : " + e.getMessage());
        }
    }

    public static void custom_method() throws Custom_Exceptions {
        throw new Custom_Exceptions("exception has occurred.");
    }
}
