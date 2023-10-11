package com.neyesem.neyesembackend.dto;

import java.util.List;

public record UserProfileResponse(
        Long id,

        String username,

        String firstName,

        String lastName,

        List<UserCommentResponse> comments



) {
}
