package org.ialibrahim.jwtauthdemo.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "CUSTOMERS")
public class CustomerEntity {
    @Id
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
}
