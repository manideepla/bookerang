package com.manideepla.bookerang.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("authors")
public record Author(@Id UUID id, String name) {

}
