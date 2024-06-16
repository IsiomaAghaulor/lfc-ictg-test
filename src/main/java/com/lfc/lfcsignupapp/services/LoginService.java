package com.lfc.lfcsignupapp.services;

import com.lfc.lfcsignupapp.dtos.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<Object>  validateLoginRequest(LoginRequest loginRequest);
    ResponseEntity<Object>  processLoginRequest(LoginRequest loginRequest);

}
