package exception;

public class ProductCollectionException extends Exception {
    public ProductCollectionException(String message) {
        super(message);
    }

    public static String notFound(long id) {
        return "there is no product identified by : "+id;
    }

    public static String alreadyExists(long id) {
        return "the indicated id is already bind to a product: "+id;
    }
}
