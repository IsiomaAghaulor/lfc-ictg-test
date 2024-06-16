package com.lfc.lfcsignupapp.dtos;

public record SignUpRequest(
        String userName,
        String email,
        String phone,
        String password
) {
}
