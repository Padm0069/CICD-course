package com.sk.employee_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Title cannot be empty")
    @NotNull(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Duration cannot be empty")
    @NotNull(message = "Duration cannot be empty")
    private String duration;

    @NotEmpty(message = "Author cannot be empty")
    @NotNull(message = "Author cannot be empty")
    private String author;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee; // Reference to Employee
}
