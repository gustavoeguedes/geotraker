package br.com.gustavoeguedes.geotraker.exception;

import org.springframework.http.ProblemDetail;

public abstract class GeoTrackerException extends  RuntimeException {
    public GeoTrackerException(String message) {
        super(message);
    }

    public GeoTrackerException(Throwable cause) {
        super(cause);
    }

    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(500);
        pd.setTitle("GeoTracker Internal Server Error");
        pd.setDetail("Contact GeoTracker support");
        return pd;
    }
}