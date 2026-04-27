package br.com.gustavoeguedes.geotraker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidDateRangeException extends GeoTrackerException {
    private final String detail;

    public InvalidDateRangeException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Invalid date range");
        pd.setDetail(detail);
        return pd;
    }
}
