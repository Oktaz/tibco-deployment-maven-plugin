package com.mckesson.esb.maven;

/**
 *
 */
public class TibcoBusinessWorksClientException extends Exception {
    public TibcoBusinessWorksClientException() {
        super();
    }

    public TibcoBusinessWorksClientException(String message) {
        super(message);
    }

    public TibcoBusinessWorksClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public TibcoBusinessWorksClientException(Throwable cause) {
        super(cause);
    }

    protected TibcoBusinessWorksClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
