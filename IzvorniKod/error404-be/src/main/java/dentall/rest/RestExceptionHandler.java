package dentall.rest;

import dentall.service.exceptions.ItemNotFoundException;
import dentall.service.exceptions.RequestDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<?> handleIllegalArgument(Exception e, WebRequest req){
        return getResponseEntity(e);
    }

    @ExceptionHandler(RequestDeniedException.class)
    protected ResponseEntity<?> handleRequestDenied(Exception e, WebRequest req){
        return getResponseEntity(e);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    protected ResponseEntity<?> handleItemNotFound(Exception e, WebRequest req){
        return getResponseEntity(e);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<?> handleRuntimeException(Exception e, WebRequest req){
        return getResponseEntity(e);
    }

    private ResponseEntity<?> getResponseEntity(Exception e) {
        logger.error(e.getMessage());

        Map<String, String> props = new HashMap<>();
        props.put("message", e.getMessage());
        props.put("status", "400");
        props.put("error", "Bad Request");
        return new ResponseEntity<>(props, HttpStatus.BAD_REQUEST);
    }
}
