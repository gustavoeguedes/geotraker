package br.com.gustavoeguedes.geotraker.exception;

public class ResourceNotFoundException extends GeoTrackerException {
    private final String detail;

    public ResourceNotFoundException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public org.springframework.http.ProblemDetail toProblemDetail() {
        var pd = org.springframework.http.ProblemDetail.forStatus(404);
        pd.setTitle("Resource not found");
        pd.setDetail(detail);
        return pd;
    }
}
