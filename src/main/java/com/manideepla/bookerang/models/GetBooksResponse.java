package com.manideepla.bookerang.models;

import java.util.List;

public record GetBooksResponse(List<UserCopyItem> books) {
}
