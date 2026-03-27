package com.AfyaFlow.demo.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
    @Column(unique = true, nullable = false)
  private String email;
  @JsonIgnore
  private String password;
  @Enumerated(EnumType.STRING)
  private Role role;
}