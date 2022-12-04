package exception;

public class UserCollectionException extends Exception {
    public UserCollectionException(String message) {
        super(message);
    }

//    public static String notFound() {
//        return "the indicated id isn't bind to any user";
//    }

    public static String alreadyExists(String email) {
        return "an account already exists with this email address: "+email;
    }

    public static String badIdentifiers() {
        return "invalid email or password";
    }

    public static String notCreated() {
        return "creating user error";
    }
}
