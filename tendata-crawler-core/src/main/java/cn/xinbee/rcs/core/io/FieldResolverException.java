package cn.xinbee.rcs.core.io;

public class FieldResolverException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public FieldResolverException() {
    }

    public FieldResolverException(String name) {
        this(name, null);
    }

    public FieldResolverException(String name, Throwable cause) {
        super("Resolve " + name + "  field failure", cause);
    }

    public FieldResolverException(Throwable cause) {
        super(cause);
    }
}
