package com.lfc.lfcsignupapp.dtos;

public record UpdatePasswordRequest(
        String email,
        String password,
        String newPassword
) {
}
