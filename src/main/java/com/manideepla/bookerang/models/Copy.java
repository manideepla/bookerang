package com.manideepla.bookerang.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("copies")
public record Copy(@Id UUID id,
                   @Column("book_id")
                   UUID bookId,
                   String owner) {
}
