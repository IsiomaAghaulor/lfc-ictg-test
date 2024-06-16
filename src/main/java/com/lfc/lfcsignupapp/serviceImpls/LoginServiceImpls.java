package com.lfc.lfcsignupapp.serviceImpls;

import com.lfc.lfcsignupapp.dtos.CustomResponse;
import com.lfc.lfcsignupapp.dtos.LoginRequest;
import com.lfc.lfcsignupapp.entities.UserInfoEntity;
import com.lfc.lfcsignupapp.repos.SignUpRepo;
import com.lfc.lfcsignupapp.services.LoginService;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginServiceImpls implements LoginService {
    final SignUpRepo signUpRepo;
    @Override
    public ResponseEntity<Object> validateLoginRequest(LoginRequest loginRequest) {
        if(loginRequest.email().isEmpty()){
            CustomResponse customResponse = new CustomResponse(
                    "INVALID EMAIL",
                    "99",
                    loginRequest
            );
            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
        }
        if(loginRequest.password().isEmpty()){
            CustomResponse customResponse = new CustomResponse(
                    "INVALID PASSWORD",
                    "99",
                    loginRequest
            );
            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
        }
        return processLoginRequest(loginRequest);
    }

    @Override
    public ResponseEntity<Object> processLoginRequest(LoginRequest loginRequest) {
        try {
            Optional<UserInfoEntity> optionalUserInfo = signUpRepo.findUserByEmail(loginRequest.email());

            if(optionalUserInfo.isPresent()){
                UserInfoEntity userInfo = optionalUserInfo.get();
                if(!userInfo.getEmail().equalsIgnoreCase(loginRequest.email())){
                    CustomResponse customResponse = new CustomResponse(
                            "INVALID CREDENTIALS",
                            "99",
                            loginRequest
                    );
                    return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
                }
                if(!userInfo.getPassword().equalsIgnoreCase(loginRequest.password())){
                    CustomResponse customResponse = new CustomResponse(
                            "INVALID CREDENTIALS",
                            "99",
                            loginRequest
                    );
                    return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
                }
                CustomResponse customResponse = new CustomResponse("LOGIN SUCCESSFUL", "00", loginRequest);
                return new ResponseEntity<>(customResponse, HttpStatus.OK);

            }else {
                CustomResponse customResponse = new CustomResponse("USER DOES NOT EXIST. PLEASE PROCEED TO SIGN UP", "00", loginRequest);
                return new ResponseEntity<>(customResponse, HttpStatus.OK);

            }
        }catch (DataAccessException | PersistenceException e){
            log.info("DB EXCEPTION CAUGHT RETRIEVING USER INFO REASON: " + e.getCause() + " IN LINE "+ e.getStackTrace()[0]);
            CustomResponse customResponse = new CustomResponse("SYSTEM MALFUNCTIONED LOGGING YOU IN. TRY AGAIN LATER", "00", loginRequest);
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            log.info("EXCEPTION CAUGHT RETRIEVING USER INFO REASON: " + e.getCause() + " IN LINE "+ e.getStackTrace()[0]);
            CustomResponse customResponse = new CustomResponse("SYSTEM MALFUNCTIONED LOGGING YOU IN. TRY AGAIN LATER", "00", loginRequest);
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
