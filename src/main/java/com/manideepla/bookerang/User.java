package com.manideepla.bookerang;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public record User(
        String username,
        String password,
        @Column("first_name") String firstName,
        @Column("last_name") String lastName) {
}
