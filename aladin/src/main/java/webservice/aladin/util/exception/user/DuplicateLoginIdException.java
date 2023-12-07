package webservice.aladin.util.exception.user;

public class DuplicateLoginIdException extends IllegalArgumentException {
    public DuplicateLoginIdException(String message) {
        super(message);
    }
}
