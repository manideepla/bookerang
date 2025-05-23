package com.manideepla.bookerang.models;

import java.util.List;

public record BooksResponse(List<NearbyBookItem> books) {
}
