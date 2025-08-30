package com.manideepla.bookerang;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "users")
public class User {
    @Id
    String username;
    String password;
    String firstName;
    String lastName;
}
