package com.softwareeng.universityapplication.rest.exceptionhandler;

import com.softwareeng.universityapplication.rest.exceptionhandler.model.HttpErrorResponse;
import com.softwareeng.universityapplication.businessLogic.exceptions.common.UniversityAppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {
//    @Autowired
//    private ExceptionMessageUtil messageUtil;

    @org.springframework.web.bind.annotation.ExceptionHandler(UniversityAppException.class)
    public ResponseEntity<HttpErrorResponse> handleServiceExceptions(UniversityAppException exception, HttpServletRequest request) {
        log.error("Exception from service layer: " + exception);
        return fillHttpErrorResponse(request, exception.getMessageKey(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpErrorResponse> handleBadCredentialsException(BadCredentialsException exception, HttpServletRequest request) {
        log.error("Exception while trying to log in: " + exception);
        return fillHttpErrorResponse(request, exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    private ResponseEntity<HttpErrorResponse> fillHttpErrorResponse(HttpServletRequest request, String localizedMessage, HttpStatus httpStatus) {
        HttpErrorResponse httpErrorResponse = new HttpErrorResponse();
        httpErrorResponse.setLocalizedMessage(localizedMessage);
        httpErrorResponse.setErrorCode(httpStatus.value());
        httpErrorResponse.setPath(request.getRequestURI());
        httpErrorResponse.setMessage("UniversityAppBE - " + httpStatus.getReasonPhrase());
        return ResponseEntity.status(httpStatus).body(httpErrorResponse);
    }
}
