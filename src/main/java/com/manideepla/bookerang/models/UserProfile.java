package com.manideepla.bookerang.models;

import java.time.OffsetDateTime;

public record UserProfile(
        String firstName,
        String lastName,
        String username,
        OffsetDateTime createdAt
) {
}
