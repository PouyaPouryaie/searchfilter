package ir.bigz.ms.searchfilter.common.exception;

import ir.bigz.ms.searchfilter.common.utility.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class BasicExceptionHandler {


    private final ApplicationProperties applicationProperties;

    private final ApplicationContext applicationContext;

    @Autowired
    public BasicExceptionHandler(ApplicationProperties applicationProperties, ApplicationContext applicationContext) {
        this.applicationProperties = applicationProperties;
        this.applicationContext = applicationContext;
    }

    //    old-version
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for(FieldError error: ex.getBindingResult().getFieldErrors()){
            errors.put(error.getField(), error.getField().equals("nationalCode") ?
                    error.getDefaultMessage():
                    String.format(applicationProperties.getProperty("application.message.validationError.text"),
                    applicationProperties.getProperty(error.getField()) != null ?
                            applicationProperties.getProperty(error.getField()) :
                            error.getField()));
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

/*    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> error : ex.getConstraintViolations()) {
            String fieldName = ((PathImpl) error.getPropertyPath()).getLeafNode().getName();
            errors.put(fieldName, error.getMessage() != null ? error.getMessage() :
                    String.format(
                            applicationProperties.getProperty("validation.message.incorrect.nationalCode"),
                            (applicationProperties.getProperty(fieldName) != null) ?
                                    applicationProperties.getProperty(fieldName) : fieldName
                    )
            );
            break;
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }*/
}
