package pl.sdacademy.carrental.exceptions;

public class BranchException extends RuntimeException {

    public BranchException(){super();}

    public BranchException(final String message){super(message);}

    public BranchException(final String message, final Throwable cause){super(message,cause);}

    public BranchException(final Throwable cause){super(cause);}

}
