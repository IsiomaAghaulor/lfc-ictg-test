package com.lfc.lfcsignupapp.serviceImpls;

import com.lfc.lfcsignupapp.dtos.CustomResponse;
import com.lfc.lfcsignupapp.dtos.SignUpRequest;
import com.lfc.lfcsignupapp.entities.UserInfoEntity;
import com.lfc.lfcsignupapp.repos.SignUpRepo;
import com.lfc.lfcsignupapp.services.SignUpService;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class SignUpServiceImpl implements SignUpService {
    final SignUpRepo signUpRepo;
    @Override
    public ResponseEntity<Object> validateSignUpRequest(SignUpRequest request) {
        if(request.userName().isEmpty()){
            CustomResponse customResponse = new CustomResponse(
                    "INVALID USER NAME",
                    "99",
                    request
            );
            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
        }
        if(request.email().isEmpty()){
            CustomResponse customResponse = new CustomResponse(
                    "INVALID EMAIL",
                    "99",
                    request
            );
            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
        }
        if(request.password().isEmpty()){
            CustomResponse customResponse = new CustomResponse(
                    "INVALID PASSWORD",
                    "99",
                    request
            );
            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
        }
        return saveUserInfoToDb(request);
    }

    @Override
    public ResponseEntity<Object> saveUserInfoToDb(SignUpRequest request) {
        try{
            UserInfoEntity userInfo = new UserInfoEntity();

            userInfo.setUserName(request.userName());
            userInfo.setEmail(request.email());
            userInfo.setPassword(request.password());
            userInfo.setPhone(request.phone());

            signUpRepo.save(userInfo);
            CustomResponse customResponse = new CustomResponse("SIGN UP SUCCESSFUL", "00", request);
            return new ResponseEntity<>(customResponse, HttpStatus.OK);

        }catch (DataAccessException | PersistenceException e){
            log.info("DB EXCEPTION CAUGHT SAVING USER INFO TO DB REASON: "+ e.getCause() + " IN LINE " + e.getStackTrace()[0]);
            CustomResponse customResponse = new CustomResponse("SYSTEM MALFUNCTIONED SIGNING YOU UP. TRY AGAIN LATER", "00", request);
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception e){
            log.info("EXCEPTION CAUGHT SAVING USER INFO TO DB REASON: "+ e.getCause() + " IN LINE " + e.getStackTrace()[0]);
            CustomResponse customResponse = new CustomResponse("SYSTEM MALFUNCTIONED SIGNING YOU UP. TRY AGAIN LATER", "00", request);
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
