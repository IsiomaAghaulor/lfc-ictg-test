package com.lfc.lfcsignupapp.dtos;

public record CreateNewPasswordRequest(
        String email,
        String newPassword,
        String confirmNewPassword
) {
}
