package com.lfc.lfcsignupapp.dtos;

public record CustomResponse(
        String responseMsg,
        String responseCode,
        Object responseDetails
) {
}
