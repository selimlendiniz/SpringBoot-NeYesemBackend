package com.neyesem.neyesembackend.dto;

import java.util.List;

public record UserDetailResponse(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,

        List<UserCommentResponse> comments
) {
}
