package br.com.gustavoeguedes.geotraker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class VeiculoExistsException extends GeoTrackerException{
    private final String detail;
    public VeiculoExistsException(String detail) {
        super(detail);
        this.detail = detail;
    }



    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pd.setTitle("Veiculo already exists");
        pd.setDetail(detail);
        return pd;
    }
}
