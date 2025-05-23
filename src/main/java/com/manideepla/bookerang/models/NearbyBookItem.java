package com.manideepla.bookerang.models;

public record NearbyBookItem(
        String id,
        String title,
        String username,
        Double distance
) {
}
