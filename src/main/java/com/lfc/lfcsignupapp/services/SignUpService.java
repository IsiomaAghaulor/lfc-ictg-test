package com.lfc.lfcsignupapp.services;

import com.lfc.lfcsignupapp.dtos.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface SignUpService {
    ResponseEntity<Object> validateSignUpRequest(SignUpRequest request);
    ResponseEntity<Object> saveUserInfoToDb(SignUpRequest request);
}
