package com.lfc.lfcsignupapp.controllers;

import com.lfc.lfcsignupapp.dtos.LoginRequest;
import com.lfc.lfcsignupapp.dtos.SignUpRequest;
import com.lfc.lfcsignupapp.services.LoginService;
import com.lfc.lfcsignupapp.services.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lfc/user-onboarding")
@RequiredArgsConstructor
public class SignUpController {
    final SignUpService signUpService;
    final LoginService loginService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp (@Validated @RequestBody SignUpRequest request){
        return signUpService.validateSignUpRequest(request);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login (@Validated @RequestBody LoginRequest request){
        return loginService.validateLoginRequest(request);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Object> forgotPassword (@Validated @RequestBody SignUpRequest request){
        return signUpService.validateSignUpRequest(request);
    }

}
