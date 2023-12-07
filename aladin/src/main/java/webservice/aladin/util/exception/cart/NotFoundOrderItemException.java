package webservice.aladin.util.exception.cart;

public class NotFoundOrderItemException extends IllegalArgumentException {
    public NotFoundOrderItemException(String message) {
        super(message);
    }
}
