package com.salesianostriana.dam.superchollo.backend.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianostriana.dam.superchollo.backend.error.model.impl.ApiErrorImpl;
import com.salesianostriana.dam.superchollo.backend.error.model.impl.ApiValidationSubError;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.exception.CategoriaNotFoundException;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.exception.EmptyCategoriaListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.exception.EmptyProductoListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.exception.ProductoNotFoundException;
import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.exception.EmptySupermercadoListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.exception.SupermercadoNotFoundException;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.exception.EmptyUsuarioListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.exception.UserOldPasswordWrongException;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.exception.UsuarioNotFoundException;
import com.salesianostriana.dam.superchollo.backend.security.error.JwtTokenException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.http.fileupload.impl.InvalidContentTypeException;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.IllegalFormatConversionException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildApiError(ex, request, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildApiErrorWithSubErrors("Error de validación. Compruebe la sublista", request, status, ex.getAllErrors());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildApiError(ex, request, status);
    }



    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorImpl
                                .builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message("Error de violación de restricción. Compruebe la sublista")
                                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                                .subErrors(
                                        ex.getConstraintViolations()
                                                .stream()
                                                .map(
                                                        v -> {
                                                            return ApiValidationSubError
                                                                    .builder()
                                                                    .message(v.getMessage())
                                                                    .rejectedValue(v.getInvalidValue())
                                                                    .object(v.getRootBean().getClass().getSimpleName())
                                                                    .field(((PathImpl) v.getPropertyPath()).getLeafNode().asString())
                                                                    .build();
                                                        }
                                                )
                                                .collect(Collectors.toList())
                                )
                                .build()
                );
    }

    @ExceptionHandler({
            CategoriaNotFoundException.class,
            EmptyCategoriaListException.class,
            ProductoNotFoundException.class,
            EmptyProductoListException.class,
            UsuarioNotFoundException.class,
            EmptyUsuarioListException.class,
            SupermercadoNotFoundException.class,
            EmptySupermercadoListException.class
    })
    public ResponseEntity<?> handleNotFoundException(EntityNotFoundException ex, WebRequest request) {

        return buildApiError(ex, request, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({
            UserOldPasswordWrongException.class
    })
    public ResponseEntity<?> handleUserPasswordWrongException(RuntimeException ex, WebRequest request) {

        return buildApiError(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            ClassCastException.class
    })
    public ResponseEntity<?> handleClassCastException(RuntimeException ex, WebRequest request) {

        return buildApiError(ex, request, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({
            InvalidContentTypeException.class
    })
    public ResponseEntity<?> handleInvalidContentTypeException(IOException ex, WebRequest request) {

        return buildApiError(ex, request, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({
            MultipartException.class
    })
    public ResponseEntity<?> handleMultipartException(RuntimeException ex, WebRequest request) {

        return buildApiError(ex, request, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex, WebRequest request) {

        return buildApiError(ex, request, HttpStatus.UNAUTHORIZED);


    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {

        return buildApiError(ex, request, HttpStatus.FORBIDDEN);

    }


    @ExceptionHandler({JwtTokenException.class})
    public ResponseEntity<?> handleTokenException(JwtTokenException ex, WebRequest request) {

        return buildApiError(ex, request, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<?> handleUserNotExistsException(UsernameNotFoundException ex, WebRequest request) {

        return buildApiError(ex, request, HttpStatus.UNAUTHORIZED);

    }


    private final ResponseEntity<Object> buildApiError(Exception ex, WebRequest request, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(ApiErrorImpl
                        .builder()
                        .status(status)
                        .message(ex.getMessage())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    private final ResponseEntity<Object> buildApiErrorWithSubErrors(String message, WebRequest request, HttpStatus status, List<ObjectError> subErrors) {
        return ResponseEntity
                .status(status)
                .body(ApiErrorImpl
                        .builder()
                        .status(status)
                        .message(message)
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .subErrors(
                                subErrors
                                        .stream()
                                        .map(ApiValidationSubError::fromObjectError)
                                        .collect(Collectors.toList())
                        )
                        .build());
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class ErrorMessage {

        private HttpStatus status;
        private String message, path;

        @Builder.Default
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
        private LocalDateTime dateTime = LocalDateTime.now();

        public static GlobalRestControllerAdvice.ErrorMessage of (HttpStatus status, String message, String path) {
            return GlobalRestControllerAdvice.ErrorMessage.builder()
                    .status(status)
                    .message(message)
                    .path(path)
                    .build();
        }

    }
}
