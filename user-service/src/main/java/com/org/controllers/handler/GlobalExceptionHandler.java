package com.org.controllers.handler;

import com.org.exceptions.AccountNotFoundException;
import com.org.exceptions.EmailNotFoundException;
import com.org.exceptions.InvalidEmailFormatException;
import com.org.exceptions.InvalidNumberFormatException;
import com.org.exceptions.LastEmailException;
import com.org.exceptions.LastPhoneException;
import com.org.exceptions.NotEnoughMoneyException;
import com.org.exceptions.PhoneNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PhoneNotFoundException.class)
    public ResponseEntity<?> handlePhoneNotFoundException(PhoneNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LastPhoneException.class)
    public ResponseEntity<?> handleLastPhoneException(LastPhoneException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidNumberFormatException.class)
    public ResponseEntity<?> handleInvalidNumberFormatException(InvalidNumberFormatException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LastEmailException.class)
    public ResponseEntity<?> handleLastEmailException(LastEmailException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailFormatException.class)
    public ResponseEntity<?> handleInvalidEmailFormatException(InvalidEmailFormatException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<?> handleEmailNotFoundException(EmailNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> handleAccountNotFoundException(AccountNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<?> handleNotEnoughMoneyException(NotEnoughMoneyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
