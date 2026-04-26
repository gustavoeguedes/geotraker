package br.com.gustavoeguedes.geotraker.exception;

import br.com.gustavoeguedes.geotraker.exception.dto.InvalidParamDto;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        var invalidParams = e.getFieldErrors()
                .stream()
                .map(fe -> new InvalidParamDto(fe.getField(), fe.getDefaultMessage()))
                .toList();

        var pd = ProblemDetail.forStatus(400);

        pd.setTitle("invalid request parameters");
        pd.setDetail("there is invalid fields on the request");
        pd.setProperty("invalid-params", invalidParams);
        return pd;
    }

    @ExceptionHandler(GeoTrackerException.class)
    public ProblemDetail handleGeoTrackerException(GeoTrackerException e) {
        return e.toProblemDetail();
    }
}
