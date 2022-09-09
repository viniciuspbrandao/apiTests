package com.vb.apiTests.resources.exceptions;

import com.vb.apiTests.services.exceptions.DataIntegratyViolationException;
import com.vb.apiTests.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String OBJECT_NOT_FOUND = "Object Not Found.";
    public static final String EMAIL_ALREADY_EXISTS_IN_THE_SYSTEM = "Email already exists in the system.";
    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler
                .objectNotFound(
                        new ObjectNotFoundException(OBJECT_NOT_FOUND),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(OBJECT_NOT_FOUND, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/users/2", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());


    }

    @Test
    void dataIntegrityViolationException() {
        ResponseEntity<StandardError> response = exceptionHandler
                .dataIntegrityViolationException(
                        new DataIntegratyViolationException(EMAIL_ALREADY_EXISTS_IN_THE_SYSTEM),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(EMAIL_ALREADY_EXISTS_IN_THE_SYSTEM, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }
}