package android.firefly.coltfashion.cc.netcomm.base;

/**
 * Created by msz on 2015/9/18.
 */
public class FireflyException extends Exception {

    static private final String EXCEPTION_STRING = "firefly exception message";

    public FireflyException() {
        super(EXCEPTION_STRING);
    }

    public FireflyException(String message) {
        super(String.format("%s [%s]", EXCEPTION_STRING, message));
    }


    public FireflyException(String message, Throwable innerException) {
        super(String.format("%s [%s]", EXCEPTION_STRING, message), innerException);
    }

}
