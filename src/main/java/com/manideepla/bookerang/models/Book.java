package com.manideepla.bookerang.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("books")
public record Book(String title, UUID author, @Id UUID id) {
}
